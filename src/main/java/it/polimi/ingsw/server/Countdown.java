package it.polimi.ingsw.server;

import it.polimi.ingsw.parsers.SetupParser;

import java.io.IOException;
import java.net.Socket;

/**
 * Used at the beginning, first of the clients login.
 * Counts how much time is passed from a new client connection.
 * If too much time passes, the game will start with less than four players.
 */
public class Countdown extends Thread {

    private long stopTempo;
    private long currentTempo;
    private boolean isActive;
    private boolean done;
    private int maxTime;

    /**
     * Class constructor.
     */

    public Countdown() {
        SetupParser reader = new SetupParser();
        reader.readSetup();
        maxTime = reader.getCountdownConnection();
        resetAndStop();
        done = false;
    }

    /**
     *
     * @return the max time to wait.
     */

    public int getMaxTime() {
        return maxTime;
    }

    /**
     * Reset and stop the timer.
     */

    public void resetAndStop() {
        synchronized (this) {
            stopTempo = 0;
            isActive = false;
        }
    }

    /**
     * Turn on the timer. It will restart from the last stop.
     */

    public void resumeClock() {
        synchronized (this) {
            currentTempo = System.currentTimeMillis();
            isActive = true;
        }
    }

    /**
     * Stop the timer.
     */

    public void stopClock() {
        synchronized (this) {
            stopTempo += System.currentTimeMillis() - currentTempo;
            isActive = false;
        }
    }

    /**
     * Restart the timer.
     */

    public void reset() {
        resetAndStop();
        resumeClock();
    }

    /**
     *
     * @return current time in milliseconds.
     */

    public long read() {
        synchronized (this) {
            return isActive ? (stopTempo + System.currentTimeMillis() - currentTempo)
                    : stopTempo;
        }
    }

    /**
     * If gameRoom sets true the variable done,
     * this method will finish without calls serverBreak method
     * because there are four players in this case.
     * If too much time passes, the game begin with less than
     * four players and this class calls serverBreak method.
     */

    @Override
    public void run() {

        while((this.read() < maxTime) && (!done)) {
            if(isActive) {
                System.out.print("\r" + this.toString());
                try {
                    Thread.sleep(100);
                } catch(InterruptedException e) {
                    System.out.println("Countdown thread interrupted");
                    Thread.currentThread().interrupt();
                }
            }
        }
        this.stopClock();
        if(this.read() >= maxTime) {
            System.out.print("\n");
            try {
                this.serverBreak();
            } catch (IOException e) {
                System.out.println("IOException in countdown");
            }
        }

    }

    /**
     * If too much time passes and there are less than four players
     * the server is blocked on serverSocket.accept(), so this method
     * create a new Socket to unlock the server, then this Socket
     * will be closed.
     */

    private void serverBreak() throws IOException {
        SetupParser reader = new SetupParser();
        reader.readSetup();
        Socket socket = new Socket(reader.getIp(), reader.getPort());
        socket.close();
    }

    /**
     *
     * @return current time in seconds.
     */

    public String toString() {
        return "" + read()/1000;
    }

    /**
     * Set true the variable done.
     */

    public void setDone() {
        done = true;
    }
}
