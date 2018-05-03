package MyGame.Model;

import MyGame.View.Subscriber;
import MyGame.View.ViewFrame;

import java.util.ArrayList;

public interface Model {
    void notifySubs() throws Exception;
    void addSub(Subscriber sub);
    void removeSub(Subscriber sub) throws Exception;

    ArrayList<Theme> getThemes();
    ArrayList<Player> getPlayers();
    void addPlayer(String name);
    void addTheme(Theme th);
    int getPlayerDeposit(String name) throws Exception;
    void setPlayerDeposit(String name, int value) throws Exception;
    Theme.Question getQuestion(String tn, int val) throws Exception;
    int getQuestionAmount();
    int getAvalQuestAmount();
    ArrayList<ArrayList<Boolean>> gaq();
    void setUpQATable();
    void setQuestionAvaliable(String theme, int val, boolean st) throws Exception;
    AI getAI();
    void addAI(AI.Difficulty mode);
    Theme.Question selectQuestionAI() throws Exception;
    void clearAll();
}
