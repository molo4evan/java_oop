import Exceptions.FileExcept;
import Exceptions.MyExcept;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Statistics {
    private Set<CharStat> char_stat = new HashSet<>();
    private FileWriter my_stat;

    public Statistics() throws MyExcept{
        try{ my_stat = new FileWriter("statistics.txt", false); }
        catch (IOException e) {
            throw new FileExcept("statistics.txt");
        }
    }

    public void stat(char c){
        if (!char_stat.add(new CharStat(c))){
            for (CharStat o : char_stat) {
                if (o.getCharacter() == c) {
                    o.inc();
                    break;
                }
            }
        }
    }

    public void flush(StringBuilder name) throws MyExcept{
        try{ my_stat.write("\n\n\n" + name.toString() + ":\n"); }
        catch (IOException e) { throw new FileExcept("statistics.txt"); }

        for (CharStat o: char_stat){
            String s = "\n\'" + o.getCharacter() + "\': " + o.getCount();
            try{ my_stat.write(s); }
            catch (IOException e) { throw new FileExcept("statistics.txt"); }
        }
    }

    public void drop() {
        try {my_stat.close();}
        catch (IOException e){
            System.err.println("ERROR: can't close file \"statistics.txt\"");
        }
    }
}
