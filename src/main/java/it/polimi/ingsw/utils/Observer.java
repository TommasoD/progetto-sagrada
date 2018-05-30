package it.polimi.ingsw.utils;


public interface Observer<T> {

    void update(T message);

    void update(T message, int id);

}