package Classes.Exceptions;

import Classes.OS.Observable;
import Classes.OS.Subscriber;

public class NoSubException extends Exception {
    public NoSubException(Observable o, Subscriber s){
        super(o.toString() + " :no such subscriber (" + s.toString() + ")");
    }
}
