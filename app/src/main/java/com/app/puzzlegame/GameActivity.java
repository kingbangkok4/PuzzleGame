package com.app.puzzlegame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import com.database.DatabaseActivity;

/**
 * Created by Administrator on 3/27/2016.
 */
public class GameActivity extends Activity{
    private DatabaseActivity myDb = new DatabaseActivity(this);
    private Button iQuestion, btAnswer1, btAnswer2, btAnswer3, btExit;
    private ArrayList<HashMap<String, String>> gameList;
    private int i_random = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        iQuestion = (Button)findViewById(R.id.imgQuestion);
        btAnswer1 = (Button)findViewById(R.id.btnAnswer1);
        btAnswer2 = (Button)findViewById(R.id.btnAnswer2);
        btAnswer3 = (Button)findViewById(R.id.btnAnswer3);
        btExit = (Button)findViewById(R.id.btnExit);

        GamesAll();

        btAnswer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckAnswer("1");
            }
        });
        btAnswer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckAnswer("2");
            }
        });
        btAnswer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckAnswer("3");
            }
        });
        btExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }

    private void CheckAnswer(String answer) {
        switch (answer) {
            case "1":
                if(gameList.get(i_random).get("answer1").equals(gameList.get(i_random).get("answer"))){
                    msgShow("ถูก");
                    GamesAll();
                }else{
                    msgShow("ผิด");
                }
                break;
            case "2":
                if(gameList.get(i_random).get("answer2").equals(gameList.get(i_random).get("answer"))){
                    msgShow("ถูก");
                    GamesAll();
                }else{
                    msgShow("ผิด");
                }
                break;
            case "3":
                if(gameList.get(i_random).get("answer3").equals(gameList.get(i_random).get("answer"))){
                    msgShow("ถูก");
                    GamesAll();
                }else{
                    msgShow("ผิด");
                }
                break;
            default:
                break;
        }

    }

    private void GamesAll() {
        gameList = myDb.GetGamesAll();
        if(gameList != null){
            i_random = randInt(0, gameList.size());

            int resQuestionId = getResources().getIdentifier(gameList.get(i_random).get("question"), "drawable", getPackageName());
            iQuestion.setBackgroundResource(resQuestionId);

            int resanswer1Id = getResources().getIdentifier(gameList.get(i_random).get("answer1"), "drawable", getPackageName());
            btAnswer1.setBackgroundResource(resanswer1Id);
            int resanswer2Id = getResources().getIdentifier(gameList.get(i_random).get("answer2"), "drawable", getPackageName());
            btAnswer2.setBackgroundResource(resanswer2Id);
            int resanswer3Id = getResources().getIdentifier(gameList.get(i_random).get("answer3"), "drawable", getPackageName());
            btAnswer3.setBackgroundResource(resanswer3Id);
        }
    }

    private int randInt(int min, int max) {
        Random r = new Random();
        int i1 = r.nextInt(max - min + 1) + min;
        return i1;
    }

    private void msgShow(String strMsg){
        Toast.makeText(getApplicationContext(), strMsg, Toast.LENGTH_SHORT).show();
    }
}
