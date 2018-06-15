package Classes.Factory;

import Classes.Factory.Details.Detail;

import java.util.ArrayList;

public class Storage <T extends Detail> {
    private int size;
    private ArrayList<T> storage = new ArrayList<>();

    public Storage(int size){
        this.size = size;
    }

    public synchronized void put(T d){
        while (storage.size() == size){
            try{
               this.wait();
            }
            catch (InterruptedException e){
                Thread.currentThread().interrupt();
                return;
            }
        }
        storage.add(d);
        this.notify();
    }

    public synchronized T get(){
        while (storage.isEmpty()){
            try{
                this.wait();
            }
            catch (InterruptedException e){
                return null;
            }
        }
        T tmp =  storage.remove(0);
        this.notify();
        return tmp;
    }

    public synchronized int getDetailsAmount(){
        return storage.size();
    }
}
