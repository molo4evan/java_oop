import Exceptions.*;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;

import static java.lang.Character.isSpaceChar;

public class Morze {
    private Map<String, AlphaPair> alphabets = new HashMap<>();
    private Map<String, Worker> workers = null;
    private Statistics stat;

    private void initWorkers(){
        if (workers != null) return;
        workers = new HashMap<>();
        workers.put("code", new Code(this));
        workers.put("decode", new Decode(this));
        workers.put("quit", new Quit(this));
    }

    private void makeAlphabet(FileReader file, String name){
        Scanner scan = new Scanner(file);
        AlphaPair out = new AlphaPair();

        while (scan.hasNextLine()){
            StringBuilder str = new StringBuilder(scan.nextLine());
            char sign = str.charAt(0);
            StringBuilder cypher = new StringBuilder(str.substring(2));
            out.put(sign, cypher);
        }

        int i = name.indexOf('.');
        alphabets.put(name.substring(0, i), out);
    }

    public AlphaPair getAlpha(String s) throws AlphaExcept{
        AlphaPair alpha = alphabets.get(s);
        if (alpha == null) throw new AlphaExcept(s);
        return alpha;
    }

    public Statistics getStat(){return stat;}

    public Morze(String alphapath) throws IOException {
        File alphas = new File(alphapath);
        File[] alphaList = alphas.listFiles();
        for (File f: alphaList){
            FileReader alpha = new FileReader(f);
            makeAlphabet(alpha, f.getName());
            alpha.close();
        }

        initWorkers();
        try{stat = new Statistics();}
        catch (MyExcept e){
            e.err_msg();
            throw new IOException();
        }
    }

    private Morze(){}

    public void handle() {
        Scanner scan = new Scanner(System.in);

        while (true) {
            StringBuilder str = new StringBuilder(scan.nextLine());

            int i = 0, m = 0;

            while (i < str.length() && isSpaceChar(str.charAt(i))) {
                i++;
            }
            m = i;

            while (i < str.length() && !isSpaceChar(str.charAt(i))) {
                i++;
            }
            String com = str.substring(m, i);

            while (i < str.length() && isSpaceChar(str.charAt(i))) {
                i++;
            }
            m = i;
            StringBuilder arg = new StringBuilder(str.substring(m, str.length()));

            Worker w = workers.get(com);
            if (w == null) {
                System.out.println("Can't resolve worker: " + com);
                continue;
            }
            if (arg.length() == 0 && !com.equals("quit")){
                System.out.println("ERROR: no input file.");
                continue;
            }

            try {
                w.exec(arg.toString());
            }
            catch (QuitExcept exc){return;}
            catch (MyExcept exc){
                exc.err_msg();
            }
        }
    }
}
