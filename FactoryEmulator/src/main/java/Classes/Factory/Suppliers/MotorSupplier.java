package Classes.Factory.Suppliers;

import Classes.Factory.Delay;
import Classes.Factory.Details.Motor;
import Classes.Factory.Storage;

import java.time.LocalDateTime;

public class MotorSupplier extends Supplier{
    public MotorSupplier(Delay delay, Storage<Motor> st, boolean dbg){
        super("Motor Supplier", delay, st, dbg);
    }

    @Override
    public void run() {
        while (true){
            try {
                sleep(super.time_to_provide.getValue());
            }
            catch (InterruptedException e){
                if (dbg) System.out.println("Motor supplier ended");
                break;
            }

            Motor tmp = new Motor(super.detail_num++);
            super.storage.put(tmp);
            notifySubs();
            if (dbg){
                System.out.println("<" + LocalDateTime.now() + ">: Motor Supplier: Motor <" + tmp.getID() + ">)");
            }
            if (isInterrupted()){
                if (dbg) System.out.println("Motor supplier ended");
                break;
            }
        }
    }
}
