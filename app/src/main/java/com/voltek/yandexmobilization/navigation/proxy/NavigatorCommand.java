package com.voltek.yandexmobilization.navigation.proxy;

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
