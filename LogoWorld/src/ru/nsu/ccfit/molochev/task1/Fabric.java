package ru.nsu.ccfit.molochev.task1;

import ru.nsu.ccfit.molochev.task1.MyExcepts.*;
import ru.nsu.ccfit.molochev.task1.MyExcepts.InitExistExcept.InitConfException;
import ru.nsu.ccfit.molochev.task1.MyExcepts.WorkerExcept.UnrecWorkerException;
import ru.nsu.ccfit.molochev.task1.MyExcepts.WorkerExcept.WrongWorkerException;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;

class Fabric {
    private static Fabric ourInstance = new Fabric();
    private Properties prop = null;
    private Map<String, Object> workers;

    static Fabric getInstance() {
        return ourInstance;
    }

    private Fabric() {

    }

    void configure(String filename) throws ConfigException, InitConfException {
        prop = new Properties();
        ClassLoader cl = ClassLoader.getSystemClassLoader();
        try {
            prop.load(cl.getResourceAsStream(filename));
        }
        catch (IOException e) {
            throw new ConfigException(filename);
        }
        if (!prop.contains("INIT")) throw new InitConfException(filename);
    }

    Object getWorker(String name) throws MainException {
        Object ret;
        if ((ret = workers.get(name)) != null) return ret;
        else{
            String classname = prop.getProperty(name);
            if (classname == null) throw new UnrecWorkerException(name);
            try{
                Class cl = Class.forName(classname);
                ret = cl.newInstance();
                workers.put(name, ret);
                return ret;
            }
            catch (ClassNotFoundException | InstantiationException | IllegalAccessException e1){
                throw new WrongWorkerException(name);
            }

        }
    }
}
