package com.example.tugas5_akbarajatigayuhrisangaji_124190047.entity;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DataMotorDAO {
    @Insert
    long insertData(DataMotor dataMotor);

    @Query("Select * from motor_db")
    List<DataMotor> getData();

    @Update
    int updateData(DataMotor item);

    @Delete
    void deleteData(DataMotor item);

}
