package ru.nsu.ccfit.molochev.task1.MyWorkers;

import ru.nsu.ccfit.molochev.task1.Field;
import ru.nsu.ccfit.molochev.task1.MyExcepts.HandleExcept.ArgAmountException;
import ru.nsu.ccfit.molochev.task1.MyExcepts.HandleExcept.MoveException;
import ru.nsu.ccfit.molochev.task1.MyExcepts.HandleExcept.NoFieldException;

import java.util.ArrayList;

public class Move implements Worker {
    @Override
    public Integer handle(ArrayList<String> args, Field f) throws MoveException, NoFieldException, ArgAmountException{
        if (f == null) throw new NoFieldException();

        if (args.size() != 2) throw new ArgAmountException(args.size(), 2);

        int steps;
        try{
            steps = Integer.parseInt(args.get(1));
        }
        catch (NumberFormatException e){
            throw new MoveException(MoveException.Situations.WrongStepsFormat, args.get(1));
        }

        Field.Pair ai = f.getAI_pos();
        Field.Pair size = f.getSize();
        switch(args.get(0)){
            case "L":
                steps = ai.getX() - steps;
                while (steps < 0) steps += size.getX();
                f.setAI_pos(steps % size.getX(), size.getY());
                break;
            case "R":
                steps = ai.getX() + steps;
                while (steps < 0) steps += size.getX();
                f.setAI_pos(steps % size.getX(), size.getY());
                break;
            case "U":
                steps = ai.getY() - steps;
                while (steps < 0) steps += size.getY();
                f.setAI_pos(size.getX(), steps % size.getY());
                break;
            case "D":
                steps = ai.getY() + steps;
                while (steps < 0) steps += size.getY();
                f.setAI_pos(size.getX(), steps % size.getY());
                break;
            default:
                throw new MoveException(MoveException.Situations.WrongDir, args.get(0));
        }

        return null;
    }
}
