package Classes.Factory.Details;

public abstract class Detail {
    protected int ID;

    public Detail(){}

    public Detail(int id){
        ID = id;
    }

    public int getID(){
        return ID;
    }
}
