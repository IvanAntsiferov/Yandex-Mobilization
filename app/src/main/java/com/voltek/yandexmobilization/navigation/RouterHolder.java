package com.voltek.yandexmobilization.navigation;

import com.voltek.yandexmobilization.navigation.proxy.Navigator;
import com.voltek.yandexmobilization.navigation.proxy.NavigatorCommand;
import com.voltek.yandexmobilization.navigation.proxy.RouterBinder;
import com.voltek.yandexmobilization.navigation.proxy.RouterBus;

import java.util.ArrayList;

public class RouterHolder implements RouterBus, RouterBinder {

    private ArrayList<NavigatorCommand> commandsQueue = new ArrayList<>();

    private Navigator navigator = null;

    @Override
    public void setNavigator(Navigator navigator) {
        this.navigator = navigator;
        executeQueue();
    }

    @Override
    public void removeNavigator() {
        this.navigator = null;
    }

    @Override
    public void execute(NavigatorCommand command) {
        if (navigator == null || !navigator.executeCommand(command)) {
            if (command.isAddToQueue())
                commandsQueue.add(command);
        }
    }

    private void executeQueue() {
        for (NavigatorCommand command : commandsQueue) {
            if (navigator == null) return;
            if (navigator.executeCommand(command)) commandsQueue.remove(command);
        }
    }
}
