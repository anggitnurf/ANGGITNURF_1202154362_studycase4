package com.anggit.android.anggitnurf_1202154362_studycase4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MenuPilihan extends AppCompatActivity {
    //deklarasi variable
    private Button list, cari;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        //Initialize the views
        list = (Button) findViewById(R.id.list);
        cari = (Button) findViewById(R.id.cari);
    }

    public void list(View view) {
        //make intent for list
        Intent list = new Intent(MenuPilihan.this, ListMahasiswa.class);
        //make toast when intent start
        Toast.makeText(MenuPilihan.this, "Pilihan List Mahasiswa", Toast.LENGTH_SHORT).show();
        //start the intent
        startActivity(list);
    }

    public void cari(View view) {
        //make intent for cari
        Intent cari = new Intent(MenuPilihan.this, CariGambar.class);
        //make toast when intent start
        Toast.makeText(MenuPilihan.this, "Pilihan Mencari Gambar", Toast.LENGTH_SHORT).show();
        //start the intent
        startActivity(cari);
    }
}
