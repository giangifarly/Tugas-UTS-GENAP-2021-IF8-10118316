package com.studio39.catatanharian_10118316.model;

import android.content.ContentValues;
import android.database.Cursor;

import com.studio39.catatanharian_10118316.database.table.TableCatatan;

public class Catatan {
    //Tanggal Pengerjaan 4 Juni 2021, 10118316, Gian Gifarly, IF8

    private int id;
    private String judul;
    private String kategori;
    private String isi_catatan;
    private String tanggal;

    public int getId() {
        return id;
    }

    public String getJudul() {
        return judul;
    }

    public String getKategori() {
        return kategori;
    }

    public String getIsi_catatan() {
        return isi_catatan;
    }

    public String getTanggal() {
        return tanggal;
    }
    //
    public void setId(int id) {
        this.id = id;
    }

    public void setJudul(String judull) {
        this.judul = judull;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public void setIsi_catatan(String isi_catatan) {
        this.isi_catatan = isi_catatan;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public Catatan fromCursor(Cursor c){
        this.setId(c.getInt(c.getColumnIndex(TableCatatan.FIELD_ID)));
        this.setJudul(c.getString(c.getColumnIndex(TableCatatan.FIELD_JUDUL)));
        this.setIsi_catatan(c.getString(c.getColumnIndex(TableCatatan.FIELD_ISICATATAN)));
        this.setKategori(c.getString(c.getColumnIndex(TableCatatan.FIELD_KATEGORI)));
        this.setTanggal(c.getString(c.getColumnIndex(TableCatatan.FIELD_TANGGAL)));
        return this;
    }

    public ContentValues toValues(){
        ContentValues cv = new ContentValues();
        if (this.getId() > 0){
            cv.put(TableCatatan.FIELD_ID, this.getId());
        }
        cv.put(TableCatatan.FIELD_JUDUL, this.getJudul());
        cv.put(TableCatatan.FIELD_KATEGORI, this.getKategori());
        cv.put(TableCatatan.FIELD_ISICATATAN, this.getIsi_catatan());
        cv.put(TableCatatan.FIELD_TANGGAL, this.getTanggal());
        return cv;
    }

    public String toString(){
        return this.getJudul();
    }
}
