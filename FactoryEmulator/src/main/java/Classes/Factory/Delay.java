package Classes.Factory;

public class Delay {
    private long value;

    Delay(int v){
        value = v;
    }

    public synchronized void setValue(long value){
        this.value = value;
    }

    public synchronized long getValue() {
        return value;
    }
}
