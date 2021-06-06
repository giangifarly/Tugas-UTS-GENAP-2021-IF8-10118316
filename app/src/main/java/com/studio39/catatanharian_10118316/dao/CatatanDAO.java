package com.studio39.catatanharian_10118316.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.studio39.catatanharian_10118316.database.DataHelper;
import com.studio39.catatanharian_10118316.database.table.TableCatatan;
import com.studio39.catatanharian_10118316.model.Catatan;

import java.util.ArrayList;

public class CatatanDAO {
    //Tanggal Pengerjaan 3 Juni 2021, 10118316, Gian Gifarly, IF8
    DataHelper dataHelper;

    public CatatanDAO(Context context){
        dataHelper = new DataHelper(context);
    }

    public ArrayList<Catatan> select(String where, String[] whereArgs){
        ArrayList<Catatan> catatan = new ArrayList<>();
        SQLiteDatabase db = dataHelper.getReadableDatabase();
        try {
            Cursor c = db.query(TableCatatan.TABLE_NAME, new String[]{"*"}, where, whereArgs, null, null, TableCatatan.FIELD_TANGGAL);
            if(c.getCount() > 0 && c.moveToFirst()){
                while (!c.isAfterLast()){
                    catatan.add(new Catatan().fromCursor(c));
                    c.moveToNext();
                }
                c.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        db.close();
        return catatan;
    }

    public long insert(Catatan c){
        long id = -1;
        SQLiteDatabase db = dataHelper.getWritableDatabase();
        try {
            ContentValues values;
            id = db.insert(TableCatatan.TABLE_NAME, null, c.toValues());
        }catch (Exception e){
            e.printStackTrace();
        }
        db.close();
        return id;
    }

    public int update(Catatan c){
        int rows = -1;
        SQLiteDatabase db = dataHelper.getWritableDatabase();
        try {
            ContentValues cv = c.toValues();
            rows = db.update(TableCatatan.TABLE_NAME, cv, TableCatatan.FIELD_ID,new String[]{String.valueOf(cv.getAsInteger(TableCatatan.FIELD_ID))} );
        }catch (Exception e){
            e.printStackTrace();
        }

        db.close();
        return rows;
    }

    public int delete(int id){
        int rows = -1;
        SQLiteDatabase db = dataHelper.getWritableDatabase();
        try {
            rows = db.delete(TableCatatan.TABLE_NAME, TableCatatan.FIELD_ID, new String[]{String.valueOf(id)});
        }catch (Exception e){
            e.printStackTrace();
        }
        db.close();
        return rows;
    }
}
