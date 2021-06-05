package com.example.myapp.viewModel;

import androidx.lifecycle.ViewModel;

import com.android.volley.RequestQueue;
import com.example.myapp.model.NewModel;
import com.example.myapp.repo.NewRepo;
import com.example.myapp.util.MutableEventLiveData;

import java.util.ArrayList;


public class NewViewModel extends ViewModel {

    NewRepo repo;

    public NewViewModel() {
        repo=new NewRepo();
    }

    public void getAll(RequestQueue queue) {
        repo.getAll(queue);
    }
    public MutableEventLiveData<ArrayList<NewModel>> getNewListLiveData() {
        return repo.getNewListLiveData();
    }
}
