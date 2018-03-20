package ru.nsu.ccfit.molochev.task1.MyExcepts.HandleExcept;

import java.util.ArrayList;

public class InitException extends HandleException {
    public enum Situations{ArgFormat, IncSize, IncPos}

    Situations s;
    ArrayList<String> args;

    private InitException(){}
    public InitException(Situations s_, ArrayList<String> args_){
        s = s_;
        args = args_;
    }

    @Override
    public void print() {

    }
}
