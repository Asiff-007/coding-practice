package com.example.test;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class programmingadapter extends RecyclerView.Adapter<programmingadapter.programmingViewHolder> {
   private String[] data;
    public programmingadapter(String[] languge) {
        this.data=data;
    }

    public programmingadapter(String[] languge,int image) {
    }

    @NonNull
    @Override
    public programmingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.list_item_layouy,parent,false);
        return new programmingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull programmingViewHolder holder, int position) {
      String title=data[position];
      holder.txttitle.setText(title);

    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    public class programmingViewHolder extends RecyclerView.ViewHolder{
        ImageView imgicon;
        TextView txttitle;

        public programmingViewHolder(@NonNull View itemView) {
            super(itemView);
            imgicon=(ImageView) itemView.findViewById(R.id.imgicon);
            txttitle=(TextView) itemView.findViewById(R.id.txttitle);

        }
    }

}
