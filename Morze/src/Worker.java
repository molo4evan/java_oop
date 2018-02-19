import Exceptions.MyExcept;

import static java.lang.Character.isSpaceChar;

public abstract class Worker {

    public abstract void exec(String filename) throws MyExcept;

}

class ArgParser{
    public void parse(String str, StringBuilder alpha, StringBuilder filename){
        int i = 0, m;

        while (i < str.length() && isSpaceChar(str.charAt(i))) {
            i++;
        }
        m = i;

        while (i < str.length() && !isSpaceChar(str.charAt(i))) {
            i++;
        }
        alpha.append(str.substring(m, i));

        while (i < str.length() && isSpaceChar(str.charAt(i))) {
            i++;
        }
        m = i;

        while (i < str.length() && !isSpaceChar(str.charAt(i))) {
            i++;
        }
        filename.append(str.substring(m, i));
    }
}