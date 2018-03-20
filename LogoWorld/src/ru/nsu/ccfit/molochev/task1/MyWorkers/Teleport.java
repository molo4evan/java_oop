package ru.nsu.ccfit.molochev.task1.MyWorkers;

import ru.nsu.ccfit.molochev.task1.Field;
import ru.nsu.ccfit.molochev.task1.MyExcepts.HandleExcept.ArgAmountException;
import ru.nsu.ccfit.molochev.task1.MyExcepts.HandleExcept.NoFieldException;
import ru.nsu.ccfit.molochev.task1.MyExcepts.HandleExcept.TpException;

import java.util.ArrayList;

public class Teleport implements Worker {
    @Override
    public Integer handle(ArrayList<String> args, Field f) throws NoFieldException, ArgAmountException, TpException {
        if (f == null) throw new NoFieldException();

        if (args.size() != 2) throw new ArgAmountException(args.size(), 2);

        Field.Pair size = f.getSize();

        int x, y;
        try{
            x = Integer.parseInt(args.get(0));
        }
        catch (NumberFormatException e){
            throw new TpException(args.get(0));
        }
        try{
            y = Integer.parseInt(args.get(1));
        }
        catch (NumberFormatException e){
            throw new TpException(args.get(1));
        }
        if (x < 0 || x > size.getX()) throw new TpException(args.get(0));
        if (y < 0 || y > size.getY()) throw new TpException(args.get(1));

        f.setAI_pos(x, y);

        return null;
    }
}
