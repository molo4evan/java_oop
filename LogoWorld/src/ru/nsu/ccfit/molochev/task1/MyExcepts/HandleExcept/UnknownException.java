package ru.nsu.ccfit.molochev.task1.MyExcepts.HandleExcept;

public class UnknownException extends HandleException {

    private UnknownException(){}
    public UnknownException(Throwable e){
        initCause(e);
    }

    public void print(){
        System.out.println("Unrecognized type of exception while handling: " + getCause().getClass().getName());
    }

    public void printAndThrow() throws Throwable{
        print();
        throw getCause();
    }
}
