import ru.nsu.ccfit.molochev.task1.Field;

public class Psvm {
    public static void main(String[] args) {
        Field f = new Field();
        f.assign(10, 10, 3, 5);
        f.print();
        f.draw();
        f.setPos(0,0);
        f.setPos(0,1);
        f.setPos(1,1);
        f.undraw();
        f.setPos(1,2);
        f.setPos(2,3);
        f.print();

    }
}
