package it.polimi.ingsw.controller;

public class CountdownMove extends Thread {

    /** Save the time istant when you stop */
    private long stopTempo;

    /** Real begin time */
    private long currentTempo;

    /** On/Off of the timer */
    private boolean isActive;

    private boolean done;

    private static final int MAX_TIME = 60000;

    private boolean gameEnded;

    private boolean wait;

    private Controller controller;

    private int idPlayer;

    public CountdownMove(Controller controller) {
        resetAndStop();
        done = false;
        this.controller = controller;
    }

    public int getMaxTime() {
        return MAX_TIME;
    }

    public void setDone() {
        done = true;
    }

    public void wakeUp() {
        wait = false;
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
        wait = true;
        done = false;

        while(!gameEnded) {
            while(wait && !gameEnded) {}
            this.reset();
            while(this.read() < MAX_TIME && !done) {}
            this.stopClock();
            if(this.read() >= MAX_TIME) {
                controller.getGame().getPlayers(idPlayer).setOnline(false);
                controller.nextPlayer(idPlayer);
            }
            wait = true;
            done = false;
        }

    }

    public void setIdPlayer(int index) {
        this.idPlayer = index;
    }

    /** return current time in seconds */
    public String toString() {
        return "" + read()/1000;
    }

}
