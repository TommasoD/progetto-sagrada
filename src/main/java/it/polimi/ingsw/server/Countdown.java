package it.polimi.ingsw.server;

import it.polimi.ingsw.parsers.NetworkParser;

import java.io.IOException;
import java.net.Socket;

public class Countdown extends Thread {

    /** Save the time istant when you stop */
    private long stopTempo;

    /** Real begin time */
    private long currentTempo;

    /** On/Off of the timer */
    private boolean isActive;

    private boolean done;

    private static final int MAX_TIME = 30000;

    public Countdown() {
        resetAndStop();
        done = false;
    }

    public int getMaxTime() {
        return MAX_TIME;
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

        //this.reset();
        while((this.read() < MAX_TIME) && (!done)) {
            if (isActive) System.out.print("\r" + this);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.exit(1);
            }
        }
        this.stopClock();
        if(this.read() >= MAX_TIME) {
            try {
                this.serverBreak();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private void serverBreak() throws IOException {
        NetworkParser reader = new NetworkParser();
        reader.readNetworkSetup();
        Socket socket = new Socket(reader.getIp(), reader.getPort());
        socket.close();
    }

    /** return current time in seconds */
    public String toString() {
        return "" + read()/1000;
    }

    public void setDone() {
        done = true;
    }
}
