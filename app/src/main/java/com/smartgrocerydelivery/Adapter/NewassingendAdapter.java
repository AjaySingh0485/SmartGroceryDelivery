package com.smartgrocerydelivery.Adapter;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.smartgrocerydelivery.Model.Orderdata.Parameter;
import com.smartgrocerydelivery.R;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class NewassingendAdapter extends RecyclerView.Adapter<NewassingendAdapter.MyViewHolder> {

    private List<Parameter> list;
    Context context;
    private AdapterCallback mAdapterCallback;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView img_expand_b;
        TextView order_id, date_item, sattus, vender_addres, customer_name, customer_addres;
        LinearLayout quastion_main;
        TextView call_vender,direction_vender,direction_customer,customer_call;
        public MyViewHolder(View view) {
            super(view);
            img_expand_b = view.findViewById(R.id.img_expand);
            order_id = view.findViewById(R.id.order_id);
            date_item = view.findViewById(R.id.date_item);
            sattus = view.findViewById(R.id.sattus);
            vender_addres = view.findViewById(R.id.vender_addres);
            customer_name = view.findViewById(R.id.customer_name);
            customer_addres = view.findViewById(R.id.customer_addres);
            call_vender= view.findViewById(R.id.call_vender);
            direction_vender= view.findViewById(R.id.direction_vender);
            direction_customer= view.findViewById(R.id.direction_customer);
            customer_call= view.findViewById(R.id.customer_call);

        }
    }


    public NewassingendAdapter(List<Parameter> list, Context context, AdapterCallback callback
    ) {
        this.list = list;
        this.context = context;
        this.mAdapterCallback = callback;
    }

    @Override
    public NewassingendAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.newly_item, parent, false);

        return new NewassingendAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final NewassingendAdapter.MyViewHolder holder, final int position) {


        holder.order_id.setText(list.get(position).getOrderNumber());
        holder.date_item.setText(getdata(list.get(position).getUpdatedDate()));


        if (list.get(position).getOrderStatus() == 6) {
            holder.sattus.setText("ReadyForPickup");
        } else if (list.get(position).getOrderStatus() == 7) {
            holder.sattus.setText("Dispatched");
        } else if (list.get(position).getOrderStatus() == 4) {
            holder.sattus.setText("Delivered");
        }

        holder.vender_addres.setText(list.get(position).getShopName() + "," + list.get(position).getShopNo() + "" + list.get(position).getVenderMobile());
        holder.customer_name.setText(list.get(position).getCustomerName());
        holder.customer_addres.setText("(" + list.get(position).getCustomerMobile() + ")," + list.get(position).getCustomerAddress());


        holder.img_expand_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAdapterCallback.onMethodCallback(position);


            }
        });
        holder.call_vender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{

                    try {
                        String phone = list.get(position).getVenderMobile();;
                        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
                        context.startActivity(intent);
                    } catch (Exception e) {

                    }
                }
                catch (Exception e){
                    Log.d("TAG@123",e.getMessage().toString());
                }

            }
        });
        holder.direction_vender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try{

                    Uri gmmIntentUri = Uri.parse("https://www.google.com/maps/dir/?api=1&destination="+list.get(position).getVenderLat()+","+list.get(position).getVenderLong());
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                    mapIntent.setPackage("com.google.android.apps.maps");
                    context.startActivity(mapIntent);
                }
                catch (Exception e){
                    Log.d("TAG@123",e.getMessage().toString());
                }


            }
        });
        holder.direction_customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try{



                    Uri gmmIntentUri = Uri.parse("https://www.google.com/maps/dir/?api=1&destination="+list.get(position).getCustomerLat()+","+list.get(position).getCustomerLong());
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                    mapIntent.setPackage("com.google.android.apps.maps");
                    context.startActivity(mapIntent);
                }
                catch (Exception e){
                    Log.d("TAG@123",e.getMessage().toString());
                }


            }
        });
        holder.customer_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{

                    try {
                        String phone = list.get(position).getCustomerMobile();;
                        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
                        context.startActivity(intent);
                    } catch (Exception e) {

                    }
                }
                catch (Exception e){
                    Log.d("TAG@123",e.getMessage().toString());
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public interface AdapterCallback {
        void onMethodCallback(int i);
    }


    public String getdata(String date__) {
        String output = "";
        try {
            String[] arr = date__.split("T");
            String input_tt = arr[1];
            String input = arr[0].replaceAll("-", "/") + " " + input_tt.substring(0, input_tt.length() - 4);
            Log.d("TAG@123", "date__: " + input);
            DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            DateFormat outputformat = new SimpleDateFormat("MM/dd/yyyy hh:mm aa");
            Date date = null;
            try {
                date = df.parse(input);
                output = outputformat.format(date);
                System.out.println(output);
                Log.d("TAG@123", "output: " + output);
            } catch (ParseException pe) {
                Log.d("TAG@123", "ParseException: " + pe.getMessage());
                pe.printStackTrace();
                return date__;
            }
        } catch (Exception e) {

            return date__;
        }

        return output;
    }
}












