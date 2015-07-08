package com.brettyin.cardshelper.model;

import android.database.sqlite.SQLiteDatabase;

import java.util.Map;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.identityscope.IdentityScopeType;
import de.greenrobot.dao.internal.DaoConfig;

import com.brettyin.cardshelper.model.Player;
import com.brettyin.cardshelper.model.Game;
import com.brettyin.cardshelper.model.GameToPlayer;

import com.brettyin.cardshelper.model.PlayerDao;
import com.brettyin.cardshelper.model.GameDao;
import com.brettyin.cardshelper.model.GameToPlayerDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see de.greenrobot.dao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig playerDaoConfig;
    private final DaoConfig gameDaoConfig;
    private final DaoConfig gameToPlayerDaoConfig;

    private final PlayerDao playerDao;
    private final GameDao gameDao;
    private final GameToPlayerDao gameToPlayerDao;

    public DaoSession(SQLiteDatabase db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        playerDaoConfig = daoConfigMap.get(PlayerDao.class).clone();
        playerDaoConfig.initIdentityScope(type);

        gameDaoConfig = daoConfigMap.get(GameDao.class).clone();
        gameDaoConfig.initIdentityScope(type);

        gameToPlayerDaoConfig = daoConfigMap.get(GameToPlayerDao.class).clone();
        gameToPlayerDaoConfig.initIdentityScope(type);

        playerDao = new PlayerDao(playerDaoConfig, this);
        gameDao = new GameDao(gameDaoConfig, this);
        gameToPlayerDao = new GameToPlayerDao(gameToPlayerDaoConfig, this);

        registerDao(Player.class, playerDao);
        registerDao(Game.class, gameDao);
        registerDao(GameToPlayer.class, gameToPlayerDao);
    }
    
    public void clear() {
        playerDaoConfig.getIdentityScope().clear();
        gameDaoConfig.getIdentityScope().clear();
        gameToPlayerDaoConfig.getIdentityScope().clear();
    }

    public PlayerDao getPlayerDao() {
        return playerDao;
    }

    public GameDao getGameDao() {
        return gameDao;
    }

    public GameToPlayerDao getGameToPlayerDao() {
        return gameToPlayerDao;
    }

}