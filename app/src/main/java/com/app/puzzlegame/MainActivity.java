package com.app.puzzlegame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import com.database.DatabaseActivity;


public class MainActivity extends Activity {
    private DatabaseActivity myDb = new DatabaseActivity(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb.openDatabase();

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                    Intent i = new Intent(MainActivity.this, MenuActivity.class);
                    startActivity(i);
                finish();
            }
        }, 5000);
    }
}
