package com.brettyin.cardshelper;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.brettyin.cardshelper.model.DaoSession;
import com.brettyin.cardshelper.model.Player;
import com.brettyin.cardshelper.model.PlayerDao;
import com.rey.material.widget.RadioButton;

import java.util.ArrayList;
import java.util.List;

import at.markushi.ui.CircleButton;


public class NewGameActivity extends AppCompatActivity {

    MyApplication myApplication;
    List<Long> idList,gameDealerHelperIDList,gamePlayerIDList;
    long gameDealerID;
    RadioButton rb1D,rb1Dh,rb1Pl;
    RadioButton rb2D,rb2Dh,rb2Pl;
    RadioButton rb3D,rb3Dh,rb3Pl;
    RadioButton rb4D,rb4Dh,rb4Pl;
    RadioButton rb5D,rb5Dh,rb5Pl;
    RadioButton rb6D,rb6Dh,rb6Pl;

    EditText editTextPoints;
    CircleButton btnNewGame;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game);
        btnNewGame=(CircleButton)findViewById(R.id.btnNewGame);
        changeBtn(false);
        myApplication=(MyApplication)this.getApplicationContext();
        LinearLayout ll1=(LinearLayout)findViewById(R.id.ll1);
        LinearLayout ll2=(LinearLayout)findViewById(R.id.ll2);
        LinearLayout ll3=(LinearLayout)findViewById(R.id.ll3);
        LinearLayout ll4=(LinearLayout)findViewById(R.id.ll4);
        LinearLayout ll5=(LinearLayout)findViewById(R.id.ll5);
        LinearLayout ll6=(LinearLayout)findViewById(R.id.ll6);

        TextView p1=(TextView)findViewById(R.id.p1);
        TextView p2=(TextView)findViewById(R.id.p2);
        TextView p3=(TextView)findViewById(R.id.p3);
        TextView p4=(TextView)findViewById(R.id.p4);
        TextView p5=(TextView)findViewById(R.id.p5);
        TextView p6=(TextView)findViewById(R.id.p6);

        DaoSession myDaosession=myApplication.getDaoSession();
        PlayerDao playerDao=myDaosession.getPlayerDao();
        idList=myApplication.getPlayerIDList();

        Player player1=playerDao.load(idList.get(0));
        Player player2=playerDao.load(idList.get(1));
        Player player3=playerDao.load(idList.get(2));
        Player player4=playerDao.load(idList.get(3));
        Player player5=playerDao.load(idList.get(4));
        Player player6;
        p1.setText(player1.getName());
        p2.setText(player2.getName());
        p3.setText(player3.getName());
        p4.setText(player4.getName());
        p5.setText(player5.getName());

        if (idList.size()==5) {
            ll6.setVisibility(LinearLayout.GONE);
        }else{
            player6=playerDao.load(idList.get(5));
            p6.setText(player6.getName());
        }

        initialRadioButtons();

        editTextPoints=(EditText)findViewById(R.id.editTextPoints);
        btnNewGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strPoints=editTextPoints.getText().toString();
                int points=-1;
                try {
                    points=Integer.parseInt(strPoints);

                }catch ( NumberFormatException nfe  )
                {

                }
                int caedDeckNr=myApplication.getSetting_deckNr();
                if (points<0 || points > 100*caedDeckNr || points/5*5!=points)
                {
                    Toast.makeText(v.getContext(),"Points wrong!!!", Toast.LENGTH_LONG).show();
                }
                else
                {
                    saveNewGame(points);


                }

            }
        });
    }

    private void saveNewGame(int points) {

        Toast.makeText(this, "D:"+gameDealerID+" DH:"+gameDealerHelperIDList.toString()+" Pl:"+gamePlayerIDList.toString(),Toast.LENGTH_LONG).show();
        myApplication.setGamePoints(points);
        myApplication.setGameDealerID(gameDealerID);
        myApplication.setGameDealerHelperiDList(gameDealerHelperIDList);
        myApplication.setGamePlayerIDList(gamePlayerIDList);
        finish();
    }

    void initialRadioButtons()
    {
        rb1D=(RadioButton) findViewById(R.id.rb1D);
        rb1Dh=(RadioButton) findViewById(R.id.rb1Dh);
        rb1Pl=(RadioButton) findViewById(R.id.rb1Pl);

        rb2D=(RadioButton) findViewById(R.id.rb2D);
        rb2Dh=(RadioButton) findViewById(R.id.rb2Dh);
        rb2Pl=(RadioButton) findViewById(R.id.rb2Pl);

        rb3D=(RadioButton) findViewById(R.id.rb3D);
        rb3Dh=(RadioButton) findViewById(R.id.rb3Dh);
        rb3Pl=(RadioButton) findViewById(R.id.rb3Pl);

        rb4D=(RadioButton) findViewById(R.id.rb4D);
        rb4Dh=(RadioButton) findViewById(R.id.rb4Dh);
        rb4Pl=(RadioButton) findViewById(R.id.rb4Pl);

        rb5D=(RadioButton) findViewById(R.id.rb5D);
        rb5Dh=(RadioButton) findViewById(R.id.rb5Dh);
        rb5Pl=(RadioButton) findViewById(R.id.rb5Pl);

        rb6D=(RadioButton) findViewById(R.id.rb6D);
        rb6Dh=(RadioButton) findViewById(R.id.rb6Dh);
        rb6Pl=(RadioButton) findViewById(R.id.rb6Pl);

        CompoundButton.OnCheckedChangeListener listener = new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if(rb1D == buttonView || rb1Dh == buttonView || rb1Pl == buttonView)
                    {
                        rb1D.setChecked(rb1D == buttonView);
                        rb1Dh.setChecked(rb1Dh == buttonView);
                        rb1Pl.setChecked(rb1Pl == buttonView);
                    }
                    else if(rb2D == buttonView || rb2Dh == buttonView || rb2Pl == buttonView)
                    {
                        rb2D.setChecked(rb2D == buttonView);
                        rb2Dh.setChecked(rb2Dh == buttonView);
                        rb2Pl.setChecked(rb2Pl == buttonView);
                    }
                    else if(rb3D == buttonView || rb3Dh == buttonView || rb3Pl == buttonView)
                    {
                        rb3D.setChecked(rb3D == buttonView);
                        rb3Dh.setChecked(rb3Dh == buttonView);
                        rb3Pl.setChecked(rb3Pl == buttonView);
                    }
                    else if(rb4D == buttonView || rb4Dh == buttonView || rb4Pl == buttonView)
                    {
                        rb4D.setChecked(rb4D == buttonView);
                        rb4Dh.setChecked(rb4Dh == buttonView);
                        rb4Pl.setChecked(rb4Pl == buttonView);
                    }
                    else if(rb5D == buttonView || rb5Dh == buttonView || rb5Pl == buttonView)
                    {
                        rb5D.setChecked(rb5D == buttonView);
                        rb5Dh.setChecked(rb5Dh == buttonView);
                        rb5Pl.setChecked(rb5Pl == buttonView);
                    }
                    else if(rb6D == buttonView || rb6Dh == buttonView || rb6Pl == buttonView)
                    {
                        rb6D.setChecked(rb6D == buttonView);
                        rb6Dh.setChecked(rb6Dh == buttonView);
                        rb6Pl.setChecked(rb6Pl == buttonView);
                    }
                    int dealerCount=0;
                    int playerCount=0;


                    gameDealerID=-1;
                    gamePlayerIDList=new ArrayList<Long>();
                    gameDealerHelperIDList=new ArrayList<Long>();
                    gameDealerHelperIDList.addAll(idList);
                    if (rb1D.isChecked()) {
                        dealerCount++;
                        gameDealerID=idList.get(0);
                        gameDealerHelperIDList.remove(idList.get(0));
                    }
                    if (rb2D.isChecked()) {
                        dealerCount++;
                        gameDealerID=idList.get(1);
                        gameDealerHelperIDList.remove(idList.get(1));

                    }
                    if (rb3D.isChecked()) {
                        dealerCount++;
                        gameDealerID=idList.get(2);
                        gameDealerHelperIDList.remove(idList.get(2));

                    }
                    if (rb4D.isChecked()) {
                        dealerCount++;
                        gameDealerID=idList.get(3);
                        gameDealerHelperIDList.remove(idList.get(3));

                    }
                    if (rb5D.isChecked()) {
                        dealerCount++;
                        gameDealerID=idList.get(4);
                        gameDealerHelperIDList.remove(idList.get(4));

                    }

                    if (rb1Pl.isChecked()) {
                        playerCount++;
                        gamePlayerIDList.add(idList.get(0));
                        gameDealerHelperIDList.remove(idList.get(0));
                    }
                    if (rb2Pl.isChecked()){
                        playerCount++;
                        gamePlayerIDList.add(idList.get(1));
                        gameDealerHelperIDList.remove(idList.get(1));
                    }
                    if (rb3Pl.isChecked()) {
                        playerCount++;
                        gamePlayerIDList.add(idList.get(2));
                        gameDealerHelperIDList.remove(idList.get(2));
                    }
                    if (rb4Pl.isChecked()) {
                        playerCount++;
                        gamePlayerIDList.add(idList.get(3));
                        gameDealerHelperIDList.remove(idList.get(3));
                    }
                    if (rb5Pl.isChecked()) {
                        playerCount++;
                        gamePlayerIDList.add(idList.get(4));
                        gameDealerHelperIDList.remove(idList.get(4));
                    }

                    if(idList.size()==6) {
                        if (rb6D.isChecked()) {
                            dealerCount++;
                            gameDealerID=idList.get(5);
                            gameDealerHelperIDList.remove(idList.get(5));

                        }
                        if (rb6Pl.isChecked()) {
                            playerCount++;
                            gamePlayerIDList.add(idList.get(5));
                            gameDealerHelperIDList.remove(idList.get(5));
                        }

                    }
                    if (dealerCount!=1 || playerCount<3)
                        changeBtn(false);
                    else
                        changeBtn(true);
                }

            }

        };

        rb1D.setOnCheckedChangeListener(listener);
        rb1Dh.setOnCheckedChangeListener(listener);
        rb1Pl.setOnCheckedChangeListener(listener);

        rb2D.setOnCheckedChangeListener(listener);
        rb2Dh.setOnCheckedChangeListener(listener);
        rb2Pl.setOnCheckedChangeListener(listener);

        rb3D.setOnCheckedChangeListener(listener);
        rb3Dh.setOnCheckedChangeListener(listener);
        rb3Pl.setOnCheckedChangeListener(listener);

        rb4D.setOnCheckedChangeListener(listener);
        rb4Dh.setOnCheckedChangeListener(listener);
        rb4Pl.setOnCheckedChangeListener(listener);

        rb5D.setOnCheckedChangeListener(listener);
        rb5Dh.setOnCheckedChangeListener(listener);
        rb5Pl.setOnCheckedChangeListener(listener);

        rb6D.setOnCheckedChangeListener(listener);
        rb6Dh.setOnCheckedChangeListener(listener);
        rb6Pl.setOnCheckedChangeListener(listener);
    }

    public void changeBtn(boolean isEnable)
    {
        btnNewGame.setEnabled(isEnable);
        if (isEnable)
            btnNewGame.setColorFilter(null);
        else
            btnNewGame.setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY);


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_new_game, menu);
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
