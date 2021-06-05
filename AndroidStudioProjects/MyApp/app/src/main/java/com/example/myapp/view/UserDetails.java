package com.example.myapp.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.myapp.R;
import com.example.myapp.databinding.ActivityUserDetailsBinding;
import com.example.myapp.model.NewModel;
import com.google.gson.Gson;


public class UserDetails extends AppCompatActivity {

    ActivityUserDetailsBinding binding;
    NewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this, R.layout.activity_user_details);
        binding.setLifecycleOwner(this);
        Gson gson=new Gson();
        String str1=getIntent().getStringExtra("a");
        model=gson.fromJson(str1,NewModel.class);
        binding.setData(model);


    }
}