package MyGame.View.MainWindows;

import MyGame.Controller.Controller;
import MyGame.Model.Theme;
import MyGame.View.ErrorWindow;
import MyGame.View.SideWindows.EndGameWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

 class ResultWindow extends JFrame{
    ResultWindow(Controller ctrl, Theme.Question q, Boolean corr, GameWindow game) throws Exception{
        super("Result");
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
        c.gridy = 0;

        game.update();

        if (corr){
            JLabel l = new JLabel("Congrads, u r right!");
            gbl.setConstraints(l ,c);
            add(l);
        }
        else {
            JLabel l1 = new JLabel("Sorry, but u r wrong...");
            gbl.setConstraints(l1 ,c);
            add(l1);
            c.gridy = 1;
            JLabel l2 = new JLabel("Correct answer is: " + q.getRight_answer());
            gbl.setConstraints(l2 ,c);
            add(l2);
        }

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {
                ctrl.setAnswering(false);
                try {
                    if (ctrl.checkEnd()) {
                        new EndGameWindow(ctrl);
                        game.dispose();
                    }
                    else if (ctrl.isSingleMode()) new AIStepWindow(ctrl, game);   //TODO: error sometimes, i think
                    else game.setVisible(true);
                }
                catch (Exception e){
                    new ErrorWindow(e);
                    game.dispose();
                }
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
