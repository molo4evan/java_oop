package MyGame.Controller;

import MyGame.Model.AI;
import MyGame.Model.Model;
import MyGame.Model.Player;
import MyGame.Model.Theme;
import MyGame.View.Subscriber;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainCtrl implements Controller {
    private Map<String, Theme> all_themes = new HashMap<>();
    private Model model;
    private boolean single = true;
    private Theme.Question current_question = null;
    private boolean answering = false;

    public MainCtrl(Model m) throws Exception{
        model = m;
        File th = new File("themes");
        if (!th.isDirectory()) throw new Exception(); //TODO: exc
        for (File f: th.listFiles()){
            if (f.isDirectory()) all_themes.put(f.getName(), new Theme(f.getName()));
        }
    }

    @Override
    public ArrayList<Theme> getThemes() {
        return model.getThemes();
    }

    @Override
    public ArrayList<String> getThemeNames() {
        ArrayList<String> tmp = new ArrayList<>();
        for (Map.Entry<String, Theme> t: all_themes.entrySet()){
            tmp.add(t.getKey());
        }
        return tmp;
    }

    private void addSinglePlayer(AI.Difficulty mode) {
        model.addPlayer("You");
        model.addAI(mode);
        single = true;
    }

    @Override
    public boolean processAnswer(String player, String answer) throws Exception{    //TODO: do
        boolean cor = current_question.isCorrect(answer);
        if (cor) model.setPlayerDeposit(player, model.getPlayerDeposit(player) + current_question.getValue());
        return cor;
    }

    @Override
    public Theme.Question getQuestion(String theme, int value) throws Exception{
        Theme.Question q = model.getQuestion(theme, value);
        model.setQuestionAvaliable(theme, value, false);
        current_question = q;
        model.notifySubs();
        return q;
    }

    @Override
    public int getQuestionAmount() {
        return model.getQuestionAmount();
    }

    @Override
    public void startGame(ArrayList<String> players, ArrayList<String> themes, int min, int max, AI.Difficulty mode) throws Exception {
        if (max < min) throw new Exception();

        if (null == players) addSinglePlayer(mode);
        else for (String p: players) model.addPlayer(p);

        if (themes.size() == 0) throw new Exception();  //TODO: exc
        for (String t: themes){
            Theme tmp = all_themes.get(t);
            if (tmp == null) throw new Exception();

            Theme tmp1 = new Theme();
            tmp1.setName(t);
            tmp1.setIcon(tmp.getIcon());
            tmp1.setValues(min, max);
            for (int i = min; i <= max; i += 100){
                tmp1.addQuestion(tmp.getQuestion(i), i);
            }
            model.addTheme(tmp1);
        }

        model.setUpQATable();
    }

    @Override
    public void addSubscriber(Subscriber sub) {
        model.addSub(sub);
    }

    @Override
    public void removeSubscriber(Subscriber sub) throws Exception{
        model.removeSub(sub);
    }

    @Override
    public void setAnswering(boolean answering) {
        this.answering = answering;
    }

    @Override
    public boolean isSingleMode() {
        return single;
    }

    @Override
    public ArrayList<ArrayList<Boolean>> getAvaliableQuestions() {
        return model.gaq();
    }

    @Override
    public AI.Answer doAIStep() throws Exception{
        Theme.Question q =  model.selectQuestionAI();
        getQuestion(q.getTheme(), q.getValue());
        String a = model.getAI().answer(q);
        return new AI.Answer(processAnswer("AI", a), q, a);
    }

    @Override
    public boolean checkEnd() {
        return model.getAvalQuestAmount() == 0;
    }

    @Override
    public void reset() {
        model.clearAll();
    }

    @Override
    public ArrayList<String> getPlayerNames() {
        ArrayList<Player> pls = model.getPlayers();

        ArrayList<String> nms = new ArrayList<>();
        for (Player p: pls){
            nms.add(p.getName());
        }

        return nms;
    }

    @Override
    public int getPlayerDeposit(String name) throws Exception{
        return model.getPlayerDeposit(name);
    }

    @Override
    public void addPoints(String name, int value) throws Exception{
        model.setPlayerDeposit(name, model.getPlayerDeposit(name) + value);
    }

    @Override
    public String getLeader() {
        int max = 0;
        int count = 0;
        String leader = null;
        for (Player p: model.getPlayers()){
            if (p.getDeposit() > max){
                max = p.getDeposit();
                count = 1;
                leader = p.getName();
            }
            else if (p.getDeposit() == max){
                count++;
            }
        }

        if (null == leader) return "Nobody";
        else if (count > 1) return "Friendship";
        else return leader;
    }
}
