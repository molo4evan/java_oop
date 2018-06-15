package Classes.OS;

import Classes.Exceptions.NoSubException;

public interface Observable {
    void addSub(Subscriber sub);
    void removeSub(Subscriber sub) throws NoSubException;    //TODO: exc
    void notifySubs();
}
