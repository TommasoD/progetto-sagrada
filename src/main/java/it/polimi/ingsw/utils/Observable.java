package it.polimi.ingsw.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Contains a list of all the observers of a class and different methods to notify them.
 * The basis of the observer pattern together with the Observer interface.
 * @param <T> the type of the observed objects.
 */
public abstract class Observable<T> {

    private final List<Observer<T>> observers = new ArrayList<Observer<T>>();

    /**
     * Adds an observer to the list of all the observers.
     * @param observer the observer.
     */

    public void register(Observer<T> observer){
        synchronized (observers) {
            observers.add(observer);
        }
    }

    /**
     * Removes an observer from the list.
     * @param observer the observer.
     */

    public void deregister(Observer<T> observer){
        synchronized (observers) {
            observers.remove(observer);
        }
    }

    /**
     * Notify all the observers of a certain event.
     * @param message the message depicting the event.
     */

    protected void notify(T message){
        synchronized (observers) {
            for(Observer<T> observer : observers){
                observer.update(message);
            }
        }
    }

    /**
     * Notify all the observers of a certain event.
     * @param message the message depicting the event.
     * @param id the id of the player associated with the event.
     */

    protected void notify(T message, int id){
        synchronized (observers) {
            for(Observer<T> observer : observers){
                observer.update(message, id);
            }
        }
    }

}