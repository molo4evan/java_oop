package ru.nsu.ccfit.molochev.task1.MyExcepts.WorkerExcept;

public class UnrecWorkerException extends WorkerException {
    private String msg;

    private UnrecWorkerException(){}
    public UnrecWorkerException(String m){
        msg = m;
    }

    public void print(){
        System.out.println("ERROR: unrecognized class: " + msg);
    }
}
