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
import java.util.logging.Logger;

/**
 * Contains all the pieces of the game, i.e. the cards (public objectives, tool cards) and
 * the dice involved in the match (those in the round track, those in the draft pool and
 * those in the dice bag). Contains a list of the players in the current game and
 * a structure to manage all the turns and rounds.
 * Every list contains elements according to the rules: 3 public objectives,
 * 2-4 players and 12 tool cards. Dice are 90 in total and scattered across
 * the above mentioned lists and the window patterns of each player.
 */
public class Game extends Observable<String> {

    private DiceBag diceBag;
    private ArrayList<Die> draft;
    private ArrayList<Player> players;
    private ArrayList<Die> roundTrack;
    private ArrayList<PublicObjective> publicObjectiveActive;
    private ArrayList<ToolCard> toolCards;
    private ToolCardParser reader;
    private RoundHandler handler;
    private boolean gameEnded;
    private Logger logger;

    private static final int N_OBJECTIVES = 3;
    private static final int N_TOOL_CARD = 12;
    private static final String RED = "RED";
    private static final String GREEN = "GREEN";
    private static final String YELLOW = "YELLOW";
    private static final String BLUE = "BLUE";
    private static final String PURPLE = "PURPLE";

    /**
     * Class constructor.
     */

    public Game(){
        diceBag = new DiceBag();
        draft = new ArrayList<Die>();
        players = new ArrayList<Player>();
        roundTrack = new ArrayList<Die>();
        publicObjectiveActive = new ArrayList<PublicObjective>(N_OBJECTIVES);
        toolCards = new ArrayList<ToolCard>(N_TOOL_CARD);
        reader = new ToolCardParser();
        gameEnded = false;
        logger = Logger.getLogger(Game.class.getName());
    }

    /**
     * Adds a new player to the list of the players involved in the game.
     * @param player the new player.
     */

    public void addPlayer(Player player) {
        players.add(player);
    }

    /**
     * Returns the player at the given index.
     * @param index the index of the player in the list of the players.
     * @return the requested player.
     */

    public Player getPlayers(int index) {
        return players.get(index);
    }

    /**
     * Returns the player corresponding to a specified ID. If no player
     * is related to the given ID, returns null.
     * @param id the ID of the requested player.
     * @return the requested player.
     */

    public Player getPlayerFromId(int id){
        for(Player p : players){
            if(p.getId() == id) return p;
        }
        return null;
    }

    /**
     * Returns true if all players are ready to play, i.e. they've already chosen both username and window pattern.
     * @return true if all players are ready; false otherwise.
     */

    public boolean allReadyToPlay(){
        for(Player p : players){
            if(!p.isReady()) return false;
        }
        return true;
    }

    /**
     * Returns the number of players in the game.
     * @return the number of players in the game as an integer.
     */

    public int playersSize(){
        return players.size();
    }

    /**
     * Checks if the selected username is already in use.
     * @param s a username.
     * @return true if there's a player with the given username; false otherwise.
     */

    public boolean find(String s){
        for (Player p : players){
            if(p.getUsername().equals(s)) return true;
        }
        return false;
    }

    /**
     * Returns the number of online players.
     * @return the number of online players as an integer.
     */

    public int getNumberOfOnlinePlayers(){
        int n = 0;
        for(Player p : players){
            if(p.isOnline()) n++;
        }
        return n;
    }

    /**
     * Inserts a die in the round track.
     * @param die the die to be inserted.
     */

    public void setDieRoundTrack(Die die) {
        roundTrack.add(die);
    }

    /**
     * Returns the die in the round track at the given index.
     * @param index the index of the die in the round track.
     * @return the requested die.
     */

    public Die getDieFromRoundTrack(int index){
        return roundTrack.get(index);
    }

    /**
     * Returns the number of dice currently in the round track.
     * @return the number of dice in the round track as an integer.
     */

    public int roundTrackSize(){
        return roundTrack.size();
    }

    /**
     * Removes a die from the round track and returns it.
     * @param index the index of the die in the round track.
     * @return the requested die.
     */

    public Die removeDieFromRoundTrack(int index) {
        Die die = roundTrack.get(index);
        roundTrack.remove(index);
        return die;
    }


    /**
     * Inserts a die in the draft pool.
     * @param die the die to be inserted.
     */

    public void setDieDraft(Die die){
        draft.add(die);
    }

    /**
     * Inserts a die in the draft pool at the given index.
     * @param index the index of the position where the die is inserted.
     * @param die the die to be inserted.
     */

    public void setDieDraft(int index, Die die){
        draft.add(index, die);
    }

    /**
     * Returns the die in the draft pool at the given index.
     * @param index the index of the die in the draft pool.
     * @return the requested die.
     */

    public Die getDieFromDraft(int index){
        return draft.get(index);
    }

    /**
     * Returns the number of dice currently in the draft pool.
     * @return the number of dice in the draft pool as an integer.
     */

    public int draftSize(){
        return draft.size();
    }

    /**
     * Removes a die from the draft pool and returns it.
     * @param index the index of the die in the draft pool.
     * @return the requested die.
     */

    public Die removeDieFromDraft(int index) {
        Die die = draft.get(index);
        draft.remove(index);
        return die;
    }

    /**
     * At the beginning of every round, gets a number of dice from the dice bag equal to
     * the number of players multiplied by two, plus one. Inserts all the dice in the draft pool.
     */

    public void setDraft() {
        for (int i = 0; i < 2 * players.size() + 1; i++) {
            draft.add(diceBag.getDie());
        }
    }

    /**
     * Moves all the dice left in the draft pool to the round track.
     */

    public void diceLeft(){
        roundTrack.addAll(draft);
        draft.clear();
    }

    /**
     * Returns the tool card at the given index.
     * @param index the index in the list of tool cards corresponding to the requested tool card.
     * @return the requested tool card.
     */

    public ToolCard getToolCard(int index) {
        return toolCards.get(index);
    }

    /**
     * When a new match starts: assigns a different private objective to each player,
     * selects the public objectives for the game, creates the tool cards from a predefined file,
     * sets the first draft and initializes the handler of rounds and turns.
     */

    public void initialize() {
        //randomly assigns private objectives
        ArrayList<PrivateObjective> privateObjectives = new ArrayList<PrivateObjective>();
        privateObjectives.add(new PrivateObjective(RED));
        privateObjectives.add(new PrivateObjective(YELLOW));
        privateObjectives.add(new PrivateObjective(PURPLE));
        privateObjectives.add(new PrivateObjective(GREEN));
        privateObjectives.add(new PrivateObjective(BLUE));
        Random random = new Random();
        int bound;
        int k;
        for(Player p : players) {
            bound = privateObjectives.size();
            k = random.nextInt(bound);
            p.setPlayerObjective(privateObjectives.get(k));
            privateObjectives.remove(k);
        }

        //randomly creates 3 public objectives
        PublicObjectiveFactory of = new PublicObjectiveFactory();
        for (int i = 0; i < N_OBJECTIVES; i++) {
            publicObjectiveActive.add(of.getRandomObjective());
        }

        toolCards.addAll(reader.readToolCards());
        setDraft();
        handler = new RoundHandler(players.size());
    }

    /**
     * Inserts a die - taken from the draft pool - in the window of a player, at the given coordinates (x, y).
     * Then notify the player of the successful operation.
     * @param playerId the ID of the player.
     * @param x the index of the row.
     * @param y the index of the column.
     * @param die the index of the die in the draft pool.
     */

    public void useDie(int playerId, int x, int y, int die) {
        WindowPattern w = getPlayerFromId(playerId).getPlayerWindow();
        Die d = removeDieFromDraft(die);
        w.getWindowMatrix(x, y).setDie(d);
        getPlayerFromId(playerId).setFirstDiePlaced(true);
        getPlayerFromId(playerId).setDieUsed(true);
    }

    /**
     * Moves a die inside the window of a player, from given coordinates (x, y) to some new coordinates (a, b).
     * In case tool card 12 is used, it is possible that the player wants to move only one die,
     * therefore default out-of-bound coordinated are given to throw an exception.
     * Then notify the player of the successful operation.
     * @param playerId the ID of the player.
     * @param x the index of the row where the die is placed.
     * @param y the index of the column where the die is placed.
     * @param a the index of the row where the die is going to be moved.
     * @param b the index of the column where the die is going to be moved.
     */

    public void moveDie(int playerId, int x, int y, int a, int b) {
        try{
            WindowPattern w = getPlayerFromId(playerId).getPlayerWindow();
            Die d = w.getWindowMatrix(x, y).removeDie();
            w.getWindowMatrix(a, b).setDie(d);
        } catch(ArrayIndexOutOfBoundsException e){
            logger.info("exceptional use of tool card 12");
        }
    }

    /**
     * Reduces the tokens available to the player (-1 if the tool card has not been used yet,
     * -2 otherwise) and sets the toolcard as already used.
     * @param toolcard the number of the tool card.
     * @param player the ID of the player.
     */

    public void useToolCard(int toolcard, int player) {
        getPlayerFromId(player).setToolCardUsed(true);
        int cost = toolCards.get(toolcard - 1).isAlreadyUsed() ? 2 : 1;
        getPlayerFromId(player).getPlayerWindow().decreaseDifficultyToken(cost);
        toolCards.get(toolcard - 1).setAsAlreadyUsed();
    }

    /**
     * Returns true if the player has enough tokens to use a certain tool card and has not used a tool card yet.
     * @param toolcard the number of the tool card.
     * @param player the ID of the player.
     * @return true if the player is able to use the card; false otherwise.
     */

    public boolean canUseToolCard(int toolcard, int player){
        if(getPlayerFromId(player).isToolCardUsed()) return false;
        int cost = toolCards.get(toolcard - 1).isAlreadyUsed() ? 2 : 1;
        return getPlayerFromId(player).getPlayerWindow().getDifficultyToken() > cost;
    }

    /**
     * Notifies one player of a certain event.
     * @param message a Json representation of the class describing the event.
     * @param player the player whom the message is sent to.
     */

    public void notifyMessage(Message message, int player){
        notify(message.serialize(), player);
    }

    /**
     * Notifies all the players in the match of a certain event.
     * @param message a Json representation of the class describing the event.
     */

    public void notifyAllPlayers(Message message){
        notify(message.serialize());
    }

    /**
     * Notifies all the players in the match of a change in the main game class,
     * i.e. a successful move by a player, the start of a new round etc.
     */

    public void notifyUpdate(){
        notify(new UpdateModelMessage(handler.getRound(), players, draft, roundTrack).serialize());
    }

    /**
     *
     * @return the dice bag.
     */

    public DiceBag getDiceBag() {
        return diceBag;
    }

    /**
     *
     * @return the draft pool.
     */

    public List<Die> getDraft() {
        return draft;
    }

    /**
     *
     * @return the round track.
     */

    public List<Die> getRoundTrack() {
        return roundTrack;
    }

    /**
     *
     * @return the list of all players in the game.
     */

    public List<Player> getPlayers() {
        return players;
    }

    /**
     *
     * @return the list of all tool cards.
     */

    public List<ToolCard> getToolCards () {
        return toolCards;
    }

    /**
     *
     * @return the list of the public objectives selected for the match.
     */

    public List<PublicObjective> getPublicObjectiveActive() {
        return publicObjectiveActive;
    }

    /**
     *
     * @return the class to manage turns and rounds.
     */

    public RoundHandler getHandler() {
        return handler;
    }

    /**
     *
     * @return true if game is ended; false otherwise.
     */

    public boolean isGameEnded() {
        return gameEnded;
    }

    /**
     *
     * @param gameEnded whether the game is ended.
     */

    public void setGameEnded(boolean gameEnded) {
        this.gameEnded = gameEnded;
    }

    /**
     * Returns a list of String representation of the public objectives.
     * @return the list of String representation of the public objectives.
     */

    public List<String> getObjectivesAsString(){
        ArrayList<String> obj = new ArrayList<String>();
        for(PublicObjective o : getPublicObjectiveActive()){
            obj.add(o.toString());
        }
        return obj;
    }

    /**
     * Builds a String representing the draft pool.
     * @return String representation of the draft pool.
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

    /**
     * Builds a String representing the round track.
     * @return String representation of the round track.
     */

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

    /**
     * Builds a String representing the class.
     * @return String representation of the class.
     */

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

    /**
     * Prints the entire class.
     */

    public void dump(){
        System.out.println(this);
    }
}