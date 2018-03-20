package ru.nsu.ccfit.molochev.task1;

import ru.nsu.ccfit.molochev.task1.MyExcepts.MainException;

import java.io.FileNotFoundException;

public class LogoWorld {        //TODO: add logging
    private ScriptHandler sh;

    private LogoWorld(){}

    public LogoWorld(String conf) throws MainException {
        sh = new ScriptHandler();
        Fabric.getInstance().configure(conf);
    }

    public void handleScript(String scriptname) throws MainException, FileNotFoundException{
        sh.handleScript(scriptname);
    }

    public void reconfigure(String new_conf) throws MainException {
        Fabric.getInstance().configure(new_conf);
    }
}
