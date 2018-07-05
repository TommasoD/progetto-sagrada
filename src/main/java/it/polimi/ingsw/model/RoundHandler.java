package it.polimi.ingsw.model;

import it.polimi.ingsw.controller.NewRoundException;

/**
 * Represents the logic of rounds and turns according to the rules.
 * The game consists of 10 rounds, each round consists of 2 turns for each player,
 * the rotation of the turns follow the pattern A B C D D C B A.
 * After every round, the second player becomes the first player and the first
 * player from before becomes the last player, i.e. the one playing two turns
 * in series in the middle of the round.
 */
public class RoundHandler {

    private int round;
    private int currentPlayerIndex;
    private int firstPlayer;
    private int nPlayers;
    private int[] turnOrder;

    private static final int MAX_ROUND = 10;

    /**
     * Constructor specifying the number of players.
     * @param n the number of players.
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

    /**
     *
     * @return the current round as an integer.
     */

    public int getRound() {
        return round;
    }

    /**
     *
     * @param currentPlayerIndex the index of the current player in the list of turns.
     */

    public void setCurrentPlayerIndex(int currentPlayerIndex) {
        this.currentPlayerIndex = currentPlayerIndex;
    }

    /**
     *
     * @return the first player of the round.
     */

    public int getFirstPlayer(){
        return firstPlayer;
    }

    /**
     *
     * @return the list of turns for the current round.
     */

    public int[] getTurnOrder() {
        return turnOrder;
    }

    /**
     * Returns the index of the current player in the list of players,
     * i.e the identifier of the class corresponding to the current player.
     * @return the index of the current player in the list of players.
     */

    public int getCurrentPlayer(){
        return turnOrder[currentPlayerIndex];
    }

    /**
     * Sets the next player of the round as the current player and
     * handles the start of a new round in case the current one is ended.
     * @throws NewRoundException if the current round is ended.
     */

    public void nextTurn() throws NewRoundException {
        currentPlayerIndex ++;
        if(currentPlayerIndex >= 2*nPlayers) {
            nextRound();
            currentPlayerIndex = 0;
            throw new NewRoundException(round);
        }
    }

    /**
     * Increases the round counter and update the list containing the order of turns until the end of the game.
     */

    public void nextRound(){
        round++;
        if(!isGameEnded()){
            System.arraycopy(turnOrder,1, turnOrder, 0, nPlayers-1);
            System.arraycopy(turnOrder, nPlayers, turnOrder, nPlayers+1, nPlayers-1);
            turnOrder[nPlayers-1] = firstPlayer;
            turnOrder[nPlayers] = firstPlayer;
            firstPlayer ++;
            if(firstPlayer >= nPlayers) firstPlayer = 0;
        }
    }

    /**
     * Returns true if the game is ended, i.e. the last round has concluded.
     * @return true if the game is ended; false otherwise.
     */

    public boolean isGameEnded(){
        return round > MAX_ROUND;
    }

    /**
     * Prints the list containing the order of turns.
     */

    public void dump(){
        for(int i = 0; i < 2*nPlayers; i++){
            System.out.println(turnOrder[i]);
        }
    }

}
