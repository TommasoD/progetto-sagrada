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

    public int getPrivatePoints() {
       return playerObjective.checkPoints(playerWindow);
    }

    //totale meno obiettivi pubblici
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
        String s = this.username;
        boolean s2 = this.isOnline;
        return "Player: " + s + " | Online: " + s2;
    }
}