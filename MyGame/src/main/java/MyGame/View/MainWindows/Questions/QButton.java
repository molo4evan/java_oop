package MyGame.View.MainWindows.Questions;

import MyGame.Controller.Controller;

import javax.swing.*;
import java.awt.*;

class QButton extends JButton {
        private String theme;
        private Integer value;
        private Controller ctrl;
        private JFrame parent;

        QButton(ImageIcon ic, String th, String val, Controller c, JFrame p){
            super(val);
            setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 40));
            setVerticalTextPosition(AbstractButton.CENTER);
            setHorizontalTextPosition(AbstractButton.CENTER);
            value = Integer.parseInt(val);
            if (ic != null) {
                setIcon(ic);
            }
            theme = th;
            ctrl = c;
            parent = p;
        }

        String getTheme() {
            return theme;
        }

        Integer getValue() {
            return value;
        }

        Controller getCtrl() {
            return ctrl;
        }

        @Override
        public JFrame getParent() {
            return parent;
        }
    }
