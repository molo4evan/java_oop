package MyGame.Model;

import java.util.HashMap;
import java.util.Random;

import static MyGame.Model.AI.Difficulty.*;

public class AI extends Player{
    public static class Answer{
        public boolean right;
        public Theme.Question question;
        public String answer;

        public Answer(boolean r, Theme.Question q, String a){
            right = r;
            question = q;
            answer = a;
        }
    }

    public enum Difficulty{Easy, Medium, Hard, Insane}

    private static HashMap persentage = new HashMap<Difficulty, Integer>(){{
        put(Easy, 25);
        put(Medium, 50);
        put(Hard, 75);
        put(Insane, 100);
    }};
    private Difficulty mode;

    AI(Difficulty m){
        super("AI");
        mode = m;
    }

    public String answer(Theme.Question q){
        int my_chance = (Integer) persentage.get(mode);
        int fortune = new Random().nextInt(100);

        boolean truth = fortune < my_chance;
        if (truth) return q.getRight_answer();
        else return q.getWrong_answer();
    }
}
