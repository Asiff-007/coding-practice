package io.thoughtbox.asif.model;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;



import io.thoughtbox.asif.R;

public class NewModel  {
    String name, email, profileImage;

    public NewModel(String name, String email, String profileImage) {
        this.name = name;
        this.email = email;
        this.profileImage = profileImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    @BindingAdapter("profileImage")
    public static void loadImage(ImageView view, String profileImage) {
        Glide.with(view.getContext())
                .load(profileImage).apply(new RequestOptions().circleCrop()).placeholder(R.drawable.ic_launcher_background)
                .into(view);
    }
}
