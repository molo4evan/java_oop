package ru.nsu.ccfit.molochev.task1;

import ru.nsu.ccfit.molochev.task1.MyExcepts.*;
import ru.nsu.ccfit.molochev.task1.MyExcepts.HandleExcept.JmpException;
import ru.nsu.ccfit.molochev.task1.MyExcepts.HandleExcept.UnknownException;
import ru.nsu.ccfit.molochev.task1.MyExcepts.InitExistExcept.InitScriptException;
import ru.nsu.ccfit.molochev.task1.MyExcepts.WorkerExcept.WrongWorkerException;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

class ScriptHandler {
    private Field field = new Field();

    void handleScript(String scriptname) throws MainException, IOException{
        ArrayList<String> script = new ArrayList<>
                (Files.lines(Paths.get(scriptname)).collect(Collectors.toList()));

        Pattern splitter = Pattern.compile("\\s+");

        String[] words = splitter.split(script.get(0));
            if (words.length == 0 || words[0] != "INIT") throw new InitScriptException(scriptname);
            else {
                reflectiveHandle(words, field);
            }

            int i = 1;
            while (i < script.size()) {
                words = splitter.split(script.get(i++));
                Object ret = reflectiveHandle(words, field);
                if (words[0].equals("ru.nsu.ccfit.molochev.task1.MyWorkers.IfJmp")) {
                    if (ret != null){
                        i = (Integer)ret;
                        if (i > script.size() || i < 0) throw new JmpException(JmpException.Cause.number, i);
                    }
                }
            }
    }

    private Object reflectiveHandle(String[] words, Field field) throws MainException {
        Object worker = Fabric.getInstance().getWorker(words[0]);

        ArrayList<String> args = new ArrayList<>(
                Arrays.asList(
                        Arrays.copyOfRange(words, 1, words.length)
                )
        );
        try{
            return worker.getClass().getMethod(
                    "handle",
                    Class.forName("java.util.ArrayList"),
                    Class.forName("Field")).invoke(worker, args, field);
        }
        catch (ClassNotFoundException e){
            System.out.println("Path is not full, i think");
        }
        catch(NoSuchMethodException |IllegalAccessException e){
            throw new WrongWorkerException(words[0]);
        }
        catch(InvocationTargetException e){
            try {
                if (Class.forName("ru.nsu.ccfit.molochev.task1.MyExcepts.MainException").
                        isAssignableFrom(e.getCause().getClass())){
                    throw (MainException) e.getCause();
                }
                else throw new UnknownException(e.getCause());
            }
            catch (ClassNotFoundException einner){
                System.out.println("Path is not full, i think");
            }
        }
        return null;
    }
}
