package Exceptions;

public class SymbolExcept extends MyExcept{
    private String s;

    public SymbolExcept(String s_){s = s_;}
    private SymbolExcept(){}

    @Override
    public void err_msg() {
        System.err.println("ERROR: can't resolve symbol:\n" + s);
    }
}
