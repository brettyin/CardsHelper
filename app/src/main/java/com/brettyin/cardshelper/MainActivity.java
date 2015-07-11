package com.brettyin.cardshelper;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.brettyin.cardshelper.model.DaoMaster;
import com.brettyin.cardshelper.model.DaoSession;
import com.brettyin.cardshelper.model.Player;
import com.brettyin.cardshelper.model.PlayerDao;

import java.util.ArrayList;
import java.util.List;

import at.markushi.ui.CircleButton;


public class MainActivity extends Activity implements View.OnClickListener {
    List<Player> playerList;
    PlayerDao playerDao;
    RecyclerView recList;
    Drawable btnColor;

    CircleButton btnNext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnNext = (CircleButton) findViewById(R.id.btnNext);
        btnNext.setOnClickListener(this);

        btnColor=btnNext.getBackground();
        changeBtn(false);

        CircleButton btnAdd = (CircleButton) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);

        recList = (RecyclerView) findViewById(R.id.cardList);
        recList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);

        initialDB();
        ContactAdapter ca = new ContactAdapter(this, playerList);
        recList.setAdapter(ca);

        ((MyApplication) this.getApplicationContext()).setMainActivity(this);

        final GestureDetector mGestureDetector = new GestureDetector(MainActivity.this, new GestureDetector.SimpleOnGestureListener() {

            @Override public boolean onSingleTapUp(MotionEvent e) {
                return false;
            }

            @Override
            public void onLongPress(MotionEvent e) {
               final View child = recList.findChildViewUnder(e.getX(),e.getY());
                PopupMenu popupMenu = new PopupMenu(MainActivity.this, child);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener(){
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.item_edit:
                                editPlayer(playerList.get(recList.getChildPosition(child)));
                                return true;
                            case R.id.item_delete:
                                deletePlayer(playerList.get(recList.getChildPosition(child)));
                                return true;

                        }
                        return true;
                    }
                });
                popupMenu.inflate(R.menu.popup_menu);
                popupMenu.show();
                //Toast.makeText(MainActivity.this, "The  is: " + playerList.get(recList.getChildPosition(child)).getName(), Toast.LENGTH_SHORT).show();

            }
        });
        recList.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
                View child = recyclerView.findChildViewUnder(motionEvent.getX(),motionEvent.getY());



                if(child!=null && mGestureDetector.onTouchEvent(motionEvent)){
//                    Drawer.closeDrawers();
                    Toast.makeText(MainActivity.this, "The Item Clicked is: " + playerList.get(recyclerView.getChildPosition(child)).getName(), Toast.LENGTH_SHORT).show();

                    return true;

                }

                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });
    }

    private void editPlayer(Player player) {
        Intent intent =new Intent(this, PlayerActivity.class);
        intent.putExtra("id",player.getId());
        startActivity(intent);
    }

    private void deletePlayer(Player player) {
        playerDao.delete(player);
        refreshPlayerList();
    }

    void refreshPlayerList()
    {
        if (playerDao==null)
            initialDB();
        else
        {
            playerList = playerDao.loadAll();
            ContactAdapter ca = new ContactAdapter(this, playerList);
            recList.setAdapter(ca);
        }
    }
    void initialDB()
    {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "cards-db", null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        DaoSession daoSession = daoMaster.newSession();
        ((MyApplication) this.getApplicationContext()).setDaoSession(daoSession);
        playerDao = daoSession.getPlayerDao();

        playerList = playerDao.loadAll();


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
        Intent intent;
        switch ( v.getId()){
            case R.id.btnAdd:
                intent =new Intent(this, PlayerActivity.class);
                startActivity(intent);
                break;
            case R.id.btnNext:
                intent =new Intent(this, GameSettingsActivity.class);
                startActivity(intent);
                break;
        }

    }

    public void changeBtn(boolean isEnable)
    {
        btnNext.setEnabled(isEnable);
        if (isEnable)
            btnNext.setColorFilter(null);
        else
            btnNext.setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY);


    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshPlayerList();
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
