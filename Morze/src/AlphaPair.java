import java.util.HashMap;
import java.util.Map;

public class AlphaPair {
    private Map<Character, StringBuilder> toCode;
    private Map<String, Character> toDecode;

    public AlphaPair(){
        toCode = new HashMap<Character, StringBuilder>();
        toDecode = new HashMap<String, Character>();
    }

    public void put(Character c, StringBuilder s){
        toCode.put(c, s);
        toDecode.put(s.toString(), c);
    }

    public StringBuilder get(Character c){
        return toCode.get(c);
    }

    public Character get(StringBuilder s){
        return toDecode.get(s.toString());
    }
}
