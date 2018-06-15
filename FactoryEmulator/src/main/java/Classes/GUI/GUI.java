package Classes.GUI;

import Classes.Factory.Factory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GUI extends JFrame{
    public GUI(Factory f){
        super("Fabric Emulator");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        GridBagLayout gbl = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        setLayout(gbl);
        c.anchor = GridBagConstraints.CENTER;
        c.fill   = GridBagConstraints.NONE;
        c.gridheight = 1;
        c.gridwidth  = GridBagConstraints.REMAINDER;
        c.gridx = 0;
        c.gridy = GridBagConstraints.RELATIVE;
        c.insets = new Insets(20, 20, 20, 20);
        c.ipadx = 0;
        c.ipady = 0;
        c.weightx = 0.0;
        c.weighty = 0.0;

        JLabel img = new JLabel(new ImageIcon("fabric.png"));
        gbl.setConstraints(img, c);
        add(img);

        c.insets.bottom = 20;
        c.insets.top = 0;
        InfoPanel ip = new InfoPanel(f);
        gbl.setConstraints(ip, c);
        add(ip);

        pack();

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                f.endWork();
                dispose();
            }
        });

        f.startWork();
        setVisible(true);
    }
}
