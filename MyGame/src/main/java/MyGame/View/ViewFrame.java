package MyGame.View;

import MyGame.Controller.Controller;

import javax.swing.*;

public abstract class ViewFrame extends JFrame {
    protected Controller ctrl;

    public ViewFrame(String name, Controller c) {
        super(name);
        ctrl = c;
    }
}