package com.example.kudian.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class RecordSQLiteOpenHelper extends SQLiteOpenHelper {
    private final static String DB_NAME = "MyRecords.db";
    private final static int DB_VERSION = 1;
    public RecordSQLiteOpenHelper(Context context){
        super(context,DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //建立records表格
        String sql = "CREATE TABLE IF NOT EXISTS records (_id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT)";
        String sqlStr = null;
        db.execSQL(sqlStr);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
