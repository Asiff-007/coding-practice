package com.example.myapp.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.myapp.R;
import com.example.myapp.adapter.NewAdapter;
import com.example.myapp.adapter.NewClickListener;
import com.example.myapp.databinding.ActivityMainBinding;
import com.example.myapp.model.NewModel;
import com.example.myapp.viewModel.NewViewModel;
import com.google.gson.Gson;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements NewClickListener {
    RecyclerView recyclerView;
    NewAdapter adapter;
    ActivityMainBinding binding;
    NewViewModel viewModel;
    ArrayList<NewModel> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setLifecycleOwner(this);
        binding.setClickers(this);
        viewModel = new ViewModelProvider(this).get(NewViewModel.class);
        RequestQueue queue = Volley.newRequestQueue(this);
        viewModel.getAll(queue);
        recyclerView = binding.rv;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);


        LiveData<ArrayList<NewModel>> newData = viewModel.getNewListLiveData();
        newData.observe(this, newModels -> {
            adapter = new NewAdapter(newModels, this, this);
            recyclerView.setAdapter(adapter);
        });

    }

    @Override
    public void onItemClicked(NewModel newModel) {

        Intent intent = new Intent(MainActivity.this, UserDetails.class);
        Gson gson=new Gson();
        String str1=gson.toJson(newModel);
        intent.putExtra("a",str1);
        startActivity(intent);

    }
}