package com.example.myapplication1;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.net.URL;

public class Datum {
    @SerializedName("email")
    @Expose

    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    @SerializedName("first_name")
    @Expose

    private String first_name;

    public String getFirst_name(){
        return first_name;
    }

    public void setFirst_name(){
        this.first_name=first_name;
    }



    @SerializedName("last_name")
    @Expose
    private String last_name;

    public String getLast_name(){
        return last_name;
    }

    public void setLast_name(){
        this.last_name=last_name;
    }
    @SerializedName("avatar")
    @Expose
    private URL avatar;

    public URL getAvatar(){
        return avatar;
    }
    public void setAvatar(){
        this.avatar=avatar;
    }

}

