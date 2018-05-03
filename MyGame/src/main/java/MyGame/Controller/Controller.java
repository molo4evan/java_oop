package MyGame.Controller;

import MyGame.Model.AI;
import MyGame.Model.Player;
import MyGame.Model.Theme;
import MyGame.View.Subscriber;

import java.util.ArrayList;
import java.util.Map;

public interface Controller {
    ArrayList<String> getThemeNames();
    ArrayList<String> getPlayerNames();
    int getPlayerDeposit(String name) throws Exception;
    void addPoints(String name, int value) throws Exception;
    //void takeAwayPoints(String name, int value) throws Exception;
    ArrayList<Theme> getThemes();
    void startGame(ArrayList<String> players, ArrayList<String> themes, int min, int max, AI.Difficulty mode) throws Exception;
    boolean processAnswer(String player, String answer) throws Exception;
    Theme.Question getQuestion(String theme, int value) throws Exception;
    int getQuestionAmount();
    void addSubscriber(Subscriber sub);
    void removeSubscriber(Subscriber sub) throws Exception;
    void setAnswering(boolean st);
    boolean isSingleMode();
    AI.Answer doAIStep() throws Exception;
    ArrayList<ArrayList<Boolean>> getAvaliableQuestions();
    boolean checkEnd();
    void reset();
    String getLeader();
}
