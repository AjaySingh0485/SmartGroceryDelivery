package com.smartgrocerydelivery.Activitys.ui.slideshow;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.JsonObject;
import com.smartgrocerydelivery.Activitys.ui.ActionBottomDialogFragment;
import com.smartgrocerydelivery.Adapter.DelivredAdapter;

import com.smartgrocerydelivery.Model.Itemdata.Subitemdata;
import com.smartgrocerydelivery.Model.Orderdata.Orderitemdata;
import com.smartgrocerydelivery.Model.Orderdata.Parameter;
import com.smartgrocerydelivery.Network.APIClientblog;
import com.smartgrocerydelivery.Network.ApiInterface;
import com.smartgrocerydelivery.Network.Const;
import com.smartgrocerydelivery.R;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

import static com.smartgrocerydelivery.Activitys.HomeActivity.item_price;
import static com.smartgrocerydelivery.Activitys.HomeActivity.item_quanity;
import static com.smartgrocerydelivery.Activitys.HomeActivity.share_data;
import static com.smartgrocerydelivery.Activitys.HomeActivity.subitemdata;
import static com.smartgrocerydelivery.Network.Const.Token;
import static com.smartgrocerydelivery.Network.Const.user_id;

public class SlideshowFragment extends Fragment implements DelivredAdapter.AdapterCallback, ActionBottomDialogFragment.ItemClickListener {

    View root;
    RecyclerView runing_recycleviw;
    List<Parameter> deliverylist = new ArrayList<>();
    DelivredAdapter SlideshowFragment;
    private ApiInterface apiService;
    private CompositeDisposable disposable = new CompositeDisposable();
    int daysSel;
Spinner spinner_short_request;
    @Override
    public void onItemClick(String item) {
        //tvSelectedItem.setText("Selected action item is " + item);
    }


    public void showBottomSheet() {


        ActionBottomDialogFragment addPhotoBottomDialogFragment = ActionBottomDialogFragment.newInstance("SlideshowFragment");
        addPhotoBottomDialogFragment.show(getActivity().getSupportFragmentManager(), ActionBottomDialogFragment.TAG);
    }


    @Override
    public void onMethodCallback(int orderid) {
        getsubitem(orderid);
    }


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_slideshow, container, false);
        apiService = APIClientblog.getClientblog(getActivity()).create(ApiInterface.class);
        runing_recycleviw = root.findViewById(R.id.runing_recycleviw);
        spinner_short_request = root.findViewById(R.id.spinner_short_request);
        spinner_short_request.getBackground().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
        spinner_short_request.setSelection(0);



        spinner_short_request.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String t = spinner_short_request.getSelectedItem().toString();
                Log.d("TAG@1234", "value index  " + i+"----"+t);
                if (spinner_short_request.getSelectedItem().toString().equals("Today")) {
                    setdata(0,true);
                }
                else if (spinner_short_request.getSelectedItem().toString().equals("Last Week")) {
                    setdata(7,true);
                }
                else if (spinner_short_request.getSelectedItem().toString().equals("Last Month")) {
                    setdata(30,true);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



       // setdata(0,false);

        return root;
    }

    private void setdata(int i,boolean flag) {


        Const.startprogress(getActivity());
        JsonObject paramObject = null;
        try {
            paramObject = new JsonObject();
            paramObject.addProperty("userId", user_id);
            if(flag){
                paramObject.addProperty("daysSel",i);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.d("TAG@123", paramObject.toString());
        disposable.add(
                apiService.getdeliveredorder("Bearer " + Token, paramObject)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<Orderitemdata>() {
                            @Override
                            public void onSuccess(Orderitemdata user) {
                                Const.finish_dialog();
                                deliverylist = user.getParameters();
                                setadapterdata();
                                Log.d("TAG@123", "Succes: " + deliverylist.size());
                                Log.d("TAG@123", "Succes: " + user.toString());
                            }


                            @Override
                            public void onError(Throwable e) {
                                Const.finish_dialog();
                                Log.d("TAG@123", "onError: " + e.getMessage());
                                Toast.makeText(getActivity(), "Getting Some Error,Please Try Later..", Toast.LENGTH_LONG).show();

                            }
                        }));
    }

    private void setadapterdata() {

        SlideshowFragment = new DelivredAdapter(deliverylist, getActivity(), this);
        RecyclerView.LayoutManager mLayout = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        runing_recycleviw.setLayoutManager(mLayout);
        runing_recycleviw.setItemAnimator(new DefaultItemAnimator());
        runing_recycleviw.setAdapter(SlideshowFragment);

    }

    public void getsubitem(int itm) {
        Const.startprogress(getActivity());
        JsonObject paramObject = null;
        try {
            paramObject = new JsonObject();
            paramObject.addProperty("orderId", String.valueOf(itm));
            // paramObject.addProperty("token","Bearer " +Token);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.d("TAG@123", paramObject.toString());


        disposable.add(
                apiService.getorderdescription("Bearer " + Token, paramObject)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<Subitemdata>() {
                            @Override
                            public void onSuccess(Subitemdata user) {
                                Const.finish_dialog();
                                if (user.getSuccess()) {
                                    subitemdata = new ArrayList<com.smartgrocerydelivery.Model.Itemdata.Parameter>();
                                    item_quanity = 0;
                                    item_price = 0.0;
                                    share_data = "";
                                    for (int i = 0; i < user.getParameters().size(); i++) {
                                        if (user.getParameters().get(i).getRowStatus() == 1) {

                                            if (share_data.equals("")) {
                                                share_data = "Your Order Id is " + user.getParameters().get(i).getOrderId().toString() + "-" + user.getParameters().get(i).getProductName().toString();
                                            } else {
                                                share_data = share_data + ",\n" + user.getParameters().get(i).getProductName().toString();

                                            }

                                            item_quanity = item_quanity + user.getParameters().get(i).getQuantity();
                                            item_price = item_price + (user.getParameters().get(i).getSellingPrice() * user.getParameters().get(i).getQuantity());
                                            // item_price=item_price+user.getParameters().get(i).getSellingPrice();
                                            subitemdata.add(user.getParameters().get(i));
                                        }
                                    }

                                    share_data = share_data + ".\n\n\nTeam Smart Grocery";
                                    showBottomSheet();
                                }

                            }


                            @Override
                            public void onError(Throwable e) {
                                Const.finish_dialog();
                                Log.d("TAG@123", "onError: " + e.getMessage());
                                Toast.makeText(getActivity(), "Getting Some Error,Please Try Later..", Toast.LENGTH_LONG).show();

                            }
                        }));
    }


}