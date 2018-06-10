package it.polimi.ingsw.controller;

public class RoundHandler {

    private int round;
    private int currentPlayerIndex;
    private int firstPlayer;
    private int nPlayers;
    private int[] turnOrder;

    /*
        constructor
     */

    public RoundHandler(int n){
        round = 1;
        firstPlayer = 0;
        currentPlayerIndex = 0;
        nPlayers = n;
        turnOrder = new int[2*n];

        for(int i = 0; i < nPlayers; i++){
            turnOrder[i] = i;
        }
        for(int i = 0; i < nPlayers; i++){
            turnOrder[nPlayers+i] = nPlayers-1-i;
        }
    }

    /*
        getters and setters
     */

    public int getRound() {
        return round;
    }

    public void setCurrentPlayerIndex(int currentPlayerIndex) {
        this.currentPlayerIndex = currentPlayerIndex;
    }

    public int getFirstPlayer(){
        return firstPlayer;
    }

    /*
        returns - as an int - the identifier of the current player
     */

    public int getCurrentPlayer(){
        return turnOrder[currentPlayerIndex];
    }

    /*
        sets the next player as the current player and calls nextRound() if round has ended
     */

    public void nextTurn() throws NewRoundException{
        currentPlayerIndex ++;
        if(currentPlayerIndex >= 2*nPlayers) {
            nextRound();
            currentPlayerIndex = 0;
            throw new NewRoundException(round);
        }
    }

    /*
        increase round and create the new turnOrder array
     */

    public void nextRound(){
        round++;
        System.arraycopy(turnOrder,1, turnOrder, 0, nPlayers-1);
        System.arraycopy(turnOrder, nPlayers, turnOrder, nPlayers+1, nPlayers-1);
        turnOrder[nPlayers-1] = firstPlayer;
        turnOrder[nPlayers] = firstPlayer;
        firstPlayer ++;
        if(firstPlayer >= nPlayers) firstPlayer = 0;
    }

    /*
        checks if game is ended (i.e. round 10 has concluded)
     */

    public boolean isGameEnded(){
        return round > 10;
    }

    public void dump(){
        for(int i = 0; i < 2*nPlayers; i++){
            System.out.println(turnOrder[i]);
        }
    }

}
