package Classes.Factory;

import Classes.Exceptions.NoSubException;
import Classes.Factory.Details.*;
import Classes.OS.Observable;
import Classes.OS.Subscriber;
import Classes.ThreadPool.Task;
import Classes.ThreadPool.TaskListener;
import Classes.ThreadPool.ThreadPool;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class AssemblyLine implements TaskListener, Observable{      //TODO: why increasing?
    private ArrayList<Subscriber> subs = new ArrayList<>();
    private Storage<Accessory> acessory_storage;
    private Storage<Body> body_storage;
    private Storage<Motor> motor_storage;
    private Storage<Car> buffer;
    private ThreadPool tp;
    private int workers_amount;

    AssemblyLine(Storage<Accessory> as, Storage<Body> bs, Storage<Motor> ms, int workers_num, int buf){
        acessory_storage = as;
        body_storage = bs;
        motor_storage = ms;
        tp = new ThreadPool(workers_num);
        workers_amount = workers_num;
        buffer = new Storage<>(buf);
    }

    public void assembleRequest(){
        Worker w = new Worker(acessory_storage, body_storage, motor_storage);
        tp.addTask(w, this);
    }

    public Car getCar(){
        Car tmp = buffer.get();
        notifySubs();
        return tmp;
    }

    public void endWork(){
        tp.destroy();
    }

    @Override
    public void taskStarted(Task t) {}

    @Override
    public void taskInterrupted(Task t) {}

    @Override
    public void taskFinished(Task t) {  //???
        if (!(t instanceof Worker)){
            System.err.println("AssemblyLine: worker type was incorrect");
            return;
        }
        Worker w = (Worker)t;
            buffer.put(w.getCar());
            notifySubs();
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

    public int getWorkersAmount(){
        return workers_amount;
    }

    public int getCarsAmount(){
        return buffer.getDetailsAmount();
    }
}
