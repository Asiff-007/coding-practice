package com.example.myapp.repo;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.myapp.model.NewModel;
import com.example.myapp.util.MutableEventLiveData;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;



public class NewRepo {
    ArrayList<NewModel> list;
    MutableEventLiveData<ArrayList<NewModel>> newListLiveData = new MutableEventLiveData<>();


    public NewRepo() {
    }

    public void getAll(RequestQueue queue) {
        list = new ArrayList<>();
        String url = "https://5fce5cb23e19cc00167c5aa7.mockapi.io/profileDetails";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, response -> {
            try {
                for (int i = 0; i < response.length(); i++) {
                    JSONObject object = response.getJSONObject((i));
                    NewModel model = new NewModel(object.getString("name"), object.getString("email"), object.getString("image_url"));
                    list.add(model);

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            newListLiveData.postValue(list);
        }, error -> {
            error.printStackTrace();
        });
        queue.add(jsonArrayRequest);
    }

    public MutableEventLiveData<ArrayList<NewModel>> getNewListLiveData() {
        return newListLiveData;
    }
}
