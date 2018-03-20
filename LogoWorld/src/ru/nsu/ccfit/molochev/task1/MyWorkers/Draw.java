package ru.nsu.ccfit.molochev.task1.MyWorkers;

import ru.nsu.ccfit.molochev.task1.Field;
import ru.nsu.ccfit.molochev.task1.MyExcepts.HandleExcept.ArgAmountException;
import ru.nsu.ccfit.molochev.task1.MyExcepts.HandleExcept.NoFieldException;

import java.util.ArrayList;

public class Draw implements Worker {
    @Override
    public Integer handle(ArrayList<String> args, Field f) throws NoFieldException, ArgAmountException {
        if (f == null) throw new NoFieldException();

        if (args.size() != 0) throw new ArgAmountException(args.size(), 0);

        f.draw();

        return null;
    }
}
