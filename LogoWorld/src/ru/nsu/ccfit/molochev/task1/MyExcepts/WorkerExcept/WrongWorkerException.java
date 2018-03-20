package ru.nsu.ccfit.molochev.task1.MyExcepts.WorkerExcept;

public class WrongWorkerException extends WorkerException {
    private String msg;

    private WrongWorkerException(){}
    public WrongWorkerException(String s){
        msg = s;
    }

    public void print(){
        System.out.println("ERROR: smth is wrong with class: " + msg);
    }
}
