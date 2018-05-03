package MyGame.View.SideWindows;

import MyGame.Controller.Controller;
import MyGame.Model.AI;
import MyGame.View.ErrorWindow;
import MyGame.View.MainWindows.GameWindow;
import MyGame.View.ViewFrame;
import MyGame.View.WarningWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Vector;

public class SetUpTheGame extends ViewFrame {
    private JList<String> themes;
    private JComboBox<Integer> lborder;
    private JComboBox<Integer> mborder;
    private JRadioButton e;
    private JRadioButton m;
    private JRadioButton h;
    private JRadioButton i;

    public SetUpTheGame(Controller ctrl){
        super("Choose game settings", ctrl);

        GridBagLayout gbl = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(gbl);

        c.fill = GridBagConstraints.NONE;
        c.gridheight = 1;
        c.weightx = 0.0;
        c.weighty = 0.0;


        JLabel header = new JLabel("Set up your game");
        header.setFont(new Font("Arial", Font.PLAIN, 20));
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(30, 0, 30, 0);
        gbl.setConstraints(header, c);
        add(header);

        JLabel sd = new JLabel("Select difficulty");
        c.insets = new Insets(0, 0, 10, 0);
        c.gridy = 1;
        gbl.setConstraints(sd, c);
        add(sd);

        ButtonGroup group = new ButtonGroup();
        e = new JRadioButton("Easy", false);
        group.add(e);
        m = new JRadioButton("Medium", true);
        group.add(m);
        h = new JRadioButton("Hard", false);
        group.add(h);
        i = new JRadioButton("Insane", false);
        group.add(i);

        c.gridwidth = 1;
        c.gridy = 2;
        c.insets = new Insets(0, 70, 20, 0);
        gbl.setConstraints(e, c);
        add(e);
        c.insets = new Insets(0, 0, 20, 0);
        c.gridx = GridBagConstraints.RELATIVE;
        gbl.setConstraints(m, c);
        add(m);
        gbl.setConstraints(h, c);
        add(h);
        gbl.setConstraints(i, c);
        add(i);

        JLabel ct = new JLabel("Choose your topics: ");
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 3;
        c.anchor = GridBagConstraints.EAST;
        gbl.setConstraints(ct, c);
        add(ct);

        themes = new JList<>(new Vector<>(ctrl.getThemeNames()));
        themes.setLayoutOrientation(JList.VERTICAL);
        themes.setVisibleRowCount(3);
        JScrollPane sp = new JScrollPane(themes);
        sp.setMinimumSize(new Dimension(200, 70));
        c.gridx = 3;
        c.anchor = GridBagConstraints.WEST;
        gbl.setConstraints(sp, c);
        add(sp);

        JLabel entVal = new JLabel("Choose the values of questions");
        c.insets = new Insets(0, 0, 5, 0);
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.gridy = 4;
        c.gridx = 0;
        c.anchor = GridBagConstraints.CENTER;
        gbl.setConstraints(entVal, c);
        add(entVal);

        Integer[] vals = {100, 200, 300, 400, 500};

        JLabel f = new JLabel("From: ");
        c.gridwidth = 2;
        c.gridy = 5;
        c.anchor = GridBagConstraints.EAST;
        gbl.setConstraints(f, c);
        add(f);

        lborder = new JComboBox<>(vals);
        c.gridx = 3;
        c.anchor = GridBagConstraints.WEST;
        gbl.setConstraints(lborder, c);
        add(lborder);

        JLabel t = new JLabel("To: ");
        c.insets = new Insets(0, 0, 40, 0);
        c.gridy = 6;
        c.gridx = 0;
        c.anchor = GridBagConstraints.EAST;
        gbl.setConstraints(t, c);
        add(t);

        mborder = new JComboBox<>(vals);
        mborder.setSelectedIndex(vals.length - 1);
        c.gridx = 3;
        c.anchor = GridBagConstraints.WEST;
        gbl.setConstraints(mborder, c);
        add(mborder);

        JButton sub = new JButton("Submit");
        c.ipadx = 50;
        c.gridy = 7;
        c.gridx = 0;
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.anchor = GridBagConstraints.CENTER;
        gbl.setConstraints(sub, c);
        add(sub);

        sub.addActionListener(actionEvent -> {
            try{
                setUpAndStart();
            }
            catch (Exception exc){
                new ErrorWindow(exc);
                dispose();
            }
         });

        sub.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {
                if (keyEvent.getKeyChar() == KeyEvent.VK_ESCAPE) dispose();
                if (keyEvent.getKeyChar() == KeyEvent.VK_ENTER){
                    try{
                        setUpAndStart();
                    }
                    catch (Exception e){
                        new ErrorWindow(e);
                        dispose();
                    }
                }
            }
        });

        setMinimumSize(new Dimension(500, 400));

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = this.getSize();
        setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
        setResizable(false);
        setVisible(true);
    }

    private void setUpAndStart() throws Exception{
        ArrayList<String> th = new ArrayList<>(themes.getSelectedValuesList());
        if (th.isEmpty()){
            new WarningWindow("Choose at least one theme", this);
            return;
        }
        int min = (Integer)lborder.getSelectedItem();
        int max = (Integer)mborder.getSelectedItem();
        if (min > max){
            new WarningWindow("Minimum can't be more than maximum", this);
            return;
        }

        AI.Difficulty mode;

        if (e.isSelected()) mode = AI.Difficulty.Easy;
        else if (m.isSelected()) mode = AI.Difficulty.Medium;
        else if (h.isSelected()) mode = AI.Difficulty.Hard;
        else mode = AI.Difficulty.Insane;

        ctrl.startGame(null, th, min, max, mode);
        new GameWindow(ctrl);
        dispose();
    }
}