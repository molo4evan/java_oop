package MyGame.View.MainWindows.Questions;

import MyGame.Model.Theme;
import MyGame.View.ErrorWindow;
import MyGame.View.MainWindows.GameWindow;
import MyGame.View.MainWindows.QuestionWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

class QuestionListener extends KeyAdapter implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        QButton src = (QButton)actionEvent.getSource();
        try {
            Theme.Question q = src.getCtrl().getQuestion(src.getTheme(), src.getValue());
            new QuestionWindow(q, src.getCtrl(), (GameWindow) src.getParent());
        }
        catch (Exception e){
            new ErrorWindow(e);
        }
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
        if (keyEvent.getKeyChar() == KeyEvent.VK_ENTER){
            QButton src = (QButton)keyEvent.getSource();
            try {
                Theme.Question q = src.getCtrl().getQuestion(src.getTheme(), src.getValue());
                new QuestionWindow(q, src.getCtrl(), (GameWindow) src.getParent());
            }
            catch (Exception e){
                new ErrorWindow(e);
            }
        }
    }
}
