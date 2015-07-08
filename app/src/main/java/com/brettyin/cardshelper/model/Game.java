package com.brettyin.cardshelper.model;

import java.util.List;
import com.brettyin.cardshelper.model.DaoSession;
import de.greenrobot.dao.DaoException;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table GAME.
 */
public class Game {

    private Long id;
    private Integer Score;
    private java.util.Date Date;

    /** Used to resolve relations */
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    private transient GameDao myDao;

    private List<GameToPlayer> gameToPlayerList;

    public Game() {
    }

    public Game(Long id) {
        this.id = id;
    }

    public Game(Long id, Integer Score, java.util.Date Date) {
        this.id = id;
        this.Score = Score;
        this.Date = Date;
    }

    /** called by internal mechanisms, do not call yourself. */
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getGameDao() : null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getScore() {
        return Score;
    }

    public void setScore(Integer Score) {
        this.Score = Score;
    }

    public java.util.Date getDate() {
        return Date;
    }

    public void setDate(java.util.Date Date) {
        this.Date = Date;
    }

    /** To-many relationship, resolved on first access (and after reset). Changes to to-many relations are not persisted, make changes to the target entity. */
    public List<GameToPlayer> getGameToPlayerList() {
        if (gameToPlayerList == null) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            GameToPlayerDao targetDao = daoSession.getGameToPlayerDao();
            List<GameToPlayer> gameToPlayerListNew = targetDao._queryGame_GameToPlayerList(id);
            synchronized (this) {
                if(gameToPlayerList == null) {
                    gameToPlayerList = gameToPlayerListNew;
                }
            }
        }
        return gameToPlayerList;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    public synchronized void resetGameToPlayerList() {
        gameToPlayerList = null;
    }

    /** Convenient call for {@link AbstractDao#delete(Object)}. Entity must attached to an entity context. */
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.delete(this);
    }

    /** Convenient call for {@link AbstractDao#update(Object)}. Entity must attached to an entity context. */
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.update(this);
    }

    /** Convenient call for {@link AbstractDao#refresh(Object)}. Entity must attached to an entity context. */
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.refresh(this);
    }

}