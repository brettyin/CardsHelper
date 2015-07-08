package com.brettyin.cardshelper;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.brettyin.cardshelper.model.DaoMaster;
import com.brettyin.cardshelper.model.DaoSession;
import com.brettyin.cardshelper.model.Player;
import com.brettyin.cardshelper.model.PlayerDao;

import java.util.ArrayList;
import java.util.List;

import at.markushi.ui.CircleButton;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    List<Player> leaseList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CircleButton btnNext = (CircleButton) findViewById(R.id.btnNext);
        btnNext.setOnClickListener(this);

        RecyclerView recList = (RecyclerView) findViewById(R.id.cardList);
        recList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);

        initialDB();
        ContactAdapter ca = new ContactAdapter(this,leaseList);
        recList.setAdapter(ca);


    }
    void initialDB()
    {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "cards-db", null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        DaoSession daoSession = daoMaster.newSession();

        PlayerDao playerDao = daoSession.getPlayerDao();

//        Player player=new Player();
//        player.setName("老郑");
//        playerDao.insertOrReplace(player);
//
//         player=new Player();
//        player.setName("来军");
//        playerDao.insertOrReplace(player);
//
//         player=new Player();
//        player.setName("菜菜");
//        playerDao.insertOrReplace(player);
//
//         player=new Player();
//        player.setName("阿猪");
//        playerDao.insertOrReplace(player);
        leaseList = playerDao.loadAll();


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    @Override
    public void onClick(View v) {

    }

    private List<ContactInfo> createList(int size) {

        List<ContactInfo> result = new ArrayList<ContactInfo>();
        for (int i=1; i <= size; i++) {
            ContactInfo ci = new ContactInfo();
            ci.name = ContactInfo.NAME_PREFIX + i;
            ci.surname = ContactInfo.SURNAME_PREFIX + i;
            ci.email = ContactInfo.EMAIL_PREFIX + i + "@test.com";

            result.add(ci);

        }

        return result;
    }
}
