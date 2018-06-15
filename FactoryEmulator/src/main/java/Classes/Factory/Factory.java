package Classes.Factory;

import Classes.Factory.Details.*;
import Classes.Factory.Suppliers.*;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;

public class Factory {
    private MotorSupplier motorSupplier;
    private BodySupplier bodySupplier;
    private ArrayList<AccessorySupplier> accessorySuppliers;
    private AutoStorageController controller;
    private ArrayList<Dealer> dealers;
    private ArrayList<Integer> delays;
    private Properties prop = new Properties();
    private SynchronousFileWriter log = null;

    private int getProperty(String s) throws Exception{
        String ss = prop.getProperty(s);
        if (ss == null) throw new Exception(); //TODO: exc
        int i;
        try {
            i = Integer.parseInt(ss);
        }
        catch (NumberFormatException e){
            throw  new Exception(); //TODO: exc
        }
        return i;
    }

    public Factory(String filename, ArrayList<Integer> delays, boolean dbg) throws Exception{
        if (delays.size() != 4) throw new Exception();  //TODO: exc

        try {
            prop.load(new FileReader(filename));
        }
        catch (IOException e){
            throw new Exception();  //TODO: exc
        }

        if (prop.getProperty("MotorStorageSize") == null ||
            prop.getProperty("BodyStorageSize") == null ||
            prop.getProperty("AccessoryStorageSize") == null ||
            prop.getProperty("AssemblyLineBufferSize") == null ||
            prop.getProperty("CarStorageSize") == null ||
            prop.getProperty("MinimalCarsAmount") == null ||
            prop.getProperty("RequestSize") == null ||
            prop.getProperty("AccessorySuppliers") == null ||
            prop.getProperty("Dealers") == null ||
            prop.getProperty("Workers") == null ||
            prop.getProperty("LogSale") == null) throw new Exception(); //TODO: exc

        if (getProperty("MotorStorageSize") <= 0 ||
            getProperty("BodyStorageSize") <= 0 ||
            getProperty("AccessoryStorageSize") <= 0 ||
            getProperty("AssemblyLineBufferSize") <= 0 ||
            getProperty("CarStorageSize") <= 0 ||
            getProperty("MinimalCarsAmount") <= 0 ||
            getProperty("RequestSize") <= 0 ||
            getProperty("AccessorySuppliers") <= 0 ||
            getProperty("Dealers") <= 0 ||
            getProperty("Workers") <= 0) throw new Exception(); //TODO: exc

        String cond = prop.getProperty("LogSale");
        boolean log;
        if (cond.equals("true")) log = true;
        else if (cond.equals("false")) log = false;
        else throw new Exception(); //TODO: exc

        this.delays = delays;

        Delay md = new Delay(delays.get(0));
        Storage<Motor> motorStorage = new Storage<>(getProperty("MotorStorageSize"));
        motorSupplier = new MotorSupplier(md, motorStorage, dbg);

        Delay bd = new Delay(delays.get(1));
        Storage<Body> bodyStorage = new Storage<>(getProperty("BodyStorageSize"));
        bodySupplier = new BodySupplier(bd, bodyStorage, dbg);

        Delay ad = new Delay(delays.get(2));
        Storage<Accessory> accessoryStorage = new Storage<>(getProperty("AccessoryStorageSize"));
        accessorySuppliers = new ArrayList<>();
        for (int i = 0; i < getProperty("AccessorySuppliers"); i++){
            accessorySuppliers.add(new AccessorySupplier(i, ad, accessoryStorage, dbg)); //TODO: other id?
        }

        AssemblyLine al = new AssemblyLine(accessoryStorage, bodyStorage, motorStorage, getProperty("Workers"), getProperty("AssemblyLineBufferSize"));
        Storage<Car> cs = new Storage<>(getProperty("CarStorageSize"));
        controller = new AutoStorageController(cs, al, getProperty("MinimalCarsAmount"), getProperty("RequestSize"), dbg);  //TODO: other names?

        SynchronousFileWriter w = null;
        try {
            if (log) w = new SynchronousFileWriter("fabric.log");
        }
        catch (IOException e){
            throw new Exception();  //TODO: exc
        }
        this.log = w;

        AtomicInteger total = new AtomicInteger(0);
        Delay dd = new Delay(delays.get(3));
        dealers = new ArrayList<>();
        for (int i = 0; i < getProperty("Dealers"); i++){
            dealers.add(new Dealer(i, dd, cs, w, dbg, total));
        }
    }

    public void startWork(){
        motorSupplier.start();
        bodySupplier.start();
        for (AccessorySupplier s: accessorySuppliers) s.start();
        controller.start();
        for (Dealer d: dealers) d.start();
    }

    public void endWork(){
        for (Dealer d: dealers) d.interrupt();
        controller.interrupt();
        for (AccessorySupplier s: accessorySuppliers) s.interrupt();
        bodySupplier.interrupt();
        motorSupplier.interrupt();
        try {
            log.close();
        }
        catch (IOException e){
            //TODO: handling?
        }
    }

    public ArrayList<AccessorySupplier> getAccessorySuppliers() {
        return accessorySuppliers;
    }

    public BodySupplier getBodySupplier() {
        return bodySupplier;
    }

    public MotorSupplier getMotorSupplier() {
        return motorSupplier;
    }

    public AutoStorageController getController() {
        return controller;
    }

    public ArrayList<Dealer> getDealers() {
        return dealers;
    }

    public ArrayList<Integer> getDelays(){
        return delays;
    }
}
