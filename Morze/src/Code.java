import Exceptions.FileExcept;
import Exceptions.MyExcept;
import Exceptions.SymbolExcept;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import static java.lang.Character.isLowerCase;
import static java.lang.Character.toUpperCase;

public class Code extends Worker {
    private Morze boss;

    public Code(Morze boss_){
        boss = boss_;
    }

    private StringBuilder code(Character c, StringBuilder alphaname) throws MyExcept{
        if (c == ' ' || c == '\t') return new StringBuilder("   ");

        AlphaPair alpha = boss.getAlpha(alphaname.toString());

        if (isLowerCase(c)) c = toUpperCase(c);
        if (c == 'Ё') c = 'Е';

        StringBuilder my_s = alpha.get(c);
        if (my_s == null) throw new SymbolExcept(c.toString());
        StringBuilder s = new StringBuilder(my_s);
        s.append(' ');
        return s;
    }

    public void exec(String input) throws MyExcept{
        FileReader in;
        FileWriter out;
        ArgParser ap = new ArgParser();
        StringBuilder alpha = new StringBuilder();
        StringBuilder filename = new StringBuilder();
        ap.parse(input, alpha, filename);

        try { in = new FileReader(filename.toString()); }
        catch(IOException e){ throw new FileExcept(filename.toString()); }
        String outName = "out_from_" + filename.toString();
        try { out = new FileWriter(outName, false); }
        catch (IOException e){ throw new FileExcept(outName); }
        Scanner scan = new Scanner(in);

        while (scan.hasNextLine()){
            StringBuilder str = new StringBuilder(scan.nextLine());
            StringBuilder coded = new StringBuilder();
            if (str.toString().equals("")) coded.append("");
            else{
                int i = 0;
                while (i < str.length()) {
                    char c = str.charAt(i++);
                    StringBuilder out_str = code(c, alpha);
                    coded.append(out_str);
                    boss.getStat().stat(c);
                }
            }

            coded.append('\n');
            System.out.print(coded);
            try { out.write(coded.toString()); }
            catch (IOException e) { throw new FileExcept(outName); }
        }

        try { in.close(); }
        catch(IOException e){ throw new FileExcept(filename.toString()); }
        try { out.close(); }
        catch (IOException e){ throw new FileExcept(outName); }

        boss.getStat().flush(filename);
    }
}
