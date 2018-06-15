package Classes.Factory.Suppliers;

import Classes.Factory.Delay;
import Classes.Factory.Details.Body;
import Classes.Factory.Storage;

import java.time.LocalDateTime;

public class BodySupplier extends Supplier{
    public BodySupplier(Delay delay, Storage<Body> st, boolean dbg){
        super("Body Supplier", delay, st, dbg);
    }

    @Override
    public void run() {
        while (true){
            try {
                sleep(super.time_to_provide.getValue());
            }
            catch (InterruptedException e){
                if (dbg) System.out.println("Body supplier ended");
                break;
            }

            Body tmp = new Body(super.detail_num++);
            super.storage.put(tmp);
            notifySubs();
            if (dbg){
                System.out.println("<" + LocalDateTime.now() + ">: Body Supplier: Body <" + tmp.getID() + ">)");
            }

            if (isInterrupted()){
                if (dbg) System.out.println("Body supplier ended");
                break;
            }
        }
    }
}
