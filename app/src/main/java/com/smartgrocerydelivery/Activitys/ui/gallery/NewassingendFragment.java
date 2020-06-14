package com.smartgrocerydelivery.Activitys.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.smartgrocerydelivery.Adapter.NewassingendAdapter;
import com.smartgrocerydelivery.Adapter.RuningAdapter;
import com.smartgrocerydelivery.R;

import java.util.ArrayList;
import java.util.List;


public class NewassingendFragment extends Fragment {

    View root;

    RecyclerView newly_recycleviw;
    List<String> newlylist = new ArrayList<>();
    NewassingendAdapter newassingendAdapter;





    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_gallery, container, false);
        newly_recycleviw = root.findViewById(R.id.newly_recycleviw);
        newassingendAdapter = new NewassingendAdapter(newlylist, getActivity());
        RecyclerView.LayoutManager mLayout = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        newly_recycleviw.setLayoutManager(mLayout);
        newly_recycleviw.setItemAnimator(new DefaultItemAnimator());
        newly_recycleviw.setAdapter(newassingendAdapter);
        setdata();
        return root;
    }



    private void setdata() {
        for (int i = 0; i <= 5; i++) {
            newlylist.add("Rachit");
        }
        newassingendAdapter.notifyDataSetChanged();

    }
}