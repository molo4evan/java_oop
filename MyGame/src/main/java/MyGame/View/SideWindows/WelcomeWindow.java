package MyGame.View.SideWindows;

import MyGame.Controller.Controller;
import MyGame.View.ViewFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class WelcomeWindow extends ViewFrame {
    public WelcomeWindow(Controller c){
        super("Hello", c);
        getContentPane().setLayout(new GridLayout(0, 1));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //getContentPane().setBackground(new Color(0, 32, 167));

        JLabel name = new JLabel(new ImageIcon("maxresdefault1.jpg"), JLabel.CENTER);
        //name.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel pak = new JLabel("Press any key (ESC for exit)", JLabel.CENTER);
        pak.setFont(new Font("Arial", Font.PLAIN, 18));
        //pak.setForeground(new Color(255, 207, 80));

        JLabel copyrights = new JLabel("Powered by MOLO4EVAN", JLabel.CENTER);
        copyrights.setVerticalAlignment(SwingConstants.BOTTOM);
        copyrights.setFont(new Font("Arial", Font.PLAIN, 12));
        //copyrights.setForeground(Color.gray);

        add(name);
        add(pak);
        add(copyrights);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {
                if (keyEvent.getKeyChar() != KeyEvent.VK_ESCAPE) new MainMenu(ctrl);
                dispose();
            }
        });

        setMinimumSize(new Dimension(500, 400));
        //pack();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = this.getSize();
        setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
        setResizable(false);
        setVisible(true);
    }
}
