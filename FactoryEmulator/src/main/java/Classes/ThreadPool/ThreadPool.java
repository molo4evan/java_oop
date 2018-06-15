package Classes.ThreadPool;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class ThreadPool {       //TODO: maybe demonic thread pool?
    private final List<ThreadPoolTask> taskQueue = new LinkedList<>();
    private Set<PooledThread> threads = new HashSet<>();

    public void addTask(Task t, TaskListener l){
        synchronized (taskQueue){
            taskQueue.add(new ThreadPoolTask(t, l));
            taskQueue.notify();
        }
    }

    public ThreadPool(int num){
        for (int i = 0; i <  num; i++){
            PooledThread tmp = new PooledThread(taskQueue, "Pooled Thread " + i);
            threads.add(tmp);
            tmp.start();
        }
    }

    public void destroy(){
        for (PooledThread t: threads){
            t.interrupt();
        }
    }
}
