package com.brettyin.cardshelper;

import android.app.Application;

import com.brettyin.cardshelper.model.DaoSession;

import java.util.List;

/**
 * Created by XYin on 08.07.2015.
 */
public class MyApplication extends Application {
    private List<Long> playerIDList;

    DaoSession daoSession;

    int setting_playerNr;
    int setting_deckNr;
    int setting_pointBreak;
    int setting_pointJump;

    public int getSetting_playerNr() {
        return setting_playerNr;
    }

    public void setSetting_playerNr(int setting_playerNr) {
        this.setting_playerNr = setting_playerNr;
    }

    public int getSetting_deckNr() {
        return setting_deckNr;
    }

    public void setSetting_deckNr(int setting_deckNr) {
        this.setting_deckNr = setting_deckNr;
    }

    public int getSetting_pointBreak() {
        return setting_pointBreak;
    }

    public void setSetting_pointBreak(int setting_pointBreak) {
        this.setting_pointBreak = setting_pointBreak;
    }

    public int getSetting_pointJump() {
        return setting_pointJump;
    }

    public void setSetting_pointJump(int setting_pointJump) {
        this.setting_pointJump = setting_pointJump;
    }

    public List<Long> getPlayerIDList() {
        return playerIDList;
    }

    public void setPlayerIDList(List<Long> playerIDList) {
        this.playerIDList = playerIDList;
    }



    public DaoSession getDaoSession() {
        return daoSession;
    }

    public void setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
    }
}
