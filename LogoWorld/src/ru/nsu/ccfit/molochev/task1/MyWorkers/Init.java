package ru.nsu.ccfit.molochev.task1.MyWorkers;

import ru.nsu.ccfit.molochev.task1.Field;
import ru.nsu.ccfit.molochev.task1.MyExcepts.HandleExcept.ArgAmountException;
import ru.nsu.ccfit.molochev.task1.MyExcepts.HandleExcept.InitException;
import ru.nsu.ccfit.molochev.task1.MyExcepts.HandleExcept.NoFieldException;

import java.util.ArrayList;

public class Init implements Worker {
    @Override
    public Integer handle(ArrayList<String> args, Field f) throws InitException, NoFieldException, ArgAmountException {
        if (f == null) throw new NoFieldException();

        if (args.size() != 4) throw new ArgAmountException(args.size(), 4);

        int x, y, a, b;
        try{
            x = Integer.parseInt(args.get(0));
            y = Integer.parseInt(args.get(1));
            a = Integer.parseInt(args.get(2));
            b = Integer.parseInt(args.get(3));
        }
        catch (NumberFormatException e){
            throw new InitException(InitException.Situations.ArgFormat, args);
        }
        if (x < 0 || y < 0) throw new InitException(InitException.Situations.IncSize, args);
        if (a < 0 || b < 0) throw new InitException(InitException.Situations.IncPos, args);

        a %= x;
        b %= y;

        f.assign(x, y, a, b);

        return null;
    }
}
