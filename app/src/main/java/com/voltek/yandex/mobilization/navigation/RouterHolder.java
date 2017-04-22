package com.voltek.yandex.mobilization.navigation;

import com.voltek.yandex.mobilization.navigation.proxy.Navigator;
import com.voltek.yandex.mobilization.navigation.proxy.NavigatorCommand;
import com.voltek.yandex.mobilization.navigation.proxy.RouterBinder;
import com.voltek.yandex.mobilization.navigation.proxy.RouterBus;

import java.util.ArrayList;

import timber.log.Timber;

/**
 * Controls app navigation.
 * RouterBinder used to bind activity with implemented Navigator interface,
 * RouterBus used to queue navigation events from presenters.
 */
public class RouterHolder implements RouterBus, RouterBinder {

    private ArrayList<NavigatorCommand> commandsQueue = new ArrayList<>();

    private Navigator navigator = null;

    @Override
    public void setNavigator(Navigator navigator) {
        Timber.d("setNavigator");
        this.navigator = navigator;
        executeQueue();
    }

    @Override
    public void removeNavigator() {
        Timber.d("removeNavigator");
        this.navigator = null;
    }

    @Override
    public void execute(NavigatorCommand command) {
        Timber.d("execute, id: " + command.getId());
        if (navigator == null || !navigator.executeCommand(command)) {
            if (command.isAddToQueue())
                commandsQueue.add(command);
        }
    }

    private void executeQueue() {
        Timber.d("executeQueue, size: " + commandsQueue.size());
        for (NavigatorCommand command : commandsQueue) {
            if (navigator == null) return;
            if (navigator.executeCommand(command)) commandsQueue.remove(command);
        }
    }
}
