package io.thoughtbox.asif.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.List;

import io.thoughtbox.asif.R;
import io.thoughtbox.asif.adapter.NewAdapter;
import io.thoughtbox.asif.adapter.NewClickListener;
import io.thoughtbox.asif.databinding.ActivityMainBinding;
import io.thoughtbox.asif.model.NewModel;
import io.thoughtbox.asif.viewModel.NewViewModel;

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
        startActivity(intent);

    }
}