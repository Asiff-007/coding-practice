package io.thoughtbox.asif.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import io.thoughtbox.asif.R;
import io.thoughtbox.asif.databinding.ActivityUserDetailsBinding;
import io.thoughtbox.asif.model.NewModel;

public class UserDetails extends AppCompatActivity {

    ActivityUserDetailsBinding binding;
    NewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_user_details);
        binding.setLifecycleOwner(this);
        /*model=new NewModel("aaaa","bbb","");
        binding.setData(model);



        Glide.with(this)
                .load(model.getProfileImage()).apply(new RequestOptions().circleCrop()).placeholder(R.drawable.ic_launcher_background)
                .into(binding.imageView2);*/
    }
}