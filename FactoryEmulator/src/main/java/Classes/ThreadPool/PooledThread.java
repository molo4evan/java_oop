package Classes.ThreadPool;

import java.util.List;

public class PooledThread extends Thread{
    private final List<ThreadPoolTask> tasks;

    public PooledThread(List<ThreadPoolTask> queue, String name)
    {
        super(name);
        tasks = queue;
    }

    private void performTask(ThreadPoolTask t){
        t.prepare();
        try {
            t.go();
        }
        catch (InterruptedException ex) {
            t.interrupted();
            return;
        }
        t.finish();
    }

    @Override
    public void run() {
        ThreadPoolTask toExec = null;
        while (true) {
            synchronized (tasks) {
                if (tasks.isEmpty()) {
                    try {
                        tasks.wait();
                    } catch (InterruptedException e) { return; }
                } else {
                    toExec = tasks.remove(0);
                }
            }
            if (toExec == null){
                if (isInterrupted()) return;
                else continue;
            }
            performTask(toExec);
            if (isInterrupted()) return;
        }
    }
}
