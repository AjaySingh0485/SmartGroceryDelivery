package com.smartgrocerydelivery.Activitys.ui.slideshow;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import static com.smartgrocerydelivery.Activitys.HomeActivity.item_quanity;
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


    @Override public void onItemClick(String item) {
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

        setdata();

        return root;
    }

    private void setdata(){





        Const.startprogress(getActivity());
        JsonObject paramObject = null;
        try {
            paramObject = new JsonObject();
            paramObject.addProperty("userId",user_id);
            // paramObject.addProperty("token","Bearer " +Token);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.d("TAG@123", paramObject.toString());
        disposable.add(
                apiService.getdeliveredorder("Bearer "+Token,paramObject)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<Orderitemdata>() {
                            @Override
                            public void onSuccess(Orderitemdata user) {
                                Const.finish_dialog();
                                deliverylist=user.getParameters();
                                setadapterdata();
                                Log.d("TAG@123", "Succes: " +deliverylist.size());
                                Log.d("TAG@123", "Succes: " +user.toString());
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

        SlideshowFragment = new DelivredAdapter(deliverylist, getActivity(),this);
        RecyclerView.LayoutManager mLayout = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        runing_recycleviw.setLayoutManager(mLayout);
        runing_recycleviw.setItemAnimator(new DefaultItemAnimator());
        runing_recycleviw.setAdapter(SlideshowFragment);

    }
    public void  getsubitem(int itm){
        Const.startprogress(getActivity());
        JsonObject paramObject = null;
        try {
            paramObject = new JsonObject();
            paramObject.addProperty("orderId",String.valueOf(itm));
            // paramObject.addProperty("token","Bearer " +Token);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.d("TAG@123", paramObject.toString());


        disposable.add(
                apiService.getorderdescription("Bearer "+Token,paramObject)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<Subitemdata>() {
                            @Override
                            public void onSuccess(Subitemdata user) {
                                Const.finish_dialog();
                                if(user.getSuccess()){
                                    subitemdata = new ArrayList<com.smartgrocerydelivery.Model.Itemdata.Parameter>();
                                    item_quanity=0;
                                    for(int i=0;i<user.getParameters().size();i++){
                                        if(user.getParameters().get(i).getRowStatus()==1){
                                            item_quanity=item_quanity+user.getParameters().get(i).getQuantity();
                                            subitemdata.add(user.getParameters().get(i));
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


}