package it.polimi.ingsw.model;
import it.polimi.ingsw.messages.ErrorMessage;
import it.polimi.ingsw.model.objectives.PrivateObjective;
import it.polimi.ingsw.model.objectives.PublicObjectiveFactory;
import it.polimi.ingsw.model.objectives.publicobjectives.*;
import it.polimi.ingsw.utils.Observable;

import java.util.List;
import java.util.Random;
import java.util.ArrayList;

public class Game extends Observable {

    private boolean gameStarted;
    private DiceBag diceBag;
    private ArrayList<Die> draft;
    private ArrayList<Player> players;
    private ArrayList<Die> roundTrack;
    private PublicObjective[] publicObjectiveActive;

    /*
        constructor
     */

    public Game(){
        gameStarted = false;
        this.diceBag = new DiceBag();
        this.draft = new ArrayList<Die>();
        this.players = new ArrayList<Player>();
        this.roundTrack = new ArrayList<Die>();
        this.publicObjectiveActive = new PublicObjective[3];
    }

    /*
        getters and setters
     */

    public boolean isGameStarted() {
        return gameStarted;
    }

    public void setGameStarted(boolean gameStarted) {
        this.gameStarted = gameStarted;
    }

    public List<Die> getDraft() {
        return draft;
    }

    public List<Die> getRoundTrack() {
        return roundTrack;
    }

    public List<Player> getPlayers() {
        return players;
    }

    /*
        players methods
     */

    public void setPlayers(Player player) {
        players.add(player);
    }

    public Player getPlayers(int index) {
        return players.get(index);
    }

    public int playersSize(){
        return players.size();
    }

    public boolean find(String s){
        for (Player p : players){
            if(p.getUsername().equals(s)) return true;
        }
        return false;
    }

    public boolean findAndReconnect(String s, int id){
        for(Player p : players){
            if (p.getUsername().equals(s)){
                if(!p.getOnline()){
                    p.setOnline(true);
                    p.setId(id);
                    return true;
                }
            }
        }
        return false;
    }

    /*
        roundTrack methods
     */

    public void setDieRoundTrack(Die die) {
        roundTrack.add(die);
    }

    public Die getDieFromRoundTrack(int index){
        return roundTrack.get(index);
    }

    public int roundTrackSize(){
        return roundTrack.size();
    }

    public Die removeDieFromRoundTrack(int index) {
        Die die = roundTrack.get(index);
        roundTrack.remove(index);
        return die;
    }

    /*
        draftPool methods
     */

    public Die getDieFromDraft(int index){
        return draft.get(index);
    }

    public void setDieDraft(Die die){
        draft.add(die);
    }

    public int draftSize(){
        return draft.size();
    }

    public Die removeDieFromDraft(int index) {
        Die die = draft.get(index);
        draft.remove(index);
        return die;
    }

    public void setDraft() {
        for (int i = 0; i < 2 * players.size() + 1; i++) {
            draft.add(diceBag.getDie());
        }
    }

    public void diceLeft(){
        roundTrack.addAll(draft);
        draft.clear();
    }

    /*
        give players their private objective, select the public objectives
     */

    public void initialize() {

        gameStarted = true;

        //randomly assigns private objectives
        ArrayList<PrivateObjective> privateObjectives = new ArrayList<PrivateObjective>();
        privateObjectives.add(new PrivateObjective("RED"));
        privateObjectives.add(new PrivateObjective("YELLOW"));
        privateObjectives.add(new PrivateObjective("PURPLE"));
        privateObjectives.add(new PrivateObjective("GREEN"));
        privateObjectives.add(new PrivateObjective("BLUE"));
        Random random = new Random();
        int bound;
        int k;
        for (int i = 0; i < players.size(); i++) {
            bound = privateObjectives.size();
            k = random.nextInt(bound);
            players.get(i).setPlayerObjective(privateObjectives.get(k));
            privateObjectives.remove(k);
        }

        //randomly creates 3 public objectives
        PublicObjectiveFactory of = new PublicObjectiveFactory();
        for (int i = 0; i < 3; i++) {
            publicObjectiveActive[i] = of.getRandomObjective();
        }
    }

    /*
        standard player actions
     */

    public void useDie(int playerIndex, int x, int y, int die) {
        WindowPattern w = getPlayers(playerIndex).getPlayerWindow();
        Die d = removeDieFromDraft(die);
        w.getWindowMatrix(x, y).setDie(d);
        if(!getPlayers(playerIndex).isFirstDiePlaced()) getPlayers(playerIndex).setFirstDiePlaced(true);
        notify(this.toString());
    }

    /*
        notify methods
     */

    public void errorMessage(int i, int player){
        notify(new ErrorMessage(i).serialize(), player);
    }

    public void okMessage(int player){

    }

    /*
        toString methods
     */

    public String draftToString(){
        try {
            StringBuilder sb = new StringBuilder();
            for (Die d : draft) {
                sb.append(d.toString());
                sb.append(" ");
            }
            return sb.toString() + "\u001B[0m";
        } catch (NullPointerException e){
            return e.toString();
        }
    }

    public String roundTrackToString() {
        try {
            StringBuilder sb = new StringBuilder();
            for (Die d : roundTrack) {
                sb.append(d.toString());
                sb.append(" ");
            }
            return sb.toString() + "\u001B[0m";
        } catch (NullPointerException e){
            return e.toString();
        }
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        String s = "Round Track List: " + roundTrackToString() + "\nDraft Pool: " + draftToString() + "\n\n";
        sb.append(s);
        for(int i = 0; i < playersSize(); i++){
            sb.append(players.get(i).toString());
            sb.append("\n");
        }
        return sb.toString();
    }

    public void dump(){
        System.out.println(this);
    }

}