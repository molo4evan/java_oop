package MyGame.Model;

import javax.swing.*;
import java.io.File;
import java.util.*;

public class Theme {
    public class Question{
        private int value;
        private String theme;
        private ArrayList<String> text = new ArrayList<>();
        private String right_answer;
        private String wrong_answer;

        public int getValue() {
            return value;
        }

        public String getRight_answer() {
            return right_answer;
        }

        public String getWrong_answer() {
            return wrong_answer;
        }

        public ArrayList<String> getText() {
            return text;
        }

        public Question(File name, String th) throws Exception{
            value = Integer.parseInt(name.getName());
            try(Scanner scanner = new Scanner(name)){
                right_answer = scanner.nextLine();
                wrong_answer = scanner.nextLine();
                while (scanner.hasNextLine()){
                    text.add(scanner.nextLine());
                }
            }
            theme = th;
        }

        public boolean isCorrect(String answer){
            String ra = right_answer.toLowerCase();
            return ra.equals(answer.toLowerCase());
        }

        public String getTheme() {
            return theme;
        }
    }

    private ArrayList<Integer> values = new ArrayList<Integer>(){{
        add(100);
        add(200);
        add(300);
        add(400);
        add(500);
    }};

    private Map<Integer, Question> questions = new HashMap<>();
    private ImageIcon icon = null;
    private String name = null;

    public Theme(){}

    public Theme(String name) throws Exception{
        this.name = name;
        File folder = new File("themes/" + name);
        if (!folder.exists() || !folder.isDirectory()) throw new Exception();    //TODO: no dir

        File[] entries = folder.listFiles();
        for (File entry: entries){
            if (entry.isDirectory()) throw new Exception();

            if (entry.getName().equals("icon.png")) {
                icon = new ImageIcon(entry.getAbsolutePath());
                if (icon.getIconWidth() > 100 || icon.getIconHeight() > 100) throw new Exception(); //TODO: exc
            }
            else addQuestion(entry);
        }
    }

    public void setIcon(ImageIcon i){
        icon = i;
    }

    public void setName(String n){
        name = n;
    }

    public void setValues(int min, int max){
        ArrayList<Integer> tmp = new ArrayList<>();
        for (int i = min; i <= max; i += 100){
            tmp.add(i);
        }
        Collections.sort(tmp);
        values = tmp;
    }

    public void addQuestion(Question q, Integer v) throws Exception{
        if (!values.contains(v) || questions.containsKey(v))
            throw new Exception(); //TODO: add exc
        questions.put(v, q);
    }

    private void addQuestion(File quest)throws Exception{
        int val = Integer.parseInt(quest.getName());
        if (!values.contains(val) || questions.containsKey(val))
            throw new Exception(); //TODO: add exc
        questions.put(val, new Question(quest, this.getName()));
    }

    public String getName() {
        return name;
    }

    public ImageIcon getIcon(){
        return icon;
    }

    public ArrayList<Integer> getValues() {
        return values;
    }

    public int getMin(){
        return values.get(0);
    }

    public int getMax(){
        return values.get(values.size() - 1);
    }

    public int getStep(){
        if (values.size() < 2) return 1;
        return values.get(1) - values.get(0);
    }

    public Question getQuestion(int val)throws Exception{
        Question q = null;
        for (Integer i: values){
            if (i.equals(val)) {
                q = questions.get(i);
            }
        }
        if (q == null) throw new Exception(); //TODO: exc
        return q;
    }

    public int getQuestionAmount(){
        return questions.size();
    }

    public Question getQuestionByIndex(int i){
        return questions.get(getMin() + getStep() * i);
    }
}
