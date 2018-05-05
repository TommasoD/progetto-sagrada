package it.polimi.ingsw;

public class Player {

    //variables
    private String username;
    private WindowPattern playerWindow;
    private PrivateObjective playerObjective;
    private boolean isOnline;

    //costruttore
    public Player(String username, WindowPattern playerWindow) {
        this.username = username;
        this.isOnline = true;
        this.playerWindow = playerWindow;
    }

    //methods
    public String getUsername() {
        return username;
    }

    public WindowPattern getPlayerWindow() {
        return playerWindow;
    }

    public void  setPlayerObjective (PrivateObjective playerObjective ) {
        this.playerObjective = playerObjective;
    }

    public PrivateObjective getPlayerObjective () {
        return  this.playerObjective;
    }

    public boolean getOnline() {
        return isOnline;
    }

    public void setOnline(boolean status) {
        this.isOnline = status;
    }


    @Override
    public String toString() {
        String s = this.username;
        boolean s2 = this.isOnline;
        return "Player: " + s + " | Online: " + s2;
    }
}