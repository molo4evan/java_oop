package MyGame.Model;

import MyGame.View.Subscriber;
import MyGame.View.ViewFrame;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

public class MainModel implements Model {
    private ArrayList<Theme> themes = new ArrayList<>();
    private ArrayList<Player> players = new ArrayList<>();
    private ArrayList<ArrayList<Boolean>> qat = new ArrayList<>();
    private AI ai;

    private ArrayList<Subscriber> subscribers = new ArrayList<>();

    @Override
    public void notifySubs() throws Exception{
        for (Subscriber s: subscribers){
            s.update();
        }
    }

    @Override
    public void addSub(Subscriber sub) {
        subscribers.add(sub);
    }

    @Override
    public void removeSub(Subscriber sub) throws Exception{
        if (!subscribers.contains(sub)) throw new Exception(); //TODO: exc
        subscribers.remove(sub);
    }

    @Override
    public ArrayList<Player> getPlayers() {
        return players;
    }

    @Override
    public ArrayList<Theme> getThemes() {
        return themes;
    }

    @Override
    public void addPlayer(String name){
        players.add(new Player(name));
    }

    @Override
    public void addTheme(Theme th) {
        themes.add(th);
    }

    @Override
    public int getPlayerDeposit(String name) throws Exception{
        for (Player p: players){
            if (p.getName().equals(name)) return p.getDeposit();
        }
        throw new Exception(); //TODO: exc
    }

    @Override
    public void setPlayerDeposit(String name, int value) throws Exception{
        Player plr = null;
        for (Player p: players){
            if (p.getName().equals(name)){
                plr = p;
                break;
            }
        }
        if (plr == null) throw new Exception(); //TODO: exc

        plr.setDeposit(value);
    }

    @Override
    public Theme.Question getQuestion(String themename, int value) throws Exception{
        Theme theme = null;
        for (Theme t: themes){
            if (t.getName().equals(themename)){
                theme = t;
                break;
            }
        }
        if (theme == null) throw new Exception(); //TODO: add exc

        return theme.getQuestion(value);
    }

    @Override
    public int getQuestionAmount() {
        int max = 0;
        for (Theme t: themes){
            int i = t.getQuestionAmount();
            max = i > max ? i : max;
        }
        return max;
    }

    @Override
    public void setUpQATable() {
        for (int i = 0; i < themes.size(); i++){
            qat.add(new ArrayList<>());
            for (int j = 0; j < themes.get(i).getQuestionAmount(); j++){
                qat.get(i).add(true);
            }
        }
    }

    @Override
    public ArrayList<ArrayList<Boolean>> gaq() {    //TODO: do
        return qat;
    }

    @Override
    public void setQuestionAvaliable(String theme, int val, boolean st) throws Exception{
        int i = -1;
        for (Theme t: themes){
            if (t.getName().equals(theme)){
                i = themes.indexOf(t);
                break;
            }
        }
        if (i == -1) throw new Exception(); //TODO: exc

        int j = themes.get(i).getValues().indexOf(val);
        if (j == -1) throw new Exception(); //TODO: exc

        qat.get(i).remove(j);
        qat.get(i).add(j, st);
    }

    @Override
    public AI getAI() {
        return ai;
    }

    @Override
    public Theme.Question selectQuestionAI() throws Exception{
        int i = -1, j = -1, k = 0;
        Random r = new Random();
        do {
            i = r.nextInt(themes.size());
            j = r.nextInt(themes.get(0).getQuestionAmount());
        }
        while (!qat.get(i).get(j) && k++ <= themes.size() * themes.get(0).getQuestionAmount());
        if (!qat.get(i).get(j)) throw new Exception();
        qat.get(i).remove(j);
        qat.get(i).add(j, false);
        return themes.get(i).getQuestionByIndex(j);
    }

    @Override
    public void addAI(AI.Difficulty mode) {
        ai = new AI(mode);
        players.add(ai);
    }

    @Override
    public int getAvalQuestAmount() {
        int sum = 0;
        for (int i = 0; i < qat.size(); i++){
            for (int j = 0; j < qat.get(i).size(); j++){
                if (qat.get(i).get(j)) sum++;
            }
        }
        return sum;
    }

    @Override
    public void clearAll(){
        themes = new ArrayList<>();
        players = new ArrayList<>();
        qat = new ArrayList<>();
        ai = null;
        subscribers = new ArrayList<>();
    }
}