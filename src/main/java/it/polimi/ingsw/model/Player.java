package it.polimi.ingsw.model;

import it.polimi.ingsw.model.objectives.PrivateObjective;

/**
 * Represents a player and contains all the necessary flags to handle the phases
 * of the match (initial phase, first turn, playing phase) and the actions of
 * the turn (die placement, use of a tool card).
 * Contains the window of the player, which contains the number of tokens
 * still available to the player.
 */
public class Player {

    private String username;
    private int id;
    private WindowPattern playerWindow;
    private PrivateObjective playerObjective;
    private boolean isOnline;
    private boolean dieUsed;
    private boolean toolCardUsed;
    private boolean firstTurnDone;
    private boolean secondTurnDone;
    private boolean firstDiePlaced;
    private boolean ready;

    /**
     * Constructor specifying username, window pattern and ID of the player.
     * @param username the name of the player.
     * @param playerWindow the personal window pattern.
     * @param id the ID of the player.
     */

    public Player(String username, WindowPattern playerWindow, int id) {
        this.username = username;
        this.id = id;
        isOnline = true;
        dieUsed = false;
        toolCardUsed = false;
        firstTurnDone = false;
        secondTurnDone = false;
        firstDiePlaced = false;
        ready = false;
        this.playerWindow = playerWindow;
    }

    /**
     * Constructor specifying the username and window pattern of the player.
     * @param username the name of the player.
     * @param playerWindow the personal window pattern.
     */

    public Player(String username, WindowPattern playerWindow) {
        this.username = username;
        isOnline = true;
        dieUsed = false;
        toolCardUsed = false;
        firstTurnDone = false;
        secondTurnDone = false;
        firstDiePlaced = false;
        ready = false;
        this.playerWindow = playerWindow;
    }

    /**
     * Constructor specifying the username of the player.
     * @param username the name of the player.
     */

    public Player (String username) {
        this.username = username;
        isOnline = true;
        dieUsed = false;
        toolCardUsed = false;
        firstTurnDone = false;
        secondTurnDone = false;
        firstDiePlaced = false;
        ready = false;
    }

    /**
     *
     * @return the ID.
     */

    public int getId() {
        return id;
    }

    /**
     *
     * @param id the ID.
     */

    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @return the username.
     */

    public String getUsername() {
        return username;
    }

    /**
     *
     * @param username the username.
     */

    public void setUsername(String username) {
        this.username = username;
    }

    /**
     *
     * @return the personal window pattern.
     */

    public WindowPattern getPlayerWindow() {
        return playerWindow;
    }

    /**
     *
     * @param playerWindow the window pattern.
     */

    public void setPlayerWindow(WindowPattern playerWindow) {
        this.playerWindow = playerWindow;
    }

    /**
     *
     * @param playerObjective the private objective.
     */

    public void  setPlayerObjective (PrivateObjective playerObjective ) {
        this.playerObjective = playerObjective;
    }

    /**
     *
     * @return the personal private objective.
     */

    public PrivateObjective getPlayerObjective () {
        return  this.playerObjective;
    }

    /**
     *
     * @return true if the player is online; false otherwise.
     */

    public boolean isOnline() {
        return isOnline;
    }

    /**
     *
     * @param status whether the player is online.
     */

    public void setOnline(boolean status) {
        this.isOnline = status;
    }

    /**
     *
     * @return true if the player has already placed a die in his turn; false otherwise.
     */

    public boolean isDieUsed() {
        return dieUsed;
    }

    /**
     *
     * @param dieUsed whether the player has already placed a die in his turn.
     */

    public void setDieUsed(boolean dieUsed) {
        this.dieUsed = dieUsed;
    }

    /**
     *
     * @return true if the player has already used a tool card in his turn; false otherwise.
     */

    public boolean isToolCardUsed() {
        return toolCardUsed;
    }

    /**
     *
     * @param toolCardUsed whether the player has already used a tool card in his turn.
     */

    public void setToolCardUsed(boolean toolCardUsed) {
        this.toolCardUsed = toolCardUsed;
    }

    /**
     *
     * @return true if the player has already placed his first die; false otherwise.
     */

    public boolean isFirstDiePlaced() {
        return firstDiePlaced;
    }

    /**
     *
     * @param firstDiePlaced whether the player has already placed his first die
     */

    public void setFirstDiePlaced(boolean firstDiePlaced) {
        this.firstDiePlaced = firstDiePlaced;
    }

    /**
     *
     * @return true if the player is ready, i.e. a username and a window has been selected; false otherwise.
     */

    public boolean isReady() {
        return ready;
    }

    /**
     *
     * @param ready whether the player is ready, i.e. a username and a window has been selected
     */

    public void setReady(boolean ready) {
        this.ready = ready;
    }

    /**
     *
     * @return true if the player has already completed his first turn of the round; false otherwise.
     */

    public boolean isFirstTurnDone() {
        return firstTurnDone;
    }

    /**
     *
     * @param firstTurnDone whether the player has already completed his first turn of the round.
     */

    public void setFirstTurnDone(boolean firstTurnDone) {
        this.firstTurnDone = firstTurnDone;
    }

    /**
     *
     * @return true if the player has already completed his second turn of the round; false otherwise.
     */

    public boolean isSecondTurnDone() {
        return secondTurnDone;
    }

    /**
     *
     * @param secondTurnDone whether the player has already completed his second turn of the round.
     */

    public void setSecondTurnDone(boolean secondTurnDone) {
        this.secondTurnDone = secondTurnDone;
    }

    /**
     * Sets dieUsed and toolCardUsed to false.
     */

    public void resetTurn(){
        dieUsed = false;
        toolCardUsed = false;
    }

    /**
     * Sets firstTurnDone and secondTurnDone to false.
     */

    public void resetRound(){
        firstTurnDone = false;
        secondTurnDone = false;
    }

    /**
     * Returns the points from the private objective of the player.
     * @return the points from the private objective as an integer.
     */

    public int getPrivatePoints() {
       return playerObjective.checkPoints(playerWindow);
    }

    /**
     * Returns the points from the private objective of the player,
     * minus the number empty slots left in his window pattern.
     * @return the points from the private objective, minus the number of empty slots.
     */

    public int getTotalPoints() {
        int emptySpace = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 4; j++) {
                if (!this.getPlayerWindow().getWindowMatrix(i, j).isNotEmpty()) emptySpace++;
            }
        }
        return  this.getPrivatePoints() + this.getPlayerWindow().getDifficultyToken() - emptySpace ;
    }

    /**
     * Returns the String representation of the class.
     * @return the String representation of the class.
     */

    @Override
    public String toString() {
        String s;
        if(isOnline) s = "online";
        else s = "offline";
        return "Player: " + username + " (" + s + ")\n" + playerWindow + "\u001B[0m";
    }

    /**
     * Prints the class.
     */

    public void dump(){
        System.out.println(this.toString());
    }

}