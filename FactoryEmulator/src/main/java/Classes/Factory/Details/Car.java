package Classes.Factory.Details;

public class Car extends Detail{
    private Accessory a;
    private Motor m;
    private Body b;

    public Car(Accessory a, Motor m, Body b){
        this.a = a;
        this.m = m;
        this.b = b;
        super.ID = a.getID() + b.getID() + m.getID();
    }

    public Accessory getAccessory() {
        return a;
    }

    public Body getBody() {
        return b;
    }

    public Motor getMotor() {
        return m;
    }
}
