package Exceptions;

public class AlphaExcept extends MyExcept{
    private String es;

    public AlphaExcept(String s){es = s;}
    private AlphaExcept(){}

    @Override
    public void err_msg() {
        System.err.println("ERROR: alphabet \"" + es + "\" does not exisits");
    }
}
