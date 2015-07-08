package com.brettyin.cardshelper;

import android.app.Application;

import java.util.List;

/**
 * Created by XYin on 08.07.2015.
 */
public class MyApplication extends Application {

    public List<Long> getPlayerIDList() {
        return playerIDList;
    }

    public void setPlayerIDList(List<Long> playerIDList) {
        this.playerIDList = playerIDList;
    }

    private List<Long> playerIDList;
}
