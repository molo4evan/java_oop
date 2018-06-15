package Classes.Factory.Details;

public class Accessory extends Detail {
    public Accessory(Integer supplyID, Integer localID){
        super(Integer.parseInt(supplyID.toString() + localID.toString()));  //TODO: hash?
    }
}
