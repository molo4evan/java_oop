package Classes.Factory;

import Classes.Exceptions.NoSubException;
import Classes.Factory.Details.Car;
import Classes.OS.Observable;
import Classes.OS.Subscriber;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
//import java.util.ArrayList;

public class Dealer extends Thread implements Observable{
    private ArrayList<Subscriber> subs = new ArrayList<>();
    private Delay buying_delay;
    private int id;
    private Storage<Car> store;
    private AtomicInteger total;
    //private ArrayList<Car> dealed_cars = new ArrayList<>();
    private SynchronousFileWriter log;
    private boolean dbg;

    Dealer(int num, Delay delay, Storage<Car> cs, SynchronousFileWriter l, boolean dbg, AtomicInteger total){
        super("Dealer " + num);
        store = cs;
        id = num;
        buying_delay = delay;
        log = l;
        this.dbg = dbg;
        this.total = total;
    }

    @Override
    public void run(){
        while (true){
            Car tmp = store.get();
            if (tmp == null) interrupt();
            if (isInterrupted()){
                if (dbg) System.out.println("Dealer " + id + " ended");
                break;
            }
            int t = total.incrementAndGet();
            notifySubs();
            if (log != null) try{
                log.write("<" + LocalDateTime.now() + ">: Dealer <" + id + ">: Auto <" +
                            tmp.getID() + "> (Body: <" + tmp.getBody().getID() + ">, Motor: <" +
                            tmp.getMotor().getID() + ">, Accessory: <" + tmp.getAccessory().getID()
                            + ">)\n");
            }
            catch (IOException e){
                //TODO: process   ???
            }
            if (dbg) {
                System.out.println("<" + LocalDateTime.now() + ">: Dealer <" + id + ">: Auto <" +
                        tmp.getID() + "> (Body: <" + tmp.getBody().getID() + ">, Motor: <" +
                        tmp.getMotor().getID() + ">, Accessory: <" + tmp.getAccessory().getID()
                        + ">)");
                System.out.println("Total cars: " + t);
            }

            if (isInterrupted()){
                if (dbg) System.out.println("Dealer " + id + " ended");
                break;
            }
            try {
                sleep(buying_delay.getValue());
            }
            catch (InterruptedException e){
                if (dbg) System.out.println("Dealer " + id + " ended");
                break;
            }
        }
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

    public Integer getTotal() {
        return total.get();
    }

    public void setDelay(int val){
        buying_delay.setValue(val);
    }
}
