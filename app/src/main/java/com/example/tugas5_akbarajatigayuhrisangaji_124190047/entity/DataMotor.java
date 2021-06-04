package com.example.tugas5_akbarajatigayuhrisangaji_124190047.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "motor_db")
public class DataMotor {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;
    @ColumnInfo(name = "nama")
    private String nama;
    @ColumnInfo(name = "stnk")
    private String stnk;
    @ColumnInfo(name = "tipe")
    private String tipe;
    @ColumnInfo(name = "warna")
    private String warna;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }

    public String getWarna() {
        return warna;
    }

    public void setWarna(String warna) {
        this.warna = warna;
    }

    public String getStnk() {
        return stnk;
    }

    public void setStnk(String stnk) {
        this.stnk = stnk;
    }
}
