package it.polimi.ingsw.model;
import it.polimi.ingsw.messages.Message;
import it.polimi.ingsw.messages.client.UpdateModelMessage;
import it.polimi.ingsw.model.objectives.PrivateObjective;
import it.polimi.ingsw.model.objectives.PublicObjectiveFactory;
import it.polimi.ingsw.model.objectives.publicobjectives.*;
import it.polimi.ingsw.parsers.ToolCardParser;
import it.polimi.ingsw.utils.Observable;

import java.util.List;
import java.util.Random;
import java.util.ArrayList;

public class Game extends Observable<String> {

    private boolean gameStarted;
    private DiceBag diceBag;
    private ArrayList<Die> draft;
    private ArrayList<Player> players;
    private ArrayList<Die> roundTrack;
    private ArrayList<PublicObjective> publicObjectiveActive;
    private ArrayList<ToolCard> toolCards;
    private ToolCardParser reader;

    private static final int N_OBJECTIVES = 3;
    private static final int N_TOOL_CARD = 12;

    /*
        constructor
     */

    public Game(){
        gameStarted = false;
        this.diceBag = new DiceBag();
        this.draft = new ArrayList<Die>();
        this.players = new ArrayList<Player>();
        this.roundTrack = new ArrayList<Die>();
        this.publicObjectiveActive = new ArrayList<PublicObjective>(N_OBJECTIVES);
        this.toolCards = new ArrayList<ToolCard>(N_TOOL_CARD);
        this.reader = new ToolCardParser();
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

    public List<ToolCard> getToolCards () {
        return toolCards;
    }

    public List<PublicObjective> getPublicObjectiveActive() {
        return publicObjectiveActive;
    }

    /*
        players methods
     */

    public void addPlayer(Player player) {
        players.add(player);
    }

    public Player getPlayers(int index) {
        return players.get(index);
    }

    /*
        returns the player corresponding to the id passed as argument
     */

    public Player getPlayerFromId(int id){
        for(Player p : players){
            if(p.getId() == id) return p;
        }
        return null;
    }

    /*
        returns true if all players are ready to play, i.e. they've already chosen both username and window
     */

    public boolean allReadyToPlay(){
        for(Player p : players){
            if(!p.isReady()) return false;
        }
        return true;
    }

    public int playersSize(){
        return players.size();
    }

    /*
        returns true if there's a player with a given username
     */

    public boolean find(String s){
        for (Player p : players){
            if(p.getUsername().equals(s)) return true;
        }
        return false;
    }

    /*
        returns true if there's an offline player with a given username,
        i.e. the player must be reconnected
     */

    public boolean findAndReconnect(String s, int id){
        for(Player p : players){
            if (p.getUsername().equals(s)){
                if(!p.isOnline()){
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
        called when a new match starts:
        - assigns players their private objective,
        - selects the public objectives,
        - creates the tool cards
        - set the first draft
     */

    public void initialize() {
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
        for (int i = 0; i < N_OBJECTIVES; i++) {
            publicObjectiveActive.add(of.getRandomObjective());
        }

        //creates tool cards
        toolCards.addAll(reader.readToolCards());

        //first draft
        setDraft();
    }

    /*
        standard player action: set a die inside a player's window
     */

    public void useDie(int playerId, int x, int y, int die) {
        WindowPattern w = getPlayerFromId(playerId).getPlayerWindow();
        Die d = removeDieFromDraft(die);
        w.getWindowMatrix(x, y).setDie(d);
        getPlayerFromId(playerId).setFirstDiePlaced(true);
        notify(this.toString());
    }

    /*
        standard player action: move a die inside a player's window
        x, y old coordinates, a, b new coordinates
     */
    public void moveDie(int playerId, int x, int y, int a, int b) {
        WindowPattern w = getPlayerFromId(playerId).getPlayerWindow();
        Die d = w.getWindowMatrix(x, y).removeDie();
        w.getWindowMatrix(a, b).setDie(d);
    }

    /*
        reduces the tokens of a player and sets a toolcard as already used
     */

    public void useToolCard(int toolcard, int player) {
        getPlayerFromId(player).setToolCardUsed(true);
        int cost = toolCards.get(toolcard - 1).isAlreadyUsed() ? 2 : 1;
        getPlayerFromId(player).getPlayerWindow().decreaseDifficultyToken(cost);
        toolCards.get(toolcard - 1).setAsAlreadyUsed();
    }

    /*
        return true if the player has enough tokens to use the card and has not used a tool card yet
     */

    public boolean canUseToolCard(int toolcard, int player){
        if(getPlayerFromId(player).isToolCardUsed()) return false;
        int cost = toolCards.get(toolcard - 1).isAlreadyUsed() ? 2 : 1;
        return getPlayerFromId(player).getPlayerWindow().getDifficultyToken() > cost;
    }

    /*
        returns the toolcard at the given
     */

    public ToolCard getToolCard(int i) {
        return toolCards.get(i);
    }

    /*
        notify GameManager of something
     */

    public void notifyMessage(Message message, int player){
        notify(message.serialize(), player);
    }

    public void notifyAllPlayers(Message message){
        notify(message.serialize());
    }

    public void notifyUpdate(){
        notify(new UpdateModelMessage(players, draft, roundTrack).serialize());
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