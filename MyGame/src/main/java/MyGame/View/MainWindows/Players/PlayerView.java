package MyGame.View.MainWindows.Players;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;

class PlayerView extends JPanel {
    JLabel deposit;

    PlayerView(String name){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEtchedBorder(EtchedBorder.RAISED),
                BorderFactory.createEmptyBorder(30, 40, 30, 40)));
        add(new JLabel(name));
        deposit = new JLabel("0");

        add(deposit);
        setMinimumSize(new Dimension(100, 100));
    }

    void reloadDeposit(Integer value) throws Exception{
        deposit.setText(value.toString());
        //repaint();
    }
}
