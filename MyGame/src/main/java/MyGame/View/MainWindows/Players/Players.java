package MyGame.View.MainWindows.Players;

import MyGame.Controller.Controller;
import MyGame.View.Subscriber;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Players extends JPanel implements Subscriber {
    private Controller ctrl;
    private Map<String, PlayerView> players = new HashMap<>();

    public Players(Controller c){
        ctrl = c;

        GridBagLayout gbl = new GridBagLayout();
        GridBagConstraints cs = new GridBagConstraints();
        setLayout(gbl);
        cs.gridy = 0;
        cs.gridx = GridBagConstraints.RELATIVE;
        cs.insets = new Insets(10, 10, 10, 10);

        ArrayList<String> ps =  c.getPlayerNames();
        PlayerView tmp;
        for (String p: ps){
            tmp = new PlayerView(p);
            players.put(p, tmp);
            gbl.setConstraints(tmp, cs);
            add(tmp);
        }
    }

    @Override
    public void update() throws Exception{
        for (Map.Entry<String, PlayerView> pl: players.entrySet()){
            String name = pl.getKey();
            PlayerView pv = pl.getValue();
            pv.reloadDeposit(ctrl.getPlayerDeposit(name));
        }
    }
}
