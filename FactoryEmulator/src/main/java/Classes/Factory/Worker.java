package Classes.Factory;

import Classes.Factory.Details.*;
import Classes.ThreadPool.Task;

public class Worker implements Task{
    private Storage<Accessory> as;
    private Storage<Body> bs;
    private Storage<Motor> ms;
    private Car result;

    public Worker(Storage<Accessory> a, Storage<Body> b, Storage<Motor> m){
        as = a;
        bs = b;
        ms = m;
    }

    @Override
    public void performWork() {
        Accessory a = as.get();
        Body b = bs.get();
        Motor m = ms.get();
        if (a == null || b == null || m == null) {
            Thread.currentThread().interrupt();
            return;
        }
        result = new Car(a, m, b);
    }

    public Car getCar(){
        return result;
    }
}
