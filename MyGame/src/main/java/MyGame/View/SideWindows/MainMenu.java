package MyGame.View.SideWindows;

import MyGame.Controller.Controller;
import MyGame.View.ViewFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class MainMenu extends ViewFrame {

    private JButton start;
    private JButton settings;
    private JButton credits;
    private JButton exit;

    public MainMenu(Controller ctrl){
        super("Main Menu", ctrl);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        GridBagLayout gbl = new GridBagLayout();
        setLayout(gbl);
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.CENTER;
        c.fill = GridBagConstraints.NONE;
        c.gridheight = 1;
        c.gridwidth  = GridBagConstraints.REMAINDER;
        c.gridx = GridBagConstraints.RELATIVE;
        c.gridy = GridBagConstraints.RELATIVE;
        c.weightx = 0.0;
        c.weighty = 0.0;
        c.insets = new Insets(3, 0, 3, 0);
        c.ipady = 5;

        start = new JButton("Start");
        c.ipadx = 174;
        gbl.setConstraints(start, c);
        add(start);

        settings = new JButton("Settings");
        c.ipadx = 150;
        gbl.setConstraints(settings, c);
        //add(settings);                        //TODO: unused button

        credits = new JButton("Information");
        c.ipadx = 128;
        gbl.setConstraints(credits, c);
        add(credits);

        exit = new JButton("Exit");
        c.ipadx = 185;
        gbl.setConstraints(exit, c);
        add(exit);

        setBehaviour();

        setMinimumSize(new Dimension(500, 400));

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = this.getSize();
        setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
        setResizable(false);
        setVisible(true);
    }

    private void setBehaviour(){
        start.addActionListener(actionEvent -> {
            new SetUpTheGame(ctrl);
            dispose();
        });

        start.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {
                if (keyEvent.getKeyChar() == KeyEvent.VK_ESCAPE) dispose();
                if (keyEvent.getKeyChar() == KeyEvent.VK_ENTER){
                    new SetUpTheGame(ctrl);
                    dispose();
                }
            }
        });

        credits.addActionListener(actionEvent -> {
            new InfoWindow();
        });

        credits.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {
                if (keyEvent.getKeyChar() == KeyEvent.VK_ESCAPE) dispose();
                if (keyEvent.getKeyChar() == KeyEvent.VK_ENTER){
                    new InfoWindow();
                }
            }
        });

        exit.addActionListener(actionEvent -> {
            dispose();
        });

        exit.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {
                if (keyEvent.getKeyChar() == KeyEvent.VK_ESCAPE ||
                    keyEvent.getKeyChar() == KeyEvent.VK_ENTER) {
                    dispose();
                }
            }
        });

        /*addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {
                if (keyEvent.getKeyChar() == KeyEvent.VK_ESCAPE) dispose();
            }
        });*/
    }
}
