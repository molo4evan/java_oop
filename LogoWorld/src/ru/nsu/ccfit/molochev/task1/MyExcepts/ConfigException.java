package ru.nsu.ccfit.molochev.task1.MyExcepts;

public class ConfigException extends MainException {
    private String file;

    private ConfigException(){}
    public ConfigException(String msg){
        file = msg;
    }

    public void print(){
        System.out.println("Error in config file \"" + file + "\"");
    }
}
