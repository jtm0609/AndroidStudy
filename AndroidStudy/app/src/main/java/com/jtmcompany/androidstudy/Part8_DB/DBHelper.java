package com.jtmcompany.androidstudy.Part8_DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    public DBHelper(@Nullable Context context) {
        super(context, "TakminDB",null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String DB_SQL="create table TakminDB" +
                "(_id integer primary key autoincrement, " +
                "title,"+
                "content)";
        sqLiteDatabase.execSQL(DB_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int OldVersion, int NewVersion) {
            if(NewVersion==DATABASE_VERSION){
                sqLiteDatabase.execSQL("drop table TakminDB");
                onCreate(sqLiteDatabase);
            }
    }
}
