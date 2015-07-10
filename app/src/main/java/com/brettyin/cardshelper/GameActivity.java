package com.brettyin.cardshelper;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.brettyin.cardshelper.model.DaoMaster;
import com.brettyin.cardshelper.model.DaoSession;
import com.brettyin.cardshelper.model.Game;
import com.brettyin.cardshelper.model.GameDao;
import com.brettyin.cardshelper.model.GameToPlayer;
import com.brettyin.cardshelper.model.GameToPlayerDao;
import com.brettyin.cardshelper.model.PlayerDao;

import java.util.List;

public class GameActivity extends AppCompatActivity {
    GameToPlayerDao gameToPlayerDao;
    List<GameToPlayer> gameList;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        recyclerView = (RecyclerView) findViewById(R.id.gameList);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);

        initialDB();
        GameAdapter ca = new GameAdapter(this, gameList);
        recyclerView.setAdapter(ca);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    void refreshGamrList()
    {
        if (gameToPlayerDao==null)
            initialDB();
        else
        {
            gameList = gameToPlayerDao.loadAll();
            GameAdapter gameAdaptera = new GameAdapter(this, gameList);
            recyclerView.setAdapter(gameAdaptera);
        }
    }
    void initialDB()
    {
        DaoSession daoSession= ((MyApplication) this.getApplicationContext()).getDaoSession();
        if (daoSession==null) {
            DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "cards-db", null);
            SQLiteDatabase db = helper.getWritableDatabase();
            DaoMaster daoMaster = new DaoMaster(db);
            daoSession = daoMaster.newSession();
            ((MyApplication) this.getApplicationContext()).setDaoSession(daoSession);
        }

        gameToPlayerDao = daoSession.getGameToPlayerDao();
        GameDao gameDao=daoSession.getGameDao();
        //Game game =gameDao.loadByRowId()
//        PlayerDao playerDao = daoSession.getPlayerDao();
//        GameDao gameDao=daoSession.getGameDao();
//        Game game=new Game();
//        game.setScore(80);
//        gameDao.insertOrReplace(game);
//
//        GameToPlayer gameToPlayer=new GameToPlayer();
//        gameToPlayer.setPlayer(playerDao.load((long)3));
//        gameToPlayer.setGameId(game.getId());
//        gameToPlayer.setLevel(2);
//        gameToPlayer.setStatus(1);
//
//        GameToPlayer gameToPlayer1=new GameToPlayer();
//        gameToPlayer1.setPlayer(playerDao.load((long)7));
//        gameToPlayer1.setGameId(game.getId());
//        gameToPlayer1.setLevel(2);
//        gameToPlayer1.setStatus(1);
//
//        GameToPlayer gameToPlayer2=new GameToPlayer();
//        gameToPlayer2.setPlayer(playerDao.load((long)9));
//        gameToPlayer2.setGameId(game.getId());
//        gameToPlayer2.setLevel(2);
//        gameToPlayer2.setStatus(1);
//
//        gameToPlayerDao.insertOrReplace(gameToPlayer);
//        gameToPlayerDao.insertOrReplace(gameToPlayer1);
//        gameToPlayerDao.insertOrReplace(gameToPlayer2);
        gameList = gameToPlayerDao.loadAll();


    }
}
