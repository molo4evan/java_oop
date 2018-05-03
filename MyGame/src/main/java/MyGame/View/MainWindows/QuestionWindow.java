package MyGame.View.MainWindows;

import MyGame.Controller.Controller;
import MyGame.Model.Theme;
import MyGame.View.ErrorWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class QuestionWindow extends JFrame {
    public QuestionWindow(Theme.Question q, Controller ctrl, GameWindow game){
        super(((Integer)q.getValue()).toString());

        game.setVisible(false);

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        GridBagLayout gbl = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        setLayout(gbl);

        c.fill = GridBagConstraints.NONE;
        c.gridheight = 1;
        c.weightx = 0.0;
        c.weighty = 0.0;
        c.insets = new Insets(0, 0, 0, 0);

        JTextArea area = new JTextArea(10, 40);
        area.setLineWrap(true);
        area.setBackground(getBackground());
        area.append("\n\n\n");
        for (String s: q.getText()){
            area.append("           ");
            area.append(s);
            area.append("\n");
        }
        area.setEditable(false);
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 0;
        c.gridy = 0;
        gbl.setConstraints(area, c);
        add(area);

        JTextField ans = new JTextField(40);
        ans.requestFocus();
        c.gridwidth = 4;
        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 0;
        c.gridy = 1;
        gbl.setConstraints(ans, c);
        add(ans);

        ans.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {
                if (keyEvent.getKeyChar() == KeyEvent.VK_ENTER){
                    boolean correct;
                    try {
                        correct = ctrl.processAnswer("You", ans.getText());
                        new ResultWindow(ctrl, q, correct, game);
                    }
                    catch (Exception e){
                        new ErrorWindow(e);
                        dispose();
                        game.dispose();
                        return;
                    }
                    dispose();
                }
            }
        });

        JButton sub = new JButton("Answer");
        c.gridwidth = 1;
        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 4;
        c.gridy = 1;
        gbl.setConstraints(sub, c);
        add(sub);

        sub.addActionListener(actionEvent -> {
            boolean correct;
            try {
                correct = ctrl.processAnswer("You", ans.getText());
                new ResultWindow(ctrl, q, correct, game);
            }
            catch (Exception e){
                new ErrorWindow(e);
                dispose();
                game.dispose();
                return;
            }
            dispose();
        });

        ctrl.setAnswering(true);

        pack();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = this.getSize();
        setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
        setVisible(true);
    }
}
