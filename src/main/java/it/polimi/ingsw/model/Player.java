package it.polimi.ingsw.model;

import it.polimi.ingsw.model.objectives.PrivateObjective;

public class Player {

    //attributes
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

    /*
        constructors
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

    /*
        getters and setters
     */


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public WindowPattern getPlayerWindow() {
        return playerWindow;
    }

    public void setPlayerWindow(WindowPattern playerWindow) {
        this.playerWindow = playerWindow;
    }

    public void  setPlayerObjective (PrivateObjective playerObjective ) {
        this.playerObjective = playerObjective;
    }

    public PrivateObjective getPlayerObjective () {
        return  this.playerObjective;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean status) {
        this.isOnline = status;
    }
    public boolean isDieUsed() {
        return dieUsed;
    }

    public void setDieUsed(boolean dieUsed) {
        this.dieUsed = dieUsed;
    }

    public boolean isToolCardUsed() {
        return toolCardUsed;
    }

    public void setToolCardUsed(boolean toolCardUsed) {
        this.toolCardUsed = toolCardUsed;
    }

    public boolean isFirstDiePlaced() {
        return firstDiePlaced;
    }

    public void setFirstDiePlaced(boolean firstDiePlaced) {
        this.firstDiePlaced = firstDiePlaced;
    }

    public boolean isReady() {
        return ready;
    }

    public void setReady(boolean ready) {
        this.ready = ready;
    }

    public boolean isFirstTurnDone() {
        return firstTurnDone;
    }

    public void setFirstTurnDone(boolean firstTurnDone) {
        this.firstTurnDone = firstTurnDone;
    }

    public boolean isSecondTurnDone() {
        return secondTurnDone;
    }

    public void setSecondTurnDone(boolean secondTurnDone) {
        this.secondTurnDone = secondTurnDone;
    }

    /*
        sets dieUsed and toolCardUsed to false
     */

    public void resetTurn(){
        dieUsed = false;
        toolCardUsed = false;
    }

    /*
       private objective points
     */

    public int getPrivatePoints() {
       return playerObjective.checkPoints(playerWindow);
    }

    /*
        total minus public objectives
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

    @Override
    public String toString() {
        String s;
        if(isOnline) s = "online";
        else s = "offline";
        return "Player: " + username + "\nStatus: " + s + "\n" + playerWindow + "\u001B[0m";
    }

    public void dump(){
        System.out.println(this.toString());
    }

}