package com.androidexamples.activity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.androidexamples.model.UserInfo;

import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME="contactManager";
    private static final String TABLE_CONTACT="contacts";

    private static final String KEY_ID="id";
    private static final String KEY_NAME="name";
    private static final String KEY_PHONE_NO="phone_no";
    private SQLiteDatabase db;

    public DBHandler(Context context) {
        super(context, DATABASE_NAME,null, DATABASE_VERSION);
         db=this.getWritableDatabase();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE="CREATE TABLE " + TABLE_CONTACT + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_NAME + " TEXT," + KEY_PHONE_NO + " TEXT" +")";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACT);

        onCreate(db);
    }

    public void addContacts(UserInfo userInfo){
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues values=new ContentValues();
        values.put(KEY_NAME,userInfo.getName());
        values.put(KEY_PHONE_NO, userInfo.getPhone_number());
        db.insert(TABLE_CONTACT, null, values);
    }

    public List<UserInfo>getAllContacts(){
        List<UserInfo>userInfoList=new ArrayList<>();
        String selectQuery ="select * from " + TABLE_CONTACT;
        Cursor cursor=db.rawQuery(selectQuery,null);

        if(cursor.moveToFirst()){
            do{
                UserInfo userInfo=new UserInfo();
                userInfo.setId(Integer.parseInt(cursor.getString(0)));
                userInfo.setName(cursor.getString(1));
                userInfo.setPhone_number(cursor.getString(2));
                userInfoList.add(userInfo);

            }while(cursor.moveToNext());
        }

       return userInfoList;
    }

    public boolean isEmpty(){
         String isEmptyQuery="Select count(*)from " + TABLE_CONTACT;
         Cursor cursor=db.rawQuery(isEmptyQuery,null);
         cursor.moveToFirst();
         int count=cursor.getInt(0);
        if(count>0){
            return true;
        }else{
            return false;
        }
    }

}
