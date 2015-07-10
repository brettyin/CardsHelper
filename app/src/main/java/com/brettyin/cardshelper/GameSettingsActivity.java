package com.brettyin.cardshelper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.rey.material.widget.Spinner;
import com.rey.material.widget.RadioButton;

import at.markushi.ui.CircleButton;

public class GameSettingsActivity extends AppCompatActivity {

    RadioButton rb5P;
    RadioButton rb6P;
    RadioButton rb2D;
    RadioButton rb3D;
    Spinner spn_break;
    Spinner spn_jump;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_settings);
        initialSpinnerAndRadios();

        CircleButton btnNext=(CircleButton)findViewById(R.id.btnNext);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveSettings();
            }
        });
    }

    private void saveSettings() {
        MyApplication app= ((MyApplication) this.getApplicationContext());
        if (rb2D.isChecked())
            app.setSetting_deckNr(2);
        else
            app.setSetting_deckNr(3);

        if (rb5P.isChecked())
            app.setSetting_playerNr(5);
        else
            app.setSetting_playerNr(6);


        app.setSetting_pointBreak(Integer.parseInt(spn_break.getSelectedItem().toString()));
        app.setSetting_pointJump(Integer.parseInt(spn_jump.getSelectedItem().toString()));

        Toast.makeText(this, app.getSetting_deckNr()+" "+app.getSetting_playerNr()+" "+app.getSetting_pointBreak()+" "+app.getSetting_pointJump(), Toast.LENGTH_LONG).show();

        Intent intent =new Intent(this, GameActivity.class);
        startActivity(intent);
    }

    private void initialSpinnerAndRadios() {
        spn_break = (Spinner)findViewById(R.id.spinner_pointsShangtai);
        spn_jump = (Spinner)findViewById(R.id.spinner_pointsJump);
        String[] items = new String[4];
        items[0]="80";
        items[1]="100";
        items[2]="120";
        items[3]="140";

        String[] itemsForJump = new String[3];
        itemsForJump[0]="20";
        itemsForJump[1]="30";
        itemsForJump[2]="40";
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.row_spn, items);
        ArrayAdapter<String> adapterForJump = new ArrayAdapter<>(this, R.layout.row_spn, itemsForJump);
        adapter.setDropDownViewResource(R.layout.row_spn_dropdown);
        adapterForJump.setDropDownViewResource(R.layout.row_spn_dropdown);
        spn_break.setAdapter(adapter);
        spn_jump.setAdapter(adapterForJump);


        rb5P = (RadioButton)findViewById(R.id.radioFive);
        rb6P = (RadioButton)findViewById(R.id.radioSix);
        rb2D = (RadioButton)findViewById(R.id.radioCardsTwo);
        rb3D = (RadioButton)findViewById(R.id.radioCardsThree);

        CompoundButton.OnCheckedChangeListener listener = new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if(rb5P == buttonView || rb6P == buttonView)
                    {
                        rb5P.setChecked(rb5P == buttonView);
                        rb6P.setChecked(rb6P == buttonView);
                    }
                    if(rb2D == buttonView || rb3D == buttonView) {
                        rb2D.setChecked(rb2D == buttonView);
                        rb3D.setChecked(rb3D == buttonView);
                    }
                }

            }

        };
        rb5P.setOnCheckedChangeListener(listener);
        rb6P.setOnCheckedChangeListener(listener);
        rb2D.setOnCheckedChangeListener(listener);
        rb3D.setOnCheckedChangeListener(listener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game_settings, menu);
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
