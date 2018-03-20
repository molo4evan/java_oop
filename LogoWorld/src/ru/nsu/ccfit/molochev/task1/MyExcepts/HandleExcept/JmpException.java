package ru.nsu.ccfit.molochev.task1.MyExcepts.HandleExcept;

public class JmpException extends HandleException {
    public enum Cause{number, condFormat}

    private String num;
    private Cause cause;

    private JmpException(){}
    public JmpException(Cause c, String n){
        cause = c;
        num = n;
    }
    public JmpException(Cause c, Integer n){
        cause = c;
        num = n.toString();
    }

    @Override
    public void print() {

    }
}
