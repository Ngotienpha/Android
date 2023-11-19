package com.example.mhike;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper2 extends SQLiteOpenHelper {
    public DBHelper2( Context context) {
        super(context, "Userdata2.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table UserDetails2 (name1 TEXT primary key, name2 TEXT, name3 TEXT, time TEXT, comment TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists Userdetails2");
    }

    public Boolean insertuserdata(String name1, String name2, String name3, String time, String comment){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name1", name1);
        contentValues.put("name2", name2);
        contentValues.put("name3", name3);
        contentValues.put("time", time);
        contentValues.put("comment", comment);
        long result = DB.insert("Userdetails2",null, contentValues);
        if(result==-1){
            return false;
        }else{
            return true;
        }
    }

    public Boolean updateuserdata(String name1, String name2, String name3, String time, String comment){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name2", name2);
        contentValues.put("name3", name3);
        contentValues.put("time", time);
        contentValues.put("comment", comment);
        Cursor cursor = DB.rawQuery("Select * from Userdetails2 where name1=?", new String[]{name1});
        if(cursor.getCount()>0) {
            long result = DB.update("Userdetails2", contentValues, "name1=?", new String[]{name1});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }else{
            return false;
        }
    }

    public Boolean deleteuserdata(String name1, String name2Txt, String name3Txt){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Userdetails2 where name1=?", new String[]{name1});
        if(cursor.getCount()>0) {
            long result = DB.delete("Userdetails2", "name1=?", new String[]{name1});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }else{
            return false;
        }
    }

    public Cursor getdata(){
        SQLiteDatabase DB =this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Userdetails2", null);
        return cursor;
    }
}
