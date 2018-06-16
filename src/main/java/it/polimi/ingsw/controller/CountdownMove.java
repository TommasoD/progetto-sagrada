package it.polimi.ingsw.controller;

import it.polimi.ingsw.messages.client.NotificationMessage;
import it.polimi.ingsw.messages.controller.LogoutMessage;
import it.polimi.ingsw.parsers.SetupParser;

public class CountdownMove extends Thread {

    /** Save the time istant when you stop */
    private long stopTempo;

    /** Real begin time */
    private long currentTempo;

    /** On/Off of the timer */
    private boolean isActive;

    private boolean done;

    private int maxTime;

    private boolean gameEnded;

    private Controller controller;

    private int playerIndex;

    public CountdownMove(Controller controller) {
        SetupParser reader = new SetupParser();
        reader.readSetup();
        maxTime = reader.getCountdownMove();
        resetAndStop();
        done = false;
        this.controller = controller;
    }

    public int getMaxTime() {
        return maxTime;
    }

    public void setDone() {
        done = true;
    }

    public void wakeUp(int index) {
        this.playerIndex = index;
    }

    public void setGameEnded() {
        gameEnded = true;
    }

    /**Reset and stop the timer */
    public void resetAndStop() {
        synchronized (this) {
            stopTempo = 0;
            isActive = false;
        }
    }

    /** turn on the timer that begin from the last stop */
    public void resumeClock() {
        synchronized (this) {
            currentTempo = System.currentTimeMillis();
            isActive = true;
        }
    }

    /** stop the timer */
    public void stopClock() {
        synchronized (this) {
            stopTempo += System.currentTimeMillis() - currentTempo;
            isActive = false;
        }
    }

    /** reset and start the timer */
    public void reset() {
        resetAndStop();
        resumeClock();
    }

    /** return current time in milliseconds */
    public long read() {
        synchronized (this) {
            return isActive ? (stopTempo + System.currentTimeMillis() - currentTempo)
                    : stopTempo;
        }
    }

    public void run() {

        gameEnded = false;
        done = false;

        this.reset();
        while(this.read() < maxTime && !done) {}

        controller.startMatch();
        done = false;

        while(!gameEnded) {
            this.reset();
            while(this.read() <maxTime&& !done) {}
            this.stopClock();
            if(this.read() >= maxTime) {
                controller.setPlayerOffline(controller.getGame().getPlayers(playerIndex).getId(), "suspended");
                controller.nextPlayer(controller.getGame().getPlayers(playerIndex).getId());
            }
            done = false;
        }

    }

    /** return current time in seconds */
    @Override
    public String toString() {
        return "" + read()/1000;
    }

}
