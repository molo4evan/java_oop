package MyGame.View.MainWindows.Questions;

import MyGame.Controller.Controller;
import MyGame.Model.Theme;
import MyGame.View.Subscriber;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Questions extends JPanel implements Subscriber {
    private Controller ctrl;
    private ArrayList<ArrayList<JButton>> quests = new ArrayList<>();

    public Questions(Controller c, JFrame base) {
        ctrl = c;
        //c.addSubscriber(this);
        ArrayList<Theme> themes = c.getThemes();
        int amount = c.getQuestionAmount();
        setLayout(new GridLayout(themes.size(), amount + 1, 10, 10));

        for (int i = 0; i < themes.size(); i++){
            quests.add(i, new ArrayList<>());
            Theme cur = themes.get(i);
            int k = 0;
            JLabel jl = new JLabel(cur.getName());      //TODO: drawing
            jl.setHorizontalTextPosition(SwingConstants.CENTER);
            add(jl);
            QuestionListener ql = new QuestionListener();
            for (Integer j = cur.getMin(); j <= cur.getMax(); j+= cur.getStep()){
                QButton jb = new QButton(cur.getIcon(), cur.getName(), j.toString(), c, base);
                jb.addActionListener(ql);
                jb.addKeyListener(ql);
                quests.get(i).add(k++, jb);
                add(jb);
            }
        }
    }

    @Override
    public void update() {
        ArrayList<ArrayList<Boolean>> aval = ctrl.getAvaliableQuestions();
        for (int i = 0; i < aval.size(); i++){
            for (int j = 0; j < aval.get(i).size(); j++){
                quests.get(i).get(j).setVisible(aval.get(i).get(j));
                quests.get(i).get(j).setEnabled(aval.get(i).get(j));
            }
        }
    }
}

