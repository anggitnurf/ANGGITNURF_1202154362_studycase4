package com.anggit.android.anggitnurf_1202154362_studycase4;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.InputStream;

public class CariGambar extends AppCompatActivity {
    //deklarasi variable
    private ImageView image;
    private ProgressDialog progress;
    private EditText link;
    private Button loader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cari_gambar);

        //initialize views
        image = (ImageView)findViewById(R.id.image);
        link = (EditText)findViewById(R.id.masukUrl);
        loader = (Button)findViewById(R.id.cariButton);

        loader.setOnClickListener(new View.OnClickListener() {

            //when button clicked
            @Override
            public void onClick(View v) {
                //get the url to string
                String masukUrl = link.getText().toString();
                //if edit text url empty
                if (masukUrl.isEmpty()) {
                    Toast.makeText(CariGambar.this, "Masukkan URL gambar", Toast.LENGTH_SHORT).show();
                }else {
                    //if url inputed
                    new loaderImage().execute(masukUrl);
                }
            }
        });
    }
    private class loaderImage extends AsyncTask<String, Void, Bitmap> {
        protected void onPreExecute() {
            super.onPreExecute();
            // Create progress dialog
            progress = new ProgressDialog(CariGambar.this);
            // Set progress dialog title
            progress .setTitle("Search Image");
            // Set progress dialog message
            progress .setMessage("Loading...");
            progress .setIndeterminate(false);
            // Show progress dialog
            progress .show();
        }
        @Override
        protected Bitmap doInBackground(String... url) {
            String urlImage = url[0];
            Bitmap bitmap = null;
            try {
                //download image from url
                InputStream input = new java.net.URL(urlImage).openStream();
                //decode
                bitmap = BitmapFactory.decodeStream(input);
            }catch (Exception e){
                e.printStackTrace();
            }
            return bitmap;
        }
        @Override
        protected void onPostExecute(Bitmap result) {
            // Set bitmap to imageview
            image.setImageBitmap(result);
            // close progress
            progress.dismiss();
        }
    }
}