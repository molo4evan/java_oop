package Classes.Factory;

import Classes.Exceptions.NoSubException;
import Classes.Factory.Details.Car;
import Classes.OS.Observable;
import Classes.OS.Subscriber;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class AutoStorageController extends Thread implements Observable{
    private ArrayList<Subscriber> subs = new ArrayList<>();
    private Storage<Car> cars;
    private AssemblyLine line;
    private int minimal_capacity;
    private int request_size;
    private boolean dbg;

    AutoStorageController(Storage<Car> c, AssemblyLine al, int min_cap, int req_size, boolean dbg){
        super("AutoStorage Controller");
        line = al;
        cars = c;
        minimal_capacity = min_cap;
        request_size = req_size;
        this.dbg = dbg;
    }

    @Override
    public void run() {
        while (true){
            if (cars.getDetailsAmount() < minimal_capacity){
                notifySubs();
                for (int i = 0; i < request_size; i++) line.assembleRequest();
                for (int i = 0; i < request_size; i++) {
                    Car tmp = line.getCar();
                    if (tmp == null) {
                        line.endWork();
                        if (dbg) System.out.println("Controller ended");
                        return;
                    }
                    cars.put(tmp);
                    notifySubs();
                    if (dbg){
                        System.out.println("<" + LocalDateTime.now() + ">: Controller: Auto <" +
                                tmp.getID() + "> (Body: <" + tmp.getBody().getID() + ">, Motor: <" +
                                tmp.getMotor().getID() + ">, Accessory: <" + tmp.getAccessory().getID()
                                + ">)");
                    }

                    if (goOut()) return;
                }
            }
            if (goOut()) return;
                while (cars.getDetailsAmount() > minimal_capacity) try{
                    cars.wait();
                }
                catch (InterruptedException e){
                    line.endWork();
                    if (dbg) System.out.println("Controller ended");
                    return;
                }
            notifySubs();
        }
    }

    private boolean goOut() {
        if (isInterrupted()){
            line.endWork();
            if (dbg) System.out.println("Controller ended");
            return true;
        }
        return false;
    }

    public AssemblyLine getLine() {
        return line;
    }

    @Override
    public void addSub(Subscriber sub) {
        subs.add(sub);
    }

    @Override
    public void removeSub(Subscriber sub) throws NoSubException{
        if (!subs.contains(sub)) throw new NoSubException(this, sub);
        subs.remove(sub);
    }

    @Override
    public void notifySubs() {
        for (Subscriber s: subs) s.update();
    }

    public int getCarsAmount(){
        return cars.getDetailsAmount();
    }

    public int getMinimalCapacity() {
        return minimal_capacity;
    }

    public int getRequestSize() {
        return request_size;
    }
}
