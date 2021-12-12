package com.example.shopar;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shopar.adapter.home_recyclerview_adapter;
import com.example.shopar.models.categoryModel;

import java.util.ArrayList;
import java.util.List;


public class home_Fragment extends Fragment {

    private RecyclerView home_recycler_view;
    private home_recyclerview_adapter rv_Adapter;
    private List<categoryModel> home_categories;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_, container, false);
        home_categories = new ArrayList<categoryModel>();
        home_categories.add(new categoryModel(R.drawable.chair,"Furniture"));
        home_categories.add(new categoryModel(R.drawable.chair,"clothes"));
        home_categories.add(new categoryModel(R.drawable.chair,"shoes"));
        home_categories.add(new categoryModel(R.drawable.chair,"Watches"));
        home_categories.add(new categoryModel(R.drawable.chair,"Furniture"));
        home_categories.add(new categoryModel(R.drawable.chair,"Furniture"));
        home_categories.add(new categoryModel(R.drawable.chair,"Furniture"));
        rv_Adapter = new home_recyclerview_adapter(home_categories,getContext());
        home_recycler_view = view.findViewById(R.id.rv_home_categories);
        home_recycler_view.setAdapter(rv_Adapter);
        home_recycler_view.setLayoutManager(new GridLayoutManager(this.getContext(),2));
        return view;
    }
}