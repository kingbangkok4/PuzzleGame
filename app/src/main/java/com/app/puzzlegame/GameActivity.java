package com.app.puzzlegame;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.HashMap;

import com.database.DatabaseActivity;

/**
 * Created by Administrator on 3/27/2016.
 */
public class GameActivity extends Activity{
    private DatabaseActivity myDb = new DatabaseActivity(this);
    private Button iQuestion, btAnswer1, btAnswer2, btAnswer3;
    private ArrayList<HashMap<String, String>> gameList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        iQuestion = (Button)findViewById(R.id.imgQuestion);
        btAnswer1 = (Button)findViewById(R.id.btnAnswer1);
        btAnswer2 = (Button)findViewById(R.id.btnAnswer2);
        btAnswer3 = (Button)findViewById(R.id.btnAnswer3);

        GamesAll();

        btAnswer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btAnswer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btAnswer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void GamesAll() {
        gameList = myDb.GetGamesAll();
        if(gameList != null){

        }
    }
}
