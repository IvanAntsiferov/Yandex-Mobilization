package com.voltek.yandex.mobilization.navigation.command;

import com.voltek.yandex.mobilization.navigation.proxy.NavigatorCommand;

public class CommandReplaceFragment extends NavigatorCommand {

    public static final int id = 10;

    private int currentIndex;

    private int previousIndex;

    public CommandReplaceFragment(int currentIndex, int previousIndex) {
        super(id);
        this.currentIndex = currentIndex;
        this.previousIndex = previousIndex;
    }

    public CommandReplaceFragment(boolean addToQueue, int currentIndex, int previousIndex) {
        super(id, addToQueue);
        this.currentIndex = currentIndex;
        this.previousIndex = previousIndex;
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public int getPreviousIndex() {
        return previousIndex;
    }
}
