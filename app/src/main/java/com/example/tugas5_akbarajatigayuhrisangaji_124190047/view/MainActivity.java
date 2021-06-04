package com.example.tugas5_akbarajatigayuhrisangaji_124190047.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tugas5_akbarajatigayuhrisangaji_124190047.R;
import com.example.tugas5_akbarajatigayuhrisangaji_124190047.entity.AppDatabase;
import com.example.tugas5_akbarajatigayuhrisangaji_124190047.entity.DataMotor;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainContact.view, View.OnClickListener {
    private AppDatabase appDatabase;
    private MainPresenter mainPresenter;
    private MainAdapter mainAdapter;

    private Button btnOK;
    private RecyclerView recyclerView;
    private EditText etNama, etStnk, etTipe, etWarna;

    private int id=0;
    boolean edit = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        btnOK = findViewById(R.id.submit);
        etNama = findViewById(R.id.namaLengkap);
        etStnk = findViewById(R.id.noStnk);
        etTipe = findViewById(R.id.tipeMotor);
        etWarna = findViewById(R.id.warnaMotor);
        recyclerView = findViewById(R.id.rc_main);

        appDatabase = AppDatabase.inidb(getApplicationContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mainPresenter = new MainPresenter(this);
        mainPresenter.readData(appDatabase);
        btnOK.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v==btnOK){
            if(etNama.getText().toString().equals("")||etStnk.getText().toString().equals("")
                    ||etTipe.getText().toString().equals("")||etWarna.getText().toString().equals("")){
                Toast.makeText(this,"Harap Isi Semua Data!", Toast.LENGTH_SHORT).show();
            } else{
                if(!edit){
                    mainPresenter.insertData(etNama.getText().toString(),etStnk.getText().toString(),etTipe.getText().toString(),etWarna.getText().toString(),appDatabase);

                }else{
                    mainPresenter.editData(etNama.getText().toString(),etStnk.getText().toString(),etTipe.getText().toString(),etWarna.getText().toString(),id,appDatabase);
                    edit = false;

                }
                resetForm();
            }
        }
    }

    @Override
    public void successAdd() {
        Toast.makeText(this, "Berhasil Menambah Data Motor", Toast.LENGTH_SHORT).show();
        mainPresenter.readData(appDatabase);
    }

    @Override
    public void successDelete() {
        Toast.makeText(this,"Berhasil Menghapus Data Motor",Toast.LENGTH_SHORT).show();
        mainPresenter.readData(appDatabase);
    }

    @Override
    public void resetForm() {
        etNama.setText("");
        etStnk.setText("");
        etTipe.setText("");
        etWarna.setText("");
        btnOK.setText("Submit");
    }

    @Override
    public void getData(List<DataMotor> list) {
        mainAdapter = new MainAdapter(this,list,this);
        recyclerView.setAdapter(mainAdapter);
    }

    @Override
    public void editData(DataMotor item) {
        etNama.setText(item.getNama());
        etStnk.setText(item.getStnk());
        etTipe.setText(item.getTipe());
        etWarna.setText(item.getWarna());
        id = item.getId();
        edit = true;
        btnOK.setText("EDIT DATA");
    }

    @Override
    public void deleteData(DataMotor item) {
        AlertDialog.Builder builder;
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.LOLLIPOP){
            builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
        }else{
            builder = new AlertDialog.Builder(this);
        }
        builder.setTitle("Menghapus Data").setMessage("Anda yakin ingin menghapus data ini?").setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() { //kalau diklik yes
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                resetForm();
                mainPresenter.deleteDatabase(item,appDatabase);

            }
        })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_dialer)
                .show();
    }
}