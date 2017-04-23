package com.voltek.yandex.mobilization.navigation;

import com.voltek.yandex.mobilization.navigation.proxy.Navigator;
import com.voltek.yandex.mobilization.navigation.proxy.NavigatorCommand;
import com.voltek.yandex.mobilization.navigation.proxy.RouterBinder;
import com.voltek.yandex.mobilization.navigation.proxy.RouterBus;

import timber.log.Timber;

/**
 * Controls app navigation.
 * RouterBinder used to bind activity with implemented Navigator interface,
 * RouterBus used to queue navigation events from presenters.
 * (In this case, queue contains only one last command)
 */
public class RouterHolder implements RouterBus, RouterBinder {

    private NavigatorCommand commandsQueue = null;

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
                commandsQueue = command;
        }
    }

    private void executeQueue() {
        Timber.d("executeQueue");
        if (navigator != null && commandsQueue != null) {
            if (navigator.executeCommand(commandsQueue)) {
                commandsQueue = null;
            }
        }
    }
}
