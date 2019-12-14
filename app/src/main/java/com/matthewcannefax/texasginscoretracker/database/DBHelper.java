package com.matthewcannefax.texasginscoretracker.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DB_FILE_NAME = "names.db";
    public static final int DB_VERSION = 1;

    public DBHelper(@Nullable Context context){
        super(context, DB_FILE_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(NameTable.SQL_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(NameTable.SQL_DELETE_TABLE);

        db.execSQL(NameTable.SQL_CREATE);
    }
}
