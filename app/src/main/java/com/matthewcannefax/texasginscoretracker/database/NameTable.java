package com.matthewcannefax.texasginscoretracker.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class NameTable {
    public static final String TABLE_NAME = "name_table";

    public static final String NAME_ID = "name_id";
    public static final String NAME = "name";

    public static final String[] ALL_COLUMNS = {NAME_ID, NAME};

    public static final String SQL_CREATE =
            "CREATE TABLE " + TABLE_NAME + "(" +
                    NAME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    NAME + " TEXT);";

    public static final String SQL_DELETE_TABLE =
            "DROP TABLE " + TABLE_NAME;

    public static final String SQL_COUNT_ALL_ROWS = "SELECT count(*) FROM " + TABLE_NAME;

    public static boolean isNotEmpty(Context context){
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Cursor cursor = db.rawQuery(SQL_COUNT_ALL_ROWS, null);
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        db.close();
        if(count > 0){
            return true;
        }else{
            return false;
        }
    }
}
