package it.polimi.ingsw.controller;
import it.polimi.ingsw.parsers.SetupParser;

/**
 * Class used to count how much time a player waits to make a move.
 * If a player waits too much time, this class sets him offline and
 * the round switches on the next player.
 */
public class CountdownMove extends Thread {

    private long stopTempo;
    private long currentTempo;
    private boolean isActive;
    private boolean done;
    private int maxTime;
    private Controller controller;
    private int playerIndex;

    /**
     * Constructs a CountdownMove from an already existing Controller.
     * @param controller an existing Controller.
     */

    public CountdownMove(Controller controller) {
        SetupParser reader = new SetupParser();
        reader.readSetup();
        maxTime = reader.getCountdownMove();
        resetAndStop();
        done = false;
        this.controller = controller;
    }

    /**
     * Set true the variable done.
     */

    public void setDone() {
        done = true;
    }


    /**
     * Save the index of the current player.
     * @param index the index of the current player.
     */

    public void wakeUp(int index) {
        this.playerIndex = index;
    }

    /**
     * Reset and stop the timer.
     */

    private void resetAndStop() {
        synchronized (this) {
            stopTempo = 0;
            isActive = false;
        }
    }

    /**
     * Turn on the timer. It will restart from the last stop.
     */

    private void resumeClock() {
        synchronized (this) {
            currentTempo = System.currentTimeMillis();
            isActive = true;
        }
    }

    /**
     * Stop the timer.
     */

    private void stopClock() {
        synchronized (this) {
            stopTempo += System.currentTimeMillis() - currentTempo;
            isActive = false;
        }
    }

    /**
     * Restart the timer.
     */

    private void reset() {
        resetAndStop();
        resumeClock();
    }

    /**
     *
     * @return current time in milliseconds.
     */

    private long read() {
        synchronized (this) {
            return isActive ? (stopTempo + System.currentTimeMillis() - currentTempo)
                    : stopTempo;
        }
    }

    /**
     * The first while is for login phase (username inserting - window choice).
     * When all the players have chosen a window,
     * the controller sets true the variable done and the match starts.
     * If a player waits too much time, the match starts anyway and
     * he will have a random window and a eventual random username.
     * The second while is for move phase.
     * When a player make a move, the controller sets true the variable done and
     * the time will be reset for the next move.
     * If a player waits too much time, this class sets him offline and
     * the round switches on the next player.
     */

    @Override
    public void run() {

        done = false;

        this.reset();
        while(this.read() < maxTime && !done) {}

        controller.startMatch();
        done = false;

        while(!controller.getGame().isGameEnded()) {
            this.reset();
            while(this.read() < maxTime && !done) {}
            this.stopClock();
            if(this.read() >= maxTime && !controller.getGame().isGameEnded()) {
                controller.setPlayerOffline(controller.getGame().getPlayers(playerIndex).getId(), "suspended");
                controller.nextPlayer(controller.getGame().getPlayers(playerIndex).getId());
            }
            done = false;
        }

    }

    /**
     *
     * @return current time in seconds.
     */
    @Override
    public String toString() {
        return "" + read()/1000;
    }

}
