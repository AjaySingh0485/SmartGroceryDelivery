package com.smartgrocerydelivery.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.smartgrocerydelivery.Model.Itemdata.Parameter;
import com.smartgrocerydelivery.Network.Const;
import com.smartgrocerydelivery.R;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.Myholder> {
    Context context;
    List<Parameter> list;

    public ProductAdapter(Context context, List<Parameter> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public Myholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item,parent,false);
        return new Myholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Myholder holder, int position) {

        holder.item_name.setText(String.valueOf(list.get(position).getProductName()));

        if(list.get(position).getWeight()>=1000){
            int wight=list.get(position).getWeight()/1000;
            holder.item_quanty.setText(String.valueOf(wight)+"Kg");

        }
        else {
            holder.item_quanty.setText(String.valueOf(list.get(position).getWeight())+"g");
        }

        holder.item_county.setText(String.valueOf(list.get(position).getQuantity()));
        String intRate = list.get(position).getMappingId().toString();


      try{  Glide.with(context)
                .load(Const.BASE_URL+"GroceryPackagingImage/"+intRate+".jpg")
                .into(holder.item_img);

       }
      catch (Exception e){}
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Myholder extends RecyclerView.ViewHolder  {
        TextView item_name,item_quanty,item_county;
        ImageView item_img;
        public Myholder(@NonNull View itemView) {
            super(itemView);

            item_img=itemView.findViewById(R.id.item_img);
            item_name=itemView.findViewById(R.id.item_name);
            item_quanty=itemView.findViewById(R.id.item_quanty);
            item_county=itemView.findViewById(R.id.item_count);

        }
    }
}
