package com.app.puzzlegame;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.IBinder;
import android.sax.StartElementListener;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.database.DatabaseActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 3/27/2016.
 */
public class GameActivity extends Activity{
    private DatabaseActivity myDb = new DatabaseActivity(this);
    private Button iQuestion, btAnswer1, btAnswer2, btAnswer3, btExit;
    private ArrayList<HashMap<String, String>> gameList;
    private int i_random = 0;
    private String level = "";

    MediaPlayer mMedia;
    ToggleButton tbBGM;
    MediaPlayer mpBgm;
    Handler handler = new Handler();
    Timer timer = new Timer();
    TimerTask timetask;
    TextView timeCount;

    private final long startTime = 30000;  // 1000 = 1 second
    private final long interval = 1000;
    private final MyCountDown countdown = new MyCountDown(startTime,interval);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            level = extras.getString("level");
        }

        mpBgm = MediaPlayer.create(GameActivity.this, R.raw.bb);
        mpBgm.setLooping(true);
        mpBgm.start();
        timeCount = (TextView) findViewById(R.id.txtTime);
        tbBGM = (ToggleButton)findViewById(R.id.tbBGM);
        tbBGM.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
                if(arg1)
                    mpBgm.start();
                else
                    mpBgm.pause();
            }
        });
        if(mMedia != null){
            mMedia.release();
        }

        iQuestion = (Button)findViewById(R.id.imgQuestion);
        btAnswer1 = (Button)findViewById(R.id.btnAnswer1);
        btAnswer2 = (Button)findViewById(R.id.btnAnswer2);
        btAnswer3 = (Button)findViewById(R.id.btnAnswer3);
        btExit = (Button)findViewById(R.id.btnExit);

        GamesAll();
        countdown.start();

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
              /*  Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);*/
                Intent i = new Intent(getBaseContext(), MenuActivity.class);
                startActivity(i);
            }
        });
    }

    private void CheckAnswer(String answer) {
        switch (answer) {
            case "1":
                if(gameList.get(i_random).get("answer1").equals(gameList.get(i_random).get("answer"))){
                    msgShow("ถูก");
                    GameUpdate();
                    ShowDeatail();
                }else{
                    msgShow("ผิด");
                    //GoToScore();
                    GamesAll();
                }
                break;
            case "2":
                if(gameList.get(i_random).get("answer2").equals(gameList.get(i_random).get("answer"))){
                    msgShow("ถูก");
                    GameUpdate();
                    ShowDeatail();
                }else{
                    msgShow("ผิด");
                    //GoToScore();
                    GamesAll();
                }
                break;
            case "3":
                if(gameList.get(i_random).get("answer3").equals(gameList.get(i_random).get("answer"))){
                    msgShow("ถูก");
                    GameUpdate();
                    ShowDeatail();
                }else{
                    msgShow("ผิด");
                    //GoToScore();
                    GamesAll();
                }
                break;
            default:
                break;
        }

    }

    private void GoToScore() {
        countdown.cancel();
        //countdown.onFinish();
        Intent i = new Intent(GameActivity.this, ScoreActivity.class);
        startActivity(i);
    }

    private void GameUpdate() {
        countdown.cancel();
        //countdown.onFinish();
        boolean status = myDb.UpdatePlayed(gameList.get(i_random).get("id"));
        if(status){
            myDb.UpdateScore(level);
            //GamesAll();
        }
    }

    private void GamesAll() {
        gameList = myDb.GetGamesAll(level);
        if(gameList.size() > 0){
            i_random = randInt(0, gameList.size()-1);
            //i_random = 36;

            int resQuestionId = getResources().getIdentifier(gameList.get(i_random).get("question"), "drawable", getPackageName());
            iQuestion.setBackgroundResource(resQuestionId);

            int resanswer1Id = getResources().getIdentifier(gameList.get(i_random).get("answer1"), "drawable", getPackageName());
            btAnswer1.setBackgroundResource(resanswer1Id);
            int resanswer2Id = getResources().getIdentifier(gameList.get(i_random).get("answer2"), "drawable", getPackageName());
            btAnswer2.setBackgroundResource(resanswer2Id);
            int resanswer3Id = getResources().getIdentifier(gameList.get(i_random).get("answer3"), "drawable", getPackageName());
            btAnswer3.setBackgroundResource(resanswer3Id);
        }else {
            Intent i = new Intent(GameActivity.this, ScoreActivity.class);
            startActivity(i);
        }
    }

    private int randInt(int min, int max) {
        Random r = new Random();
        int i1 = r.nextInt(max - min + 1) + min;
        return i1;
    }

    private void msgShow(String strMsg){
        Toast.makeText(getApplicationContext(), strMsg, Toast.LENGTH_SHORT).show();
        if("ถูก".equals(strMsg)){
            stopPlaying();
            mMedia = MediaPlayer.create(GameActivity.this, R.raw.t);
            mMedia.start();
        }else {
            mMedia = MediaPlayer.create(GameActivity.this, R.raw.f);
            mMedia.start();
        }
    }

    private void ShowDeatail(){
        Intent i = new Intent(GameActivity.this, DetailActivity.class);
        i.putExtra("answer_detail", gameList.get(i_random).get("answer_detail"));
        startActivity(i);
    }

    private void stopPlaying(){
        if (mMedia != null) {
            mMedia.stop();
            mMedia.release();
            mMedia = null;
        }
    }

    public class MyCountDown extends CountDownTimer {
        public MyCountDown(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
            // TODO Auto-generated constructor stub
        }

        @Override
        public void onFinish() {
            // TODO Auto-generated method stub
            timeCount.setText("หมดเวลา !");
            Intent i = new Intent(GameActivity.this, ScoreActivity.class);
            startActivity(i);
        }

        @Override
        public void onTick(long remain) {
            // TODO Auto-generated method stub
            int timeRemain = (int) (remain) / 1000;
            timeCount.setText(" เวลาที่เหลือ : " + timeRemain);
        }
    }
        public void onResume() {
            super.onResume();
            if(tbBGM.isChecked())
                mpBgm.start();
        }

        public void onPause() {
            super.onPause();
            mpBgm.pause();
        }

        public void onDestroy() {
            super.onDestroy();
            mpBgm.stop();
            mpBgm.release();
            mpBgm = null;
        }
    }



