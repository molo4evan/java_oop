import Exceptions.FileExcept;
import Exceptions.MyExcept;
import Exceptions.SymbolExcept;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Decode extends Worker {
    private Morze boss;

    public Decode(Morze boss_){
        boss = boss_;
    }

    private StringBuilder decode(String str, String alphaname) throws MyExcept{
        if (str.equals("")) return new StringBuilder("");

        AlphaPair alpha = boss.getAlpha(alphaname);
        StringBuilder out = new StringBuilder();

        String[] words = str.split("[ ]{3}");
        for (String s: words) {
            String[] letters = s.split(" ");
            for (String l: letters) {
                if (l.equals("")) continue;
                Character c = alpha.get(new StringBuilder(l));
                if (c == null) throw new SymbolExcept(l);
                out.append(c);
                boss.getStat().stat(c);
            }
            out.append(' ');
        }

        return out;
    }

    public void exec(String input)throws MyExcept{
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
            String str = scan.nextLine();
            StringBuilder decoded = decode(str, alpha.toString());
            decoded.append('\n');
            System.out.print(decoded);
            try { out.write(decoded.toString()); }
            catch (IOException e) { throw new FileExcept(outName); }
        }

        try { in.close(); }
        catch(IOException e){ throw new FileExcept(filename.toString()); }
        try { out.close(); }
        catch (IOException e){ throw new FileExcept(outName); }

        boss.getStat().flush(filename);
    }

}
