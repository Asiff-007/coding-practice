package com.example.myapplication1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private Context context;
    private  List<Datum> data;

    public Adapter(Context context, List<Datum> data){
        this.context=context;
        this.data=data;

    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.item_user_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Datum user= data.get(position);
        holder.txtuser.setText(user.getEmail());
        holder.txtuser1.setText(user.getFirst_name());
        holder.txtuser2.setText(user.getLast_name());
        Glide.with(holder.imguser.getContext()).load(user.getAvatar()).into(holder.imguser);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imguser;
        TextView txtuser;
        TextView txtuser1;
        TextView txtuser2;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imguser=(ImageView) itemView.findViewById(R.id.imguser);
            txtuser=(TextView) itemView.findViewById(R.id.txtuser);
            txtuser1=(TextView) itemView.findViewById(R.id.txtuser1);
            txtuser2=(TextView) itemView.findViewById(R.id.txtuser2);
        }
    }
}
