package com.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;


public class DatabaseActivity extends SQLiteOpenHelper {
    private static String DB_NAME = "puzzle_game_db.sqlite";
    private static String TABLE_GAME = "tb_game";
    private static String TABLE_SCORE = "tb_score";
    private static String TABLE_ANSWER_DETAIL = "tb_answer_detail";
    private static Integer BUFFER_SIZE = 128;
    private SQLiteDatabase myDataBase;
    private final Context context;
    private DecimalFormat df = new DecimalFormat("#,###,###.##");
    String message = "";

    public DatabaseActivity(Context context) {
        super(context, DB_NAME, null, 1);
        this.context = context;
    }

    public void openDatabase() {
        File dbFolder = context.getDatabasePath(DB_NAME).getParentFile();
        if (!dbFolder.exists()) {
            dbFolder.mkdir();
        }

        File dbFile = context.getDatabasePath(DB_NAME);

        if (!dbFile.exists()) {
            try {
                copyDatabase(dbFile);
            } catch (IOException e) {
                throw new RuntimeException("Error creating source database", e);
            }
        }
    }

    private void copyDatabase(File targetDbFile) throws IOException {
        // ***********************************************************************************
        // Open your local db as the input stream
        InputStream myInput = context.getAssets().open(DB_NAME);

        // Open the empty db as the output stream
        OutputStream myOutput = new FileOutputStream(targetDbFile);

        // transfer bytes from the inputfile to the outputfile
        byte[] buffer = new byte[BUFFER_SIZE];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }

        myOutput.flush();
        myOutput.close();
        myInput.close();

        // ***********************************************************************************
    }

    @Override
    public synchronized void close() {
        if (myDataBase != null)
            myDataBase.close();
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

	public ArrayList<HashMap<String, String>> GetGamesAll(String level) {
		try {
			ArrayList<HashMap<String, String>> MyArrList = new ArrayList<HashMap<String, String>>();
			HashMap<String, String> map;
			SQLiteDatabase db;
			db = this.getReadableDatabase(); // Read Data
			String strSQL = "SELECT "+TABLE_GAME+".id, "+TABLE_GAME+".question, "+TABLE_GAME+".answer1, "+TABLE_GAME+".answer2, "+TABLE_GAME+".answer3, "+TABLE_GAME+".answer, "+TABLE_ANSWER_DETAIL+".answer_detail  FROM "
					+ TABLE_GAME + " LEFT OUTER JOIN "+TABLE_ANSWER_DETAIL+" ON "+TABLE_GAME+".id = "+TABLE_ANSWER_DETAIL+".game_id  WHERE "+TABLE_GAME+".played = 0 AND "+TABLE_GAME+".level = "+level+" ORDER BY "+TABLE_GAME+".id ";
			Cursor cursor = db.rawQuery(strSQL, null);
			if (cursor != null) {
				if (cursor.moveToFirst()) {
					do {
						map = new HashMap<String, String>();
						map.put("id", cursor.getString(0));
						map.put("question", cursor.getString(1));
						map.put("answer1", cursor.getString(2));
						map.put("answer2", cursor.getString(3));
						map.put("answer3", cursor.getString(4));
                        map.put("answer", cursor.getString(5));
                        map.put("answer_detail", cursor.getString(6));
						MyArrList.add(map);
					} while (cursor.moveToNext());
				}
			}
			cursor.close();
			db.close();
			return MyArrList;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

    public boolean UpdatePlayed(String id){
        boolean status = false;
        SQLiteDatabase db;
        db = this.getWritableDatabase(); // Write Data
        try {
            String strSQL = " UPDATE "+TABLE_GAME+" SET played = 1 WHERE id = "+id+" ";
            db.execSQL(strSQL);
            status = true;
        }catch(Exception e){
            e.printStackTrace();
        }
        db.close();

        return status;
    }

    public void UpdateScore(String level){
        SQLiteDatabase db;
        db = this.getWritableDatabase(); // Write Data
        try {
            String strSQL = " UPDATE "+TABLE_SCORE+" SET score_total = score_total + score_set WHERE level = "+level+" ";
            db.execSQL(strSQL);
        }catch(Exception e){
            e.printStackTrace();
        }
        db.close();
    }

    public ArrayList<HashMap<String, String>> ShowScore(){
        ArrayList<HashMap<String, String>> MyArrList = new ArrayList<HashMap<String, String>>();
        try {
            HashMap<String, String> map;
            SQLiteDatabase db;
            db = this.getReadableDatabase(); // Read Data
            String strSQL = "SELECT score_total FROM "+TABLE_SCORE+" ORDER BY level ";
            Cursor cursor = db.rawQuery(strSQL, null);
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {
                        map = new HashMap<String, String>();
                        map.put("score_total", cursor.getString(0));
                        MyArrList.add(map);
                    } while (cursor.moveToNext());
                }
            }
            cursor.close();
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return MyArrList;
    }

    public void ResetScore(){
        SQLiteDatabase db;
        db = this.getWritableDatabase(); // Write Data
        try {
            String strSQL = " UPDATE "+TABLE_SCORE+" SET score_total = 0 ";
            db.execSQL(strSQL);

            String strSQLGame = " UPDATE "+TABLE_GAME+" SET played = 0 ";
            db.execSQL(strSQLGame);
        }catch(Exception e){
            e.printStackTrace();
        }
        db.close();
    }

    public boolean CheckFinishedGame(){
        boolean status = false;
        try {
            String selectQuery = " SELECT * FROM "+TABLE_GAME+" WHERE played = 0 ";
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(selectQuery, null);
            cursor.moveToFirst();
            int total = cursor.getCount();
            if(total == 0){
                status = true;
            }
            cursor.close();
            db.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return  status;
    }


}
