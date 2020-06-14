package com.smartgrocerydelivery.Activitys.ui.home;

import android.content.Intent;
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
import com.smartgrocerydelivery.Activitys.LoginActivity;
import com.smartgrocerydelivery.Activitys.OtpverificationActivity;
import com.smartgrocerydelivery.Adapter.RuningAdapter;
import com.smartgrocerydelivery.Model.User;
import com.smartgrocerydelivery.Network.ApiClient;
import com.smartgrocerydelivery.Network.ApiInterface;
import com.smartgrocerydelivery.R;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

import static com.smartgrocerydelivery.Network.Const.OTP_number;
import static com.smartgrocerydelivery.Network.Const.Phone_number;
import static com.smartgrocerydelivery.Network.Const.Token;
import static com.smartgrocerydelivery.Network.Const.user_id;


public class RuningFragment extends Fragment {

    View root;
    RecyclerView runing_recycleviw;
    List<String> runinglist = new ArrayList<>();
    RuningAdapter runingAdapter;
    private ApiInterface apiService;
    private CompositeDisposable disposable = new CompositeDisposable();
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_home, container, false);
        apiService = ApiClient.getClient(getActivity()).create(ApiInterface.class);
        runing_recycleviw = root.findViewById(R.id.runing_recycleviw);

        runingAdapter = new RuningAdapter(runinglist, getActivity());
        RecyclerView.LayoutManager mLayout = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        runing_recycleviw.setLayoutManager(mLayout);
        runing_recycleviw.setItemAnimator(new DefaultItemAnimator());
        runing_recycleviw.setAdapter(runingAdapter);
        setdata();
        return root;
    }

    private void setdata() {
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
                apiService.GetAssignedOrders("Bearer " +Token,paramObject)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<JsonObject>() {
                            @Override
                            public void onSuccess(JsonObject user) {

                                Log.d("TAG@123", "Succes: " +user.toString());
                            }

                            @Override
                            public void onError(Throwable e) {
                                // pbProcessing.setVisibility(View.INVISIBLE);
                                Log.d("TAG@123", "onError: " + e.getMessage());
                                Toast.makeText(getActivity(), "Getting Some Error,Please Try Later..", Toast.LENGTH_LONG).show();

                            }
                        }));
    }
}