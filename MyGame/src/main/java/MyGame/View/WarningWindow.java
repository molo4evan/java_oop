package MyGame.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class WarningWindow extends JDialog {
    public WarningWindow(String s, JFrame f){
        super(f, "Warning!", true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        GridBagLayout gbl = new GridBagLayout();
        setLayout(gbl);
        GridBagConstraints c = new GridBagConstraints();

        JLabel err = new JLabel(s);
        c.gridx = 0;
        c.gridy = 0;
        c.insets= new Insets(50, 50, 50, 50);
        gbl.setConstraints(err, c);
        add(err);

        pack();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = this.getSize();
        setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
        setResizable(false);
        setVisible(true);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {
                dispose();
            }
        });
    }
}
