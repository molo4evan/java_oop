package MyGame.View.MainWindows;

import MyGame.Controller.Controller;
import MyGame.Model.AI;
import MyGame.View.SideWindows.EndGameWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

class AIStepWindow extends JFrame {
    AIStepWindow(Controller ctrl, GameWindow game) throws Exception{
        super("AI turn");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        GridBagConstraints c = new GridBagConstraints();
        GridBagLayout gbl = new GridBagLayout();
        setLayout(gbl);

        c.fill = GridBagConstraints.NONE;
        c.gridheight = 1;
        c.weightx = 0.0;
        c.weighty = 0.0;
        c.insets = new Insets(20, 20, 20, 20);
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 0;
        c.gridy = GridBagConstraints.RELATIVE;

        AI.Answer answer = ctrl.doAIStep();

        JLabel ql = new JLabel("Question:");
        gbl.setConstraints(ql, c);
        add(ql);

        JTextArea q = new JTextArea(10, 40);
        q.setLineWrap(true);
        q.setBackground(getBackground());
        q.append("\n\n\n");
        for (String s: answer.question.getText()){
            q.append("           ");
            q.append(s);
            q.append("\n");
        }
        q.setEditable(false);
        gbl.setConstraints(q, c);
        add(q);

        JLabel a1 = new JLabel("AI answer:");
        JLabel a2 = new JLabel(answer.answer);
        gbl.setConstraints(a1, c);
        add(a1);
        gbl.setConstraints(a2, c);
        add(a2);

        if (answer.right){
            JLabel l = new JLabel("It's right.");
            gbl.setConstraints(l ,c);
            add(l);
        }
        else {
            JLabel l1 = new JLabel("It's wrong.");
            gbl.setConstraints(l1 ,c);
            add(l1);
            JLabel l2 = new JLabel("Correct answer is: " + answer.question.getRight_answer());
            gbl.setConstraints(l2 ,c);
            add(l2);
        }

        game.update();

        q.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {
                if (ctrl.checkEnd()) {
                    game.dispose();
                    new EndGameWindow(ctrl);
                }
                else game.setVisible(true);
                dispose();
            }
        });

        pack();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = this.getSize();
        setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
        setVisible(true);
    }
}
