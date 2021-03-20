package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class History extends AppCompatActivity {

    DataManager DataMan;

    private ListView lista;

    Button clear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        lista = (ListView) findViewById(R.id.hist_list);
        clear = (Button) findViewById(R.id.clear_hist);
        DataMan = new DataManager(this);
        clear.setOnClickListener(new View.OnClickListener(){
           public void onClick(View v){
               DataMan.ClearDb();
               finish();
               startActivity(getIntent());
           }
        });

        LoadHist();
    }

    private void LoadHist(){
        Cursor data = DataMan.getData();

        ArrayList<String> listData = new ArrayList<>();
        while(data.moveToNext()){
            listData.add(data.getString(1) + "\n" + data.getString(2));
        }

        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
        lista.setAdapter(adapter);
    }
}