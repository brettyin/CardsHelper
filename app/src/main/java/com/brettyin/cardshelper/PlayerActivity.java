package com.brettyin.cardshelper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.brettyin.cardshelper.model.DaoSession;
import com.brettyin.cardshelper.model.Player;
import com.brettyin.cardshelper.model.PlayerDao;

import at.markushi.ui.CircleButton;

public class PlayerActivity extends AppCompatActivity {
    EditText ediName;
    Long playerID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        ediName=(EditText)findViewById(R.id.ediName);
        Intent intent = getIntent();
        playerID=intent.getLongExtra("id",-1);
        if (playerID>-1)
        {
            DaoSession daoSession= ((MyApplication) this.getApplicationContext()).getDaoSession();
            PlayerDao playerDao = daoSession.getPlayerDao();
            Player player=playerDao.load(playerID);
            ediName.setText(player.getName());
        }

        CircleButton btnOK=(CircleButton)findViewById(R.id.btnNext);
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addPlayer();
            }
        });
    }

    private void addPlayer()
    {
        ediName=(EditText)findViewById(R.id.ediName);
        if (ediName.getText().length()>0)
        {
            DaoSession daoSession= ((MyApplication) this.getApplicationContext()).getDaoSession();
            PlayerDao playerDao = daoSession.getPlayerDao();
            Player newPlayer;
            if (playerID>-1)
            {
                newPlayer=playerDao.load(playerID);
            }
            else
            {
                newPlayer=new Player();
            }

            newPlayer.setName(ediName.getText().toString());
            playerDao.insertOrReplace(newPlayer);
            finish();

        }
        else
        {
            Toast.makeText(getApplicationContext(),"Dude, please enter something", Toast.LENGTH_LONG).show();
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_player, menu);
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
}
