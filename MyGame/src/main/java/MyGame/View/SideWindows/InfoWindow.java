package MyGame.View.SideWindows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

class InfoWindow extends JFrame {
    InfoWindow(){
        super("Information");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        GridBagLayout gbl = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        setLayout(gbl);
        c.gridx = 0;
        c.gridy = GridBagConstraints.RELATIVE;

        JLabel a = new JLabel("To add new themepack:");
        a.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(30, 50, 40, 50);
        gbl.setConstraints(a, c);
        add(a);

        JLabel b = new JLabel("- Add a directory with name of your theme to \"themes\" folder");
        c.insets = new Insets(10, 50, 10, 50);
        c.anchor = GridBagConstraints.WEST;
        gbl.setConstraints(b, c);
        add(b);

        JLabel d = new JLabel("- To directory add files with names from \"100\" to \"500\" and \"icon.png\" file, if needed");
        c.insets = new Insets(10, 50, 5, 50);
        gbl.setConstraints(d, c);
        add(d);

        JLabel e = new JLabel("     Note: icon should not be more than 100x100 points!");
        e.setForeground(Color.red);
        c.insets = new Insets(5, 50, 10, 50);
        gbl.setConstraints(e, c);
        add(e);

        JLabel f = new JLabel("- In Question file:");
        c.insets = new Insets(10, 50, 5, 50);
        gbl.setConstraints(f, c);
        add(f);

        JLabel h = new JLabel("     - first line is right answer;");
        c.insets = new Insets(5, 50, 5, 50);
        gbl.setConstraints(h, c);
        add(h);

        JLabel g = new JLabel("     - second line is wrong one;");
        gbl.setConstraints(g, c);
        add(g);

        JLabel i = new JLabel("     - other lines are the question.");
        gbl.setConstraints(i, c);
        add(i);

        JLabel j = new JLabel("Follow the instructions and enjoy the game!");
        c.insets = new Insets(15, 50, 40, 50);
        c.anchor = GridBagConstraints.CENTER;
        gbl.setConstraints(j, c);
        add(j);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {
                dispose();
            }
        });

        pack();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = this.getSize();
        setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
        setResizable(false);
        setVisible(true);
    }
}
