package ru.nsu.ccfit.molochev.task1.MyExcepts.InitExistExcept;

public class InitConfException extends InitExistanceException {
    private String file;

    private InitConfException(){}
    public InitConfException(String f){file = f;}

    public void print(){
        System.out.println("Error in config file \"" + file + "\", there is no INIT command");
    }
}
