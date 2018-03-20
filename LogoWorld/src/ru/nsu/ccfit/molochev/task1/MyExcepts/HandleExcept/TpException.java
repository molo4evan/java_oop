package ru.nsu.ccfit.molochev.task1.MyExcepts.HandleExcept;

public class TpException extends HandleException {
    String number;

    private TpException(){}
    public TpException(String num){
        number = num;
    }

    @Override
    public void print() {

    }
}
