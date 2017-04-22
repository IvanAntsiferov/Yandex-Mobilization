package com.voltek.yandex.mobilization.navigation.command;

import com.voltek.yandex.mobilization.navigation.proxy.NavigatorCommand;

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
