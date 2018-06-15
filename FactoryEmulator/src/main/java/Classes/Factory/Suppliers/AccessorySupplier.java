package Classes.Factory.Suppliers;

import Classes.Factory.Delay;
import Classes.Factory.Details.Accessory;
import Classes.Factory.Storage;

import java.time.LocalDateTime;

public class AccessorySupplier extends Supplier {
    private int my_num;

    public AccessorySupplier(int num, Delay delay, Storage<Accessory> st, boolean dbg){
        super("Accessory Supplier " + num, delay, st, dbg);
        my_num = num;
    }

    @Override
    public void run() {
        while (true){
            try {
                sleep(super.time_to_provide.getValue());
            }
            catch (InterruptedException e){
                if (dbg) System.out.println("Accessory supplier " + my_num + " ended");
                break;
            }

            Accessory tmp = new Accessory(my_num, super.detail_num++);
            super.storage.put(tmp);
            notifySubs();
            if (dbg){
                System.out.println("<" + LocalDateTime.now() + ">: Accessory Supplier <" + my_num +
                                    ">: Accessory <" + tmp.getID() + ">)");
            }

            if (isInterrupted()){
                if (dbg) System.out.println("Accessory supplier " + my_num + " ended");
                break;
            }
        }
    }
}
