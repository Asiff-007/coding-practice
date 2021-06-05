package io.thoughtbox.asif.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import io.thoughtbox.asif.R;
import io.thoughtbox.asif.databinding.DataBinding;
import io.thoughtbox.asif.model.NewModel;
import io.thoughtbox.asif.view.MainActivity;

public class NewAdapter extends RecyclerView.Adapter<NewAdapter.NewViewHolder> {
    ArrayList<NewModel> list;
    Context context;
    MainActivity clickListener;


    public NewAdapter(ArrayList<NewModel> list, Context context,MainActivity clickListener) {
        this.list = list;
        this.context = context;
        this.clickListener=clickListener;

    }


    @NonNull
    @Override
    public NewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        DataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context),
                R.layout.data,
                parent,
                false);
        return new NewViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull NewViewHolder holder, int position) {
        holder.binding.setData(list.get(position));
        holder.binding.getRoot().setOnClickListener(view -> {
            clickListener.onItemClicked(list.get(position));
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class NewViewHolder extends RecyclerView.ViewHolder {
        DataBinding binding;

        public NewViewHolder(@NonNull DataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
