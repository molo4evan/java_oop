package ru.nsu.ccfit.molochev.task1.MyWorkers;

import ru.nsu.ccfit.molochev.task1.Field;
import ru.nsu.ccfit.molochev.task1.MyExcepts.HandleExcept.HandleException;

import java.util.ArrayList;

public interface Worker {
    Integer handle(ArrayList<String> args, Field f) throws HandleException;
}
