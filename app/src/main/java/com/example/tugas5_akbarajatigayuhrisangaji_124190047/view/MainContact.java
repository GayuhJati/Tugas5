package com.example.tugas5_akbarajatigayuhrisangaji_124190047.view;

import com.example.tugas5_akbarajatigayuhrisangaji_124190047.entity.AppDatabase;
import com.example.tugas5_akbarajatigayuhrisangaji_124190047.entity.DataMotor;

import java.util.List;

public interface MainContact {
    interface view {
        void successAdd();
        void successDelete();
        void resetForm();
        void getData(List<DataMotor> list);
        void editData(DataMotor item);
        void deleteData(DataMotor item);
    }
    interface presenter{
        void insertData(String nama, String stnk, String tipe, String warna, AppDatabase database);
        void readData(AppDatabase database);
        void editData(String nama, String stnk, String tipe, String warna, int id, AppDatabase database);
        void deleteDatabase(DataMotor dataMotor, AppDatabase database);
    }
}
