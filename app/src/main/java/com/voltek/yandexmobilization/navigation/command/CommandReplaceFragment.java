package com.voltek.yandexmobilization.navigation.command;

import com.voltek.yandexmobilization.navigation.proxy.NavigatorCommand;

public class CommandReplaceFragment extends NavigatorCommand {

    public static final int id = 10;

    private int fragmentIndex;

    public CommandReplaceFragment(int fragmentIndex) {
        super(id);
        this.fragmentIndex = fragmentIndex;
    }

    public CommandReplaceFragment(int fragmentIndex, boolean addToQueue) {
        super(id, addToQueue);
        this.fragmentIndex = fragmentIndex;
    }

    public int getFragmentIndex() {
        return fragmentIndex;
    }
}
