package MyGame.View.MainWindows;

import MyGame.Controller.Controller;
import MyGame.View.MainWindows.Players.Players;
import MyGame.View.MainWindows.Questions.Questions;
import MyGame.View.Subscriber;
import MyGame.View.ViewFrame;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends ViewFrame implements Subscriber {
    Questions quests;
    Players pls;
    public GameWindow(Controller ctrl){
        super("My Game", ctrl);

        JPanel p = new JPanel();

        GridBagConstraints c = new GridBagConstraints();
        GridBagLayout gbl = new GridBagLayout();
        p.setLayout(gbl);              // other?
        c.insets = new Insets(10, 10, 10, 10);
        c.gridx = 0;
        c.gridy = GridBagConstraints.RELATIVE;

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        quests = new Questions(ctrl, this);
        gbl.setConstraints(quests, c);
        p.add(quests);

        pls = new Players(ctrl);
        gbl.setConstraints(pls, c);
        p.add(pls);

        ctrl.addSubscriber(this);

        JScrollPane sp = new JScrollPane(p);
        sp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        setContentPane(sp);

        //setBounds(400, 200, 500, 400);  //what to do with sizes?
        pack();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = this.getSize();
        setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
        //setMinimumSize(getSize());
        setResizable(true);
        setVisible(true);
    }

    @Override
    public void update() throws Exception{
        quests.update();
        pls.update();
    }
}

