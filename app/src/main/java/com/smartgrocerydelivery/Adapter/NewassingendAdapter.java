package com.smartgrocerydelivery.Adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.smartgrocerydelivery.R;
import java.util.ArrayList;
import java.util.List;


public class NewassingendAdapter extends RecyclerView.Adapter<NewassingendAdapter.MyViewHolder> {
    List<String> newlylist;
    Context context;

    public NewassingendAdapter(List<String> newlylist) {
        this.newlylist = newlylist;
    }
    @NonNull
    @Override

    public NewassingendAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.newly_item, parent, false);
        return new NewassingendAdapter.MyViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(@NonNull final NewassingendAdapter.MyViewHolder holder, int position) {
        holder.img_expand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callProductListApi(holder,newlylist);
            }
        });

    }
public void callProductListApi(MyViewHolder holder, List<String> newlylist){
        holder.recyclerViewProduct.setVisibility(View.VISIBLE);
        List<String> stringList=new ArrayList<>();
        stringList.add("AJAY");
        stringList.add("RACHIT");
    LinearLayoutManager linearLayoutManager=new LinearLayoutManager(holder.recyclerViewProduct.getContext(),LinearLayoutManager.VERTICAL,false);
    linearLayoutManager.setInitialPrefetchItemCount(stringList.size());
    ProductAdapter productAdapter=new ProductAdapter(context,stringList);
    holder.recyclerViewProduct.setLayoutManager(linearLayoutManager);
    holder.recyclerViewProduct.setAdapter(productAdapter);
    holder.recyclerViewProduct.setRecycledViewPool(new RecyclerView.RecycledViewPool());
}
    @Override
    public int getItemCount() {
        return newlylist.size();
    }

    public NewassingendAdapter(List<String> runinglist, Context context) {
        this.newlylist = runinglist;
        this.context = context;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        RecyclerView recyclerViewProduct;
        ImageView img_expand;
        public MyViewHolder(View view) {
            super(view);
            recyclerViewProduct = view.findViewById(R.id.recycleview_product);
            recyclerViewProduct.setVisibility(View.GONE);
            img_expand=view.findViewById(R.id.img_expand);

        }
    }
}
