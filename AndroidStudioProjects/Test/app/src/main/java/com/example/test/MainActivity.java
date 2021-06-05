package com.example.test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView pgmlist=findViewById(R.id.pgmlist);
        pgmlist.setLayoutManager(new LinearLayoutManager(this));
        String[] language={"java","Python","c++","java script","c","swift","go","androoid","cobol","php"};
       // int image[]={R.mipmap.ic_launcher};
        pgmlist.setAdapter(new programmingadapter(language));
    }
}