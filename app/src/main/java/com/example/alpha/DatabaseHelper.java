package com.example.alpha;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String TAG = "DBHelper";
    private static final String DATA_BASE = "Recyclini.db";

    //individue table
    public static final String INDIVIDUE_TABLE = "individue";
    public static final String INDIVIDUE_ID_NUM ="idnum";
    public static final String INDIVIDUE_USER_NAME="username";
    public static final String INDIVIDUE_PHONE="phone";
    public static final String INDIVIDUE_EMAIL="email";
    public static final String INDIVIDUE_PASS_WORD="password";
    public static final String CREATE_INDIVIDUE="CREATE TABLE "+INDIVIDUE_TABLE+" ( "+INDIVIDUE_ID_NUM+" INTEGER PRIMARY KEY AUTOINCREMENT,"
            +INDIVIDUE_USER_NAME+" TEXT NOT NULL , "+INDIVIDUE_PHONE+" INTEGER , "+INDIVIDUE_EMAIL+" TEXT UNIQUE , "+INDIVIDUE_PASS_WORD+" TEXT NOT NULL );";


    //entreprise table
    public static final String ENTREPRISE_TABLE = "entreprise";
    public static final String ENTREPRISE_ID_NUM ="idnum";
    public static final String ENTREPRISE_USER_NAME="username";
    public static final String ENTREPRISE_PHONE="phone";
    public static final String ENTREPRISE_NUMRC="numrc";
    public static final String ENTREPRISE_NRC="numrc";
    public static final String ENTREPRISE_EMAIL="email";
    public static final String ENTREPRISE_PASS_WORD="password";
    public static final String CREATE_ENTREPRISE="CREATE TABLE "+ENTREPRISE_TABLE+" ( "+ENTREPRISE_ID_NUM+" INTEGER PRIMARY KEY AUTOINCREMENT,"
            +ENTREPRISE_USER_NAME+" TEXT NOT NULL , "+ENTREPRISE_PHONE+" INTEGER , "+ENTREPRISE_NUMRC+" INTEGER NOT NULL , "+ENTREPRISE_NRC+" INTEGER NOT NULL , "+ENTREPRISE_EMAIL+" TEXT UNIQUE , "+ENTREPRISE_PASS_WORD+" TEXT NOT NULL );";


    //annonce table
    public static final String ANNONCE_TABLE="annonce";
    public static final String ANNONCE_ID_NUM="idnum";
    public static final String ANNONCE_TITLE="title";
    public static final String ANNONCE_TYPE="type";
    public static final String ANNONCE_QTE="qte";
    public static final String ANNONCE_PRICE="price";
    public static final String ANNONCE_DESC="description";
    public static final String ANNONCE_USER="user";
    public static final String ANNONCE_ID_USER="iduser";
    public static final String CREATE_ANNONCE="CREATE TABLE "+ANNONCE_TABLE+" ( "+ANNONCE_ID_NUM+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +ANNONCE_TITLE+" TEXT NOT NULL ,"+ANNONCE_TYPE+" TEXT NOT NULL , "+ANNONCE_QTE+" TEXT NOT NULL , "+ANNONCE_PRICE+" REAL NOT NULL , "
            +ANNONCE_DESC+" TEXT , "+ANNONCE_USER+" TEXT NOT NULL , "+ANNONCE_ID_USER+" INTEGER NOT NULL , CONSTRAINT annonce_cons CHECK ( "+ANNONCE_ID_USER+" IN ('individue','entreprise')) );";

    public DatabaseHelper(@Nullable Context context) {

        super(context,DATA_BASE, null,1);
        //SQLiteDatabase db=this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_INDIVIDUE);
        db.execSQL(CREATE_ENTREPRISE);
        db.execSQL(CREATE_ANNONCE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(TAG,
                "Upgrading the database from version " + oldVersion + " to "+ newVersion);
        db.execSQL("DROP TABLE IF EXISTS "+INDIVIDUE_TABLE);
        db.execSQL("DROP TABLE IF EXISTS "+ENTREPRISE_TABLE );
        db.execSQL("DROP TABLE IF EXISTS "+ANNONCE_TABLE );
        onCreate(db);

    }


}
