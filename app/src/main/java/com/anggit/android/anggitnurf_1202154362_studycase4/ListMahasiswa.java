package com.anggit.android.anggitnurf_1202154362_studycase4;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;

public class ListMahasiswa extends AppCompatActivity {
    //deklarasi variable
    private ListView ListView;
    private ProgressBar ProgressBar;
    private Button StartAsyncTask;

    //saving string list of mahasiswa name to array variable
    private String[] Mahasiswa = {
            "Jungkook", "Jimin", "Jin", "Jhope", "Suga",
            "V", "Suga", "Rapmon", "Osas", "Milea",
            "Dilan", "Rangga", "Cinta", "Yoga", "Rachel"
    };

    //adding item to listView
    private AddItemToListView mAddItemToListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_mahasiswa);

        //Initialize the views
        ProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        ListView = (ListView) findViewById(R.id.listView);
        StartAsyncTask = (Button) findViewById(R.id.mulaiAsync);

        //make progressbar gone when start
        ListView.setVisibility(View.GONE);

        //setup adapter
        ListView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, new ArrayList<String>()));

        //async start when button clicked
        StartAsyncTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //adapter process
                mAddItemToListView = new AddItemToListView();
                mAddItemToListView.execute();
            }
        });
    }

    public class AddItemToListView extends AsyncTask<Void, String, Void> {

        //Member variables
        private ArrayAdapter<String> mAdapter;
        private int counter = 1;
        ProgressDialog mProgressDialog = new ProgressDialog(ListMahasiswa.this);

        protected void onPreExecute() {
            mAdapter = (ArrayAdapter<String>) ListView.getAdapter(); //casting suggestion

            //processing AsyncTask
            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            mProgressDialog.setTitle("Loading Data");
            mProgressDialog.setProgress(0);

            //canceling the AsyncTask
            mProgressDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel Process", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    mAddItemToListView.cancel(true);
                    ProgressBar.setVisibility(View.VISIBLE);
                    dialog.dismiss();
                }
            });
            mProgressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            for (String item : Mahasiswa) {
                publishProgress(item);
                try {
                    Thread.sleep(100);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (isCancelled()) {
                    mAddItemToListView.cancel(true);
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            mAdapter.add(values[0]);

            Integer current_status = (int) ((counter / (float) Mahasiswa.length) * 100);
            ProgressBar.setProgress(current_status);

            //set progress on dialog process
            mProgressDialog.setProgress(current_status);

            //set precentage on dialog process
            mProgressDialog.setMessage(String.valueOf(current_status + "%"));
            counter++;
        }

        @Override
        protected void onPostExecute(Void Void) {
            //hide progressbar
            ProgressBar.setVisibility(View.GONE);

            mProgressDialog.dismiss();
            ListView.setVisibility(View.VISIBLE);
        }
    }
}

