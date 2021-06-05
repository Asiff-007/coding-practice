package io.thoughtbox.asif.viewModel;

import androidx.lifecycle.ViewModel;

import com.android.volley.RequestQueue;

import java.util.ArrayList;

import io.thoughtbox.asif.model.NewModel;
import io.thoughtbox.asif.repo.NewRepo;
import io.thoughtbox.asif.util.MutableEventLiveData;

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
