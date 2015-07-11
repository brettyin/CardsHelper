package com.brettyin.cardshelper;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.brettyin.cardshelper.model.DaoMaster;
import com.brettyin.cardshelper.model.DaoSession;
import com.brettyin.cardshelper.model.Game;
import com.brettyin.cardshelper.model.GameDao;
import com.brettyin.cardshelper.model.GameToPlayer;
import com.brettyin.cardshelper.model.GameToPlayerDao;
import com.brettyin.cardshelper.model.PlayerDao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import at.markushi.ui.CircleButton;

public class GameActivity extends AppCompatActivity {
    GameToPlayerDao gameToPlayerDao;
    List<GameToPlayer> gameList;
    RecyclerView recyclerView;

    MyApplication myApplication;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        myApplication=((MyApplication) this.getApplicationContext());
        recyclerView = (RecyclerView) findViewById(R.id.gameList);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);


        CircleButton btnAddGame=(CircleButton)findViewById(R.id.btnAddGame);
        btnAddGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myApplication.setIsNewGame(true);
                Intent intent=new Intent(GameActivity.this , NewGameActivity.class);
                startActivity(intent);
            }
        });
        initialDB();
        GameAdapter ca = new GameAdapter(this, gameList);
        recyclerView.setAdapter(ca);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (myApplication.isNewGame())
        {
            updateGameTable();
        }
    }

    private void updateGameTable() {
        int points=myApplication.getGamePoints();
        int deckNr=myApplication.getSetting_deckNr();
        int pointsToBreak=myApplication.getSetting_pointBreak();
        int pointsToJump=myApplication.getSetting_pointJump();

        int gameGroup=myApplication.getCurrentGameGroup();
        int gameNr=myApplication.getCurrentGameNr();

        long dealerID=myApplication.getGameDealerID();
        List<Long> dealerHelperIDs=myApplication.getGameDealerHelperiDList();
        List<Long> playerIDs=myApplication.getGamePlayerIDList();

        boolean hasBreak=(points>=pointsToBreak);
        int jumpLevelNr=1;
        if(hasBreak)
        {
            int overPoints=points-pointsToBreak;
            jumpLevelNr=jumpLevelNr+overPoints/pointsToJump;
        }
        else
        {
            int lessPoints=pointsToBreak-points;
            jumpLevelNr=jumpLevelNr+lessPoints/pointsToJump;
            if (points==0)
            {
                jumpLevelNr=4;
            }
        }

        GameDao gameDao=myApplication.getDaoSession().getGameDao();
        Game game=new Game();
        game.setDate(new Date());
        game.setGroup(gameGroup);
        game.setNr(gameNr + 1);
        game.setScore(points);
        gameDao.insert(game);
        gameList = gameToPlayerDao.queryBuilder().where(GameToPlayerDao.Properties.GameId.eq(game.getId()-1)).list();

        List<GameToPlayer> newList=new ArrayList<GameToPlayer>();
        for (GameToPlayer gameToPlayer:gameList )
        {
            GameToPlayer newGTP =new GameToPlayer();
            newGTP.setGameId(game.getId());
            newGTP.setPlayer(gameToPlayer.getPlayer());
            if(hasBreak) {
                if(playerIDs.contains(gameToPlayer.getPlayerId()))
                {

                    newGTP.setStatus(3);
                    newGTP.setLevel(gameToPlayer.getLevel()+jumpLevelNr);
                }
                else if(dealerID==gameToPlayer.getPlayerId() )
                {
                    newGTP.setStatus(1);
                    newGTP.setLevel(gameToPlayer.getLevel());
                }else if(dealerHelperIDs.contains(gameToPlayer.getPlayerId()))
                {
                    newGTP.setStatus(2);
                    newGTP.setLevel(gameToPlayer.getLevel());
                }
            }
            else
            {
                if(playerIDs.contains(gameToPlayer.getPlayerId()))
                {

                    newGTP.setStatus(3);
                    newGTP.setLevel(gameToPlayer.getLevel());
                }
                else if(dealerID==gameToPlayer.getPlayerId() )
                {
                    newGTP.setStatus(1);
                    newGTP.setLevel(gameToPlayer.getLevel()+jumpLevelNr);
                }else if(dealerHelperIDs.contains(gameToPlayer.getPlayerId()))
                {
                    newGTP.setStatus(2);
                    newGTP.setLevel(gameToPlayer.getLevel()+jumpLevelNr);
                }
            }
            gameToPlayerDao.insert(newGTP);
            newList.add(newGTP);
        }
        GameAdapter gameAdapter = new GameAdapter(this, newList);
        recyclerView.setAdapter(gameAdapter);
        myApplication.setIsNewGame(false);
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
            myApplication.setDaoSession(daoSession);
        }

        gameToPlayerDao = daoSession.getGameToPlayerDao();
        GameDao gameDao=daoSession.getGameDao();
        List<Game> oldGames=gameDao.loadAll();
        int gameGroup=0;
        if (oldGames.size()>0)
        {
            gameGroup=oldGames.get(oldGames.size()-1).getGroup()+1;
        }
        else
        {
            gameGroup=1;
        }
        myApplication.setCurrentGameNr(1);
        myApplication.setCurrentGameGroup(gameGroup);
        Game game=new Game();
        game.setNr(1);
        game.setGroup(gameGroup);
        game.setDate(new Date());
        gameDao.insertOrReplace(game);

        PlayerDao playerDao = daoSession.getPlayerDao();
        for (long id:myApplication.getPlayerIDList())
        {
            GameToPlayer gameToPlayer=new GameToPlayer();
            gameToPlayer.setGameId(game.getId());
            gameToPlayer.setLevel(2);
            gameToPlayer.setPlayer(playerDao.load(id));
            gameToPlayer.setStatus(-1);
            gameToPlayerDao.insert(gameToPlayer);
        }


        gameList = gameToPlayerDao.queryBuilder().where(GameToPlayerDao.Properties.GameId.eq(game.getId())).list();


    }
}
