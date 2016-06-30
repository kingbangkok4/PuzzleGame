package com.app.puzzlegame;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.database.DatabaseActivity;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Administrator on 3/27/2016.
 */
public class ScoreActivity extends Activity{
    private TextView txtScore1, txtScore2, txtScore3;
    private Button btReset, btExit;
    private DatabaseActivity myDb = new DatabaseActivity(this);
    private ArrayList<HashMap<String, String>> scoreList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        txtScore1 = (TextView)findViewById(R.id.textViewScore1);
        txtScore2 = (TextView)findViewById(R.id.textViewScore2);
        txtScore3 = (TextView)findViewById(R.id.textViewScore3);
        btReset = (Button)findViewById(R.id.btnReset);
        btExit = (Button)findViewById(R.id.btnExit);

        /*btReset.setVisibility(View.INVISIBLE);
        if(myDb.CheckFinishedGame()){
            btReset.setVisibility(View.VISIBLE);
        }*/

        ShowScore(); //  ใช่ในการโชว์คะแนนขึ้นมา
        final AlertDialog.Builder ad = new AlertDialog.Builder(this);
        btReset.setOnClickListener(new View.OnClickListener() {  //เมธอด  ปุ่ม  reset  คะแนน
            @Override
            public void onClick(View v) {
                ad.setTitle("ยืนยันการรีเซ็ตคะแนน");
                ad.setMessage("คุณแน่ใจหรือว่าต้องการรีเซ็ตคะแนน?")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                ResetScore();
                            }
                        })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                }).show();
            }
        });
        btExit.setOnClickListener(new View.OnClickListener() {   //ปุ่มกดที่ใช่กลับไปหน้าแรก
            @Override
            public void onClick(View v) {
              /*  Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);*/
                Intent i = new Intent(ScoreActivity.this, MenuActivity.class);
                startActivity(i);
            }
        });
    }

    private void ShowScore() {
        scoreList = myDb.ShowScore();
        txtScore1.setText(scoreList.get(0).get("score_total").toString());
        txtScore2.setText(scoreList.get(1).get("score_total").toString());
        txtScore3.setText(scoreList.get(2).get("score_total").toString());
    }

    private void ResetScore() {
        myDb.ResetScore();
        //btReset.setVisibility(View.INVISIBLE);
        ShowScore();
    }
}
