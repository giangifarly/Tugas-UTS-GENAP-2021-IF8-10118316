package com.studio39.catatanharian_10118316.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.studio39.catatanharian_10118316.database.table.TableCatatan;

public class DataHelper extends SQLiteOpenHelper {
    //Tanggal Pengerjaan 3 Juni 2021, 10118316, Gian Gifarly, IF8

    private static final String DATABASE_NAME = "catatan_harian";
    private static final int DATABASE_VERSION = 1;
    public DataHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(TableCatatan.CREATE_STATEMENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }
}





