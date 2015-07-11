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
    MainActivity mainActivity;



    int setting_playerNr=5;
    int setting_deckNr=2;
    int setting_pointBreak=100;
    int setting_pointJump=40;

    int currentGameGroup;

    boolean isNewGame=false;
    int points;
    List<Long> gamePlayerIDList;
    List<Long> gameDealerHelperiDList;

    public Long getGameDealerID() {
        return gameDealerID;
    }

    public void setGameDealerID(Long gameDealerID) {
        this.gameDealerID = gameDealerID;
    }

    public boolean isNewGame() {
        return isNewGame;
    }

    public void setIsNewGame(boolean isNewGame) {
        this.isNewGame = isNewGame;
    }

    public int getGamePoints() {
        return points;
    }

    public void setGamePoints(int points) {
        this.points = points;
    }

    public List<Long> getGamePlayerIDList() {
        return gamePlayerIDList;
    }

    public void setGamePlayerIDList(List<Long> gamePlayerIDList) {
        this.gamePlayerIDList = gamePlayerIDList;
    }

    public List<Long> getGameDealerHelperiDList() {
        return gameDealerHelperiDList;
    }

    public void setGameDealerHelperiDList(List<Long> gameDealerHelperiDList) {
        this.gameDealerHelperiDList = gameDealerHelperiDList;
    }

    Long gameDealerID;
    public int getCurrentGameGroup() {
        return currentGameGroup;
    }

    public void setCurrentGameGroup(int currentGameGroup) {
        this.currentGameGroup = currentGameGroup;
    }

    public int getCurrentGameNr() {
        return currentGameNr;
    }

    public void setCurrentGameNr(int currentGameNr) {
        this.currentGameNr = currentGameNr;
    }

    int currentGameNr;

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

    public MainActivity getMainActivity() {
        return mainActivity;
    }

    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }
}
