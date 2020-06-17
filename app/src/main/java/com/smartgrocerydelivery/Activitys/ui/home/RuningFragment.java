package com.smartgrocerydelivery.Activitys.ui.home;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonObject;
import com.smartgrocerydelivery.Activitys.HomeActivity;
import com.smartgrocerydelivery.Activitys.ui.ActionBottomDialogFragment;
import com.smartgrocerydelivery.Adapter.RuningAdapter;
import com.smartgrocerydelivery.Model.Itemdata.Subitemdata;
import com.smartgrocerydelivery.Model.Orderdata.Orderitemdata;
import com.smartgrocerydelivery.Model.Orderdata.Parameter;
import com.smartgrocerydelivery.Network.APIClientblog;
import com.smartgrocerydelivery.Network.ApiInterface;
import com.smartgrocerydelivery.Network.Const;
import com.smartgrocerydelivery.R;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

import static com.smartgrocerydelivery.Activitys.HomeActivity.item_quanity;
import static com.smartgrocerydelivery.Activitys.HomeActivity.subitemdata;
import static com.smartgrocerydelivery.Network.Const.Token;
import static com.smartgrocerydelivery.Network.Const.user_id;


public class RuningFragment extends Fragment implements RuningAdapter.AdapterCallback, ActionBottomDialogFragment.ItemClickListener {

    View root;
    RecyclerView runing_recycleviw;
    List<Parameter> runinglist = new ArrayList<>();
    RuningAdapter runingAdapter;
    private ApiInterface apiService;
    private CompositeDisposable disposable = new CompositeDisposable();
    private static int position_item=0;
    private static int OrderId_item=0;

    @Override
    public void onItemClick(String item) {
        changeorderstraus();
    }


    public void showBottomSheet() {
        ActionBottomDialogFragment addPhotoBottomDialogFragment = ActionBottomDialogFragment.newInstance("RuningFragment");
        addPhotoBottomDialogFragment.setCommunicator(this);
        addPhotoBottomDialogFragment.show(getActivity().getSupportFragmentManager(), ActionBottomDialogFragment.TAG);
    }


    @Override
    public void onMethodCallback(int position) {


        //

        position_item=position;
        OrderId_item=runinglist.get(position).getOrderId();
        getsubitem(runinglist.get(position).getOrderId());


    }


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_home, container, false);
        apiService = APIClientblog.getClientblog(getActivity()).create(ApiInterface.class);
        runing_recycleviw = root.findViewById(R.id.runing_recycleviw);

        setdata();

        return root;
    }

    private void setdata() {


        Const.startprogress(getContext());
        JsonObject paramObject = null;
        try {
            paramObject = new JsonObject();
            paramObject.addProperty("userId", user_id);
            // paramObject.addProperty("token","Bearer " +Token);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.d("TAG@123", paramObject.toString());


        disposable.add(
                apiService.getpickedorder("Bearer " + Token, paramObject)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<Orderitemdata>() {
                            @Override
                            public void onSuccess(Orderitemdata user) {

                                Const.finish_dialog();
                                runinglist = user.getParameters();
                                setadapterdata();

                                Log.d("TAG@123", "Succes: " + runinglist.size());
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

        runingAdapter = new RuningAdapter(runinglist, getActivity(), this);
        RecyclerView.LayoutManager mLayout = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        runing_recycleviw.setLayoutManager(mLayout);
        runing_recycleviw.setItemAnimator(new DefaultItemAnimator());
        runing_recycleviw.setAdapter(runingAdapter);

    }


    public void getsubitem(int itm) {
        Const.startprogress(getContext());
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
                            public void onSuccess(Subitemdata user_data) {
                                Const.finish_dialog();
                              if(user_data.getSuccess()){
                                  subitemdata = new ArrayList<com.smartgrocerydelivery.Model.Itemdata.Parameter>();
                                  item_quanity=0;

                                  for(int i=0;i<user_data.getParameters().size();i++){
                                      if(user_data.getParameters().get(i).getRowStatus()==1){
                                          item_quanity=item_quanity+user_data.getParameters().get(i).getQuantity();
                                          subitemdata.add(user_data.getParameters().get(i));
                                      }
                                  }


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
    public void changeorderstraus(){

        /* */





        Const.startprogress(getActivity());
        JsonObject paramObject = null;
        try {
            paramObject = new JsonObject();
            paramObject.addProperty("orderId",String.valueOf(OrderId_item));
            paramObject.addProperty("orderStatus","4");
            paramObject.addProperty("userId",String.valueOf(user_id));
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.d("TAG@123", paramObject.toString());


        disposable.add(
                apiService.updateorderstatus("Bearer "+Token,paramObject)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<JsonObject>() {
                            @Override
                            public void onSuccess(JsonObject user) {
                                Const.finish_dialog();
                                if(user.getAsJsonObject().get("success").getAsBoolean()){
                                    runinglist.remove(position_item);
                                    runingAdapter.notifyDataSetChanged();
                                    Toast.makeText(getActivity(),user.getAsJsonObject().get("message").getAsString(),Toast.LENGTH_LONG).show();
                                }
                                else {

                                    Toast.makeText(getActivity(),user.getAsJsonObject().get("message").getAsString(),Toast.LENGTH_LONG).show();

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