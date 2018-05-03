package ru.nsu.ccfit.molochev.task1.MyExcepts;

public class AIPosException extends MainException {
    int x, y;

    private AIPosException(){}
    public AIPosException(int x_, int y_){
        x = x_;
        y = y_;
    }

    @Override
    public void print() {

    }
}
