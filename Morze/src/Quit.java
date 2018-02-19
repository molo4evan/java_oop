import Exceptions.MyExcept;
import Exceptions.QuitExcept;

public class Quit extends Worker {
    private Morze boss;

    public Quit(Morze boss_){
        boss = boss_;
    }

    public void exec(String filename) throws MyExcept {
        boss.getStat().drop();
        throw new QuitExcept();
    }
}
