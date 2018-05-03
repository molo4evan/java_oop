package MyGame.Model;

public class Player {
    final private String name;
    private int deposit = 0;

    public Player(String str){
        name = str;
    }

    public int getDeposit(){
        return deposit;
    }

    public void setDeposit(int value){
        deposit = value;
    }

    public String getName(){
        return name;
    }
}
