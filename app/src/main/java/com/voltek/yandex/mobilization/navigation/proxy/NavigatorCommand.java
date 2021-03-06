package com.voltek.yandex.mobilization.navigation.proxy;

/**
 * Base class for navigation commands. Used by classes, that implements Navigator interface,
 * to determine which type of command was received.
 */
public abstract class NavigatorCommand {

    private int id;

    private boolean addToQueue = true;

    public NavigatorCommand(int id) {
        this.id = id;
    }

    public NavigatorCommand(int id, boolean addToQueue) {
        this.id = id;
        this.addToQueue = addToQueue;
    }

    public int getId() {
        return id;
    }

    public boolean isAddToQueue() {
        return addToQueue;
    }
}
