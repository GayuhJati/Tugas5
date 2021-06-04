package com.example.tugas5_akbarajatigayuhrisangaji_124190047.view;

import android.os.AsyncTask;
import android.util.Log;

import com.example.tugas5_akbarajatigayuhrisangaji_124190047.entity.AppDatabase;
import com.example.tugas5_akbarajatigayuhrisangaji_124190047.entity.DataMotor;

import java.util.List;

public class MainPresenter implements MainContact.presenter{
    private MainContact.view view;
    public MainPresenter(MainContact.view view) {
        this.view = view;
    }

    class InsertData extends AsyncTask<Void,Void,Long> {
        private AppDatabase appDatabase;
        private DataMotor dataMotor;

        public InsertData(AppDatabase appDatabase, DataMotor dataMotor) {
            this.appDatabase = appDatabase;
            this.dataMotor = dataMotor;
        }

        @Override
        protected Long doInBackground(Void... voids) {
            return appDatabase.dao().insertData(dataMotor);

        }
        protected void onPostExecute(Long along){
            super.onPostExecute(along);
            view.successAdd();
        }
    }



    @Override
    public void insertData(String nama, String stnk, String tipe,String warna, AppDatabase database) {
        final DataMotor dataMotor = new DataMotor();
        dataMotor.setNama(nama);
        dataMotor.setStnk(stnk);
        dataMotor.setTipe(tipe);
        dataMotor.setWarna(warna);
        new InsertData(database,dataMotor).execute();
    }

    @Override
    public void readData(AppDatabase database) {
        List<DataMotor> list;
        list = database.dao().getData();
        view.getData(list);
    }

    @Override
    public void editData(String nama, String stnk, String tipe, String warna, int id, AppDatabase database) {
        final DataMotor dataMotor=new DataMotor();
        dataMotor.setNama(nama);
        dataMotor.setStnk(stnk);
        dataMotor.setTipe(tipe);
        dataMotor.setWarna(warna);
        dataMotor.setId(id);
        new InsertData(database,dataMotor).execute();
    }


    class EditData extends AsyncTask<Void, Void, Integer> {
        private AppDatabase appDatabase;
        private DataMotor dataMotor;

        public EditData(AppDatabase appDatabase, DataMotor dataMotor) {
            this.appDatabase = appDatabase;
            this.dataMotor = dataMotor;
        }

        @Override
        protected Integer doInBackground(Void... voids) {
            return appDatabase.dao().updateData(dataMotor);
        }

        protected void onPostExecute(Integer integer){
            super.onPostExecute(integer);
            Log.d("integer db","onPostExecute : "+integer);
            view.successAdd();
        }
    }



    class DeleteData extends AsyncTask<Void,Void,Long>{
        private AppDatabase appDatabase;
        private DataMotor dataMotor;

        public DeleteData(AppDatabase appDatabase, DataMotor dataMotor) {
            this.appDatabase = appDatabase;
            this.dataMotor = dataMotor;
        }

        @Override
        protected Long doInBackground(Void... voids) {
            appDatabase.dao().deleteData(dataMotor);
            return null;
        }

        protected void onPostExecute(Long along){
            super.onPostExecute(along);
            view.successDelete();
        }
    }

    @Override
    public void deleteDatabase(DataMotor dataMotor, AppDatabase database) {
        new DeleteData(database,dataMotor).execute();
    }
}
