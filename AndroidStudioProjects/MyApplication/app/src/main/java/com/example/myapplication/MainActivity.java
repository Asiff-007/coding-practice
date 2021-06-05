package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
String TAG="MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
   /* @Override
    protected void onRestart() {
        super.onRestart();
        setContentView(R.layout.activity_main2);


    }*/
    void handler()
    {
        Intent intent=new Intent(getApplicationContext(),MainActivity.class)
                startA
    }

}