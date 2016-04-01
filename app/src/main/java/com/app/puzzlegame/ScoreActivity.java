package com.app.puzzlegame;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.database.DatabaseActivity;

/**
 * Created by Administrator on 3/27/2016.
 */
public class ScoreActivity extends Activity{
    private TextView txtScore;
    private Button btReset;
    private DatabaseActivity myDb = new DatabaseActivity(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        txtScore = (TextView)findViewById(R.id.textViewScore);
        btReset = (Button)findViewById(R.id.btnReset);

        btReset.setVisibility(View.INVISIBLE);
        if(myDb.CheckFinishedGame()){
            btReset.setVisibility(View.VISIBLE);
        }

        ShowScore();

        btReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ResetScore();
            }
        });
    }

    private void ShowScore() {
        txtScore.setText(myDb.ShowScore());
    }

    private void ResetScore() {
        myDb.ResetScore();
        btReset.setVisibility(View.INVISIBLE);
        ShowScore();
    }
}
