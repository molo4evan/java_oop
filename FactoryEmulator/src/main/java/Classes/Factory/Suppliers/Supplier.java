package Classes.Factory.Suppliers;

import Classes.Exceptions.NoSubException;
import Classes.Factory.Delay;
import Classes.Factory.Storage;
import Classes.OS.Observable;
import Classes.OS.Subscriber;

import java.util.ArrayList;

public abstract class Supplier extends Thread implements Observable{
    ArrayList<Subscriber> subs = new ArrayList<>();
    Delay time_to_provide;
    Storage storage;
    int detail_num = 0;
    boolean dbg;

    Supplier(String name, Delay delay, Storage st, boolean dbg){
        super(name);
        time_to_provide = delay;
        storage = st;
        this.dbg = dbg;
    }

    public Integer getTotal(){
        return storage.getDetailsAmount();
    }

    public void setDelay(int value){
        time_to_provide.setValue(value);
    }

    @Override
    public void notifySubs() {
        for (Subscriber s: subs) s.update();
    }

    @Override
    public void removeSub(Subscriber sub) throws NoSubException{
        if (!subs.contains(sub)) throw new NoSubException(this, sub);
        subs.remove(sub);
    }

    @Override
    public void addSub(Subscriber sub) {
        subs.add(sub);
    }
}
