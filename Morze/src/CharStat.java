public class CharStat {
    private int count;
    private char c;

    public CharStat(char c_){
        c = c_;
        count = 1;
    }

    public void inc(){
        count++;
    }

    public int getCount() {
        return count;
    }

    public char getCharacter() {
        return c;
    }
}
