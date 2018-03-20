package ru.nsu.ccfit.molochev.task1.MyWorkers;

import ru.nsu.ccfit.molochev.task1.Field;
import ru.nsu.ccfit.molochev.task1.MyExcepts.HandleExcept.ArgAmountException;
import ru.nsu.ccfit.molochev.task1.MyExcepts.HandleExcept.JmpException;
import ru.nsu.ccfit.molochev.task1.MyExcepts.HandleExcept.NoFieldException;

import java.util.ArrayList;

public class IfJmp implements Worker {
    @Override
    public Integer handle(ArrayList<String> args, Field f) throws NoFieldException, ArgAmountException, JmpException{
        if (f == null) throw new NoFieldException();

        if (args.size() != 2) throw new ArgAmountException(args.size(), 2);

        Integer next;
        try{
        next = Integer.parseInt(args.get(1));
        }
        catch (NumberFormatException e){
            throw new JmpException(JmpException.Cause.number, args.get(1));
        }
        if (next < 0) throw new JmpException(JmpException.Cause.number, args.get(1));

        switch (args.get(0)){
            case "NoCond":
                return next;
            default:
                throw new JmpException(JmpException.Cause.condFormat, args.get(0));
        }
    }
}
