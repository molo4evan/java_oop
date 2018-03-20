package ru.nsu.ccfit.molochev.task1.MyExcepts.HandleExcept;

public class MoveException extends HandleException {
    public enum Situations{WrongDir, WrongStepsFormat}

    private Situations cause;
    private String mistake;

    public MoveException(Situations sit, String mist){
        cause = sit;
        mistake = mist;
    }
    private MoveException(){}

    @Override
    public void print() {

    }

}
