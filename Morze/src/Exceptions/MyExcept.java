package Exceptions;

public abstract class MyExcept extends Exception {
    public void err_msg(){
        System.err.println("ERROR: unrecognized error");
    }
}
