package ru.nsu.ccfit.molochev.task1.MyExcepts.HandleExcept;

public class ArgAmountException extends HandleException {
    private int real;
    private int required;

    private ArgAmountException(){}
    public ArgAmountException(int real_, int required_){
        real = real_;
        required = required_;
    }

    @Override
    public void print() {

    }
}
