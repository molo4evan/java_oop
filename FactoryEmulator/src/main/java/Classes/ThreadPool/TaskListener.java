package Classes.ThreadPool;

public interface TaskListener {
    void taskStarted(Task t);
    void taskInterrupted(Task t);
    void taskFinished(Task t);
}