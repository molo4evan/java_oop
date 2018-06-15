import Classes.Factory.Factory;
import Classes.GUI.GUI;

import java.util.ArrayList;

public class FactoryEmulator {
    public static void main(String[] args) throws Exception{
        ArrayList<Integer> delays = new ArrayList<Integer>(){{
            add(1000);
            add(1000);
            add(1000);
            add(1000);
        }};
        Factory f = new Factory("config.properties", delays, true);
        new GUI(f);
    }
}
