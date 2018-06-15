package Classes.ThreadPool;

public interface Task {
    //String getName();   //TODO: need?
    void performWork() throws InterruptedException;
}
