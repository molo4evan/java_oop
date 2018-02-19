package Exceptions;

public class FileExcept extends MyExcept{
    private String filename;

    public FileExcept(String fn){filename = fn;}
    private FileExcept(){}

    @Override
    public void err_msg() {
        System.err.println("ERROR: smth wrong with file " + filename);
    }
}
