package it.polimi.ingsw.utils;

/**
 * Implements different methods to manage an observed event depicted
 * by a message received from the an observable class.
 * The basis of the observer pattern together with the Observable class.
 * @param <T>
 */
public interface Observer<T> {

    /**
     * Does something when notified by an observable class.
     * @param message the message depicting the event.
     */

    void update(T message);

    /**
     * Does something when notified by an observable class.
     * @param message the message depicting the event.
     * @param id the id of the player associated with the event.
     */

    void update(T message, int id);

}