package com.app.puzzlegame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.database.DatabaseActivity;

/**
 * Created by Administrator on 01-Jul-16.
 */
public class LevelActivity extends Activity {
    private Button btnLevel1, btnLevel2, btnLevel3, btExit;
    private Boolean ckLevel1 = false, ckLevel2 = false, ckLevel3 = false;
    private DatabaseActivity myDb = new DatabaseActivity(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);

        btnLevel1 = (Button) findViewById(R.id.btnLevel1);
        btnLevel2 = (Button) findViewById(R.id.btnLevel2);
        btnLevel3 = (Button) findViewById(R.id.btnLevel3);
        btExit = (Button) findViewById(R.id.btnExit);

/*        ckLevel1 = myDb.CheckFinishedLevel("1");
        ckLevel2 = myDb.CheckFinishedLevel("2");
        ckLevel3 = myDb.CheckFinishedLevel("3");

        if(ckLevel1){
            btnLevel1.setEnabled(false);
        }
        if(ckLevel2){
            btnLevel2.setEnabled(false);
        }
        if(ckLevel1){
            btnLevel3.setEnabled(false);
        }*/

        btnLevel1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), GameActivity.class);
                i.putExtra("level", "1");
                startActivity(i);
            }
        });
        btnLevel2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), GameActivity.class);
                i.putExtra("level", "2");
                startActivity(i);
            }
        });
        btnLevel3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), GameActivity.class);
                i.putExtra("level", "3");
                startActivity(i);
            }
        });
        btExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), MenuActivity.class);
                startActivity(i);
                startActivity(i);
            }
        });
    }
}
