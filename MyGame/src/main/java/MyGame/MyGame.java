package MyGame;

import MyGame.Controller.Controller;
import MyGame.Controller.MainCtrl;
import MyGame.Model.MainModel;
import MyGame.Model.Model;
import MyGame.View.SideWindows.WelcomeWindow;

import javax.swing.*;

public class MyGame {
    public MyGame() throws Exception{
        Model m = new MainModel();
        Controller c = new MainCtrl(m);

        SwingUtilities.invokeLater(() -> new WelcomeWindow(c));
    }
}
