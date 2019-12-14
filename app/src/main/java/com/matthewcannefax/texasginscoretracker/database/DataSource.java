package com.matthewcannefax.texasginscoretracker.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.matthewcannefax.texasginscoretracker.model.Player;

public class DataSource {
    private Context mContext;
    private SQLiteDatabase mDatabase;
    SQLiteOpenHelper mDbHelper;

    public DataSource(Context context){
        mContext = context;
        mDbHelper = new DBHelper(mContext);
        mDatabase = mDbHelper.getWritableDatabase();
    }

    public void open(){
        mDatabase = mDbHelper.getWritableDatabase();
    }

    public void close(){
        mDbHelper.close();
    }

    private ContentValues toValues(String name){
        ContentValues values = new ContentValues(1);
        values.put(NameTable.NAME, name);
        return values;
    }

    public void addNameToDB(String name){
        if(!mDatabase.isOpen()){
            open();
        }
        ContentValues values = toValues(name);
        mDatabase.insert(NameTable.TABLE_NAME, null, values);

        close();
    }

    public String getSpecificName(CharSequence charSequence){
        if(!mDatabase.isOpen()){
            open();
        }

        String name = "";
        String partialName = charSequence.toString();
        partialName = partialName + "%";
        String[] nameArray = {partialName};

//        Cursor cursor = mDatabase.query(
//                NameTable.TABLE_NAME,
//                NameTable.ALL_COLUMNS,
//                NameTable.NAME + "=?",
//                nameArray,
//                null,
//                null,
//                null);

        Cursor cursor = mDatabase.rawQuery("SELECT " + NameTable.NAME + " FROM " + NameTable.TABLE_NAME + " WHERE " + NameTable.NAME + " LIKE ?" , nameArray);

        if(cursor != null && cursor.getCount() != 0){
            cursor.moveToFirst();
            name = cursor.getString(cursor.getColumnIndex(NameTable.NAME));
        }

        close();

        return name;
    }
}
