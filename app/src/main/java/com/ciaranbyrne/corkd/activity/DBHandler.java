package com.ciaranbyrne.corkd.activity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
Code from http://mobilesiri.com/android-sqlite-database-tutorial-using-android-studio/
 */



public class DBHandler extends SQLiteOpenHelper {
    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "winesInfo";
    // Contacts table name
    private static final String TABLE_WINES = "wines";
    // Wines Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_TYPE = "type";
    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_WINES_TABLE = "CREATE TABLE"+ TABLE_WINES + "("
        + KEY_ID + "INTEGER PRIMARY KEY," + KEY_NAME + "TEXT,"
        + KEY_TYPE + "TEXT" + ")";
        db.execSQL(CREATE_WINES_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
// Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_WINES);
// Creating tables again
        onCreate(db);
    }

    
    //CRUD
    // Adding new wine
    public void addWine(Wine wine) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, wine.getName()); // Wine Name
        values.put(KEY_TYPE, wine.getType()); // Wine Type

// Inserting Row
        db.insert(TABLE_WINES, null, values);
        db.close(); // Closing database connection
    }
    // Getting one wine
    public Wine getWine(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_WINES, new String[]{KEY_ID,
                KEY_NAME, KEY_TYPE}, KEY_ID + "=?",
        new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Wine contact = new Wine(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), (cursor.getString(2)));
// return wine
        return contact;
    }
    // Getting All Wines
    public List<Wine> getAllWines() {
        List<Wine> wineList = new ArrayList<Wine>();
// Select All Query
        String selectQuery = "SELECT * FROM" + TABLE_WINES;


        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

// looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Wine wine = new Wine();
                wine.setId(Integer.parseInt(cursor.getString(0)));
                wine.setName(cursor.getString(1));
                wine.setType(cursor.getString(2));
// Adding wine to list
                wineList.add(wine);
            } while (cursor.moveToNext());
        }

// return contact list
        return wineList;
    }
    // Getting wines Count
    public int getWinesCount() {
        String countQuery = "SELECT * FROM" + TABLE_WINES;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

// return count
        return cursor.getCount();
    }
    // Updating a wine
    public int updateWine(Wine wine) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, wine.getName());
        values.put(KEY_TYPE, wine.getType());

// updating row
        return db.update(TABLE_WINES, values, KEY_ID + " = ?",
        new String[]{String.valueOf(wine.getId())});
    }

    // Deleting a wine
    public void deleteWine(Wine wine) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_WINES, KEY_ID + " = ?",
        new String[] { String.valueOf(wine.getId()) });
        db.close();
    }
}