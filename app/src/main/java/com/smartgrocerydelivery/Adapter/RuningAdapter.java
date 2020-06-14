package com.smartgrocerydelivery.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.smartgrocerydelivery.R;

import java.util.List;

public class RuningAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<String> runinglist;
    Context context;

    public RuningAdapter(List<String> runinglist, Context context) {
        this.runinglist = runinglist;
        this.context = context;
    }
    @NonNull
   /* @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }*/
    @Override
    public RuningAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.runing_item, parent, false);

        return new RuningAdapter.MyViewHolder(itemView);
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {


        public MyViewHolder(View view) {
            super(view);

        }
    }








    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return runinglist.size();
    }
}
