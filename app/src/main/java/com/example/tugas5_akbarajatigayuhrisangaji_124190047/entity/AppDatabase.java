package com.example.tugas5_akbarajatigayuhrisangaji_124190047.entity;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {DataMotor.class},version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract DataMotorDAO dao();
    private static AppDatabase appDatabase;

    public static AppDatabase inidb(Context context){
        if (appDatabase==null)
            appDatabase= Room.databaseBuilder(context,AppDatabase.class,"dbMotor").allowMainThreadQueries().build();
        return appDatabase;

    }
}
