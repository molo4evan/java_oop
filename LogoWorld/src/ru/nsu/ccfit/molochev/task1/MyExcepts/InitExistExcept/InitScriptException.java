package ru.nsu.ccfit.molochev.task1.MyExcepts.InitExistExcept;

public class InitScriptException extends InitExistanceException {
    private String file;

    private InitScriptException(){}
    public InitScriptException(String f){file = f;}

    public void print(){
        System.out.println("ERROR: script \"" + file + "\" have no INIT command");
    }
}
