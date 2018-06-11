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

    private static int max_time;

    private boolean gameEnded;

    private Controller controller;

    private int playerIndex;

    public CountdownMove(Controller controller) {
        SetupParser reader = new SetupParser();
        reader.readSetup();
        max_time = reader.getCountdownMove();
        resetAndStop();
        done = false;
        this.controller = controller;
    }

    public int getMaxTime() {
        return max_time;
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
        while(this.read() < max_time && !done) {}

        controller.startMatch();
        done = false;

        while(!gameEnded) {
            this.reset();
            while(this.read() <max_time&& !done) {}
            this.stopClock();
            if(this.read() >= max_time) {
                controller.getGame().getPlayers(playerIndex).setOnline(false);
                controller.getGame().notifyAllPlayers(new NotificationMessage(controller.getGame().getPlayers(playerIndex).getUsername(), "suspended"));
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
