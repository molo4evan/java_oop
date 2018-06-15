package Classes.ThreadPool;

class ThreadPoolTask {
    private TaskListener listener;
    private Task task;

    ThreadPoolTask(Task t, TaskListener l) {
        listener = l;
        task = t;
    }
    void prepare()
    {
        listener.taskStarted(task);
    }
    void finish()
    {
        listener.taskFinished(task);
    }
    void interrupted()
    {
        listener.taskInterrupted(task);
    }
    void go() throws InterruptedException
    {
        task.performWork();
    }
    /*String getName()
    {
        return task.getName();
    }*/
}