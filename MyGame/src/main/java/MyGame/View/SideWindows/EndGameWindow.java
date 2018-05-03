package MyGame.View.SideWindows;

import MyGame.Controller.Controller;
import MyGame.View.ErrorWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class EndGameWindow extends JFrame{
    public EndGameWindow(Controller ctrl){
        super("Game over!");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        GridBagLayout gbl = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        getContentPane().setLayout(gbl);
        c.gridx = 0;
        c.gridy = GridBagConstraints.RELATIVE;
        c.anchor = GridBagConstraints.CENTER;

        String winner = ctrl.getLeader();
        JLabel wl = new JLabel(winner + " win!");
        wl.setFont(new Font(Font.SERIF, Font.PLAIN, 30));
        wl.setBorder(BorderFactory.createEmptyBorder(15, 30, 15, 30));
        gbl.setConstraints(wl, c);
        add(wl);

        if (!winner.equals("Nobody")) {
            JLabel sc = new JLabel();
            try {
                sc.setText("Total score: " + ctrl.getPlayerDeposit(winner));
            } catch (Exception e) {
                new ErrorWindow(e);
                dispose();
            }
            sc.setFont(new Font(Font.SERIF, Font.PLAIN, 30));
            sc.setBorder(BorderFactory.createEmptyBorder(15, 30, 15, 30));
            gbl.setConstraints(sc, c);
            add(sc);
        }

        ctrl.reset();

        pack();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = this.getSize();
        setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
        setVisible(true);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {
                new MainMenu(ctrl);
                dispose();
            }
        });
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                new MainMenu(ctrl);
                dispose();
            }
        });
    }
}
