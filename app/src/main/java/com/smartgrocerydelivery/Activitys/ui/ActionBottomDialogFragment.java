package com.smartgrocerydelivery.Activitys.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.smartgrocerydelivery.Adapter.ProductAdapter;
import com.smartgrocerydelivery.R;

import java.util.ArrayList;
import java.util.List;

import static com.smartgrocerydelivery.Activitys.HomeActivity.item_price;
import static com.smartgrocerydelivery.Activitys.HomeActivity.item_quanity;
import static com.smartgrocerydelivery.Activitys.HomeActivity.share_data;
import static com.smartgrocerydelivery.Activitys.HomeActivity.subitemdata;

public class ActionBottomDialogFragment extends BottomSheetDialogFragment
        implements View.OnClickListener {
    public static final String TAG = "ActionBottomDialog";
    private static String type;
    private ItemClickListener mListener;
    RecyclerView recyclerViewProduct;
    View root;
    TextView total_quanity,price_selling;
    ProductAdapter productAdapter;
LinearLayout order_stsus;
    Spinner spinner;
    ImageView share_icon;
    Button ok_button;

    public static ActionBottomDialogFragment newInstance(String data) {
        type=new String("");
        type=data;

        ActionBottomDialogFragment otherFragment = new ActionBottomDialogFragment();

        return  otherFragment;
    }








    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.bottom_sheet, container, false);
        recyclerViewProduct = root.findViewById(R.id.recycleview_product);
        spinner = root.findViewById(R.id.spinner_issue);
        ok_button = root.findViewById(R.id.ok_button);
        order_stsus= root.findViewById(R.id.order_stsus);
        total_quanity= root.findViewById(R.id.total_quanity);
        share_icon= root.findViewById(R.id.share_icon);






        price_selling=root.findViewById(R.id.price_selling);


        price_selling.setText("₹"+" "+String.format("%.02f", item_price));


        total_quanity.setText(""+item_quanity);



        share_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try{
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, share_data);
                sendIntent.setType("text/plain");
                Intent shareIntent = Intent.createChooser(sendIntent, null);
                startActivity(shareIntent);
                }catch (Exception e){

                }


            }
        });
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        productAdapter = new ProductAdapter(getActivity(), subitemdata);
        recyclerViewProduct.setLayoutManager(mLayoutManager);
        recyclerViewProduct.setAdapter(productAdapter);
        recyclerViewProduct.setRecycledViewPool(new RecyclerView.RecycledViewPool());
        ok_button.setOnClickListener(this);
        List<String> stringList = null;
        stringList = new ArrayList<>();

        if(type.equals("RuningFragment")){
            stringList.add("Delivered");
        }
        else if(type.equals("NewassingendFragment")){
            stringList.add("Dispatched");
        }
        else if(type.equals("SlideshowFragment")){
            order_stsus.setVisibility(View.GONE);

        }
       // stringList.add("Select Country");

        ArrayAdapter aa = new ArrayAdapter(getActivity(), R.layout.spinnr, stringList);
        aa.setDropDownViewResource(R.layout.spinnr_item);

        spinner.setAdapter(aa);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    public void setCommunicator(ItemClickListener communicator) {
        mListener =  communicator;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);


    }
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }





    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.ok_button:
                mListener.onItemClick(spinner.getSelectedItem().toString());
                dismiss();
                break;


        }
    }

    public interface ItemClickListener {
        void onItemClick(String item);
    }
}