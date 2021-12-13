package com.example.shopar;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shopar.adapter.productavailableadapter;
import com.example.shopar.models.productavailablemodel;

import java.util.List;

public class productsAvailableFragment extends Fragment {

    private RecyclerView rvProductAvailable;
    private List<productavailablemodel> productavailablemodelList;
    private com.example.shopar.adapter.productavailableadapter productavailableadapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_products_available, container, false);
        rvProductAvailable = view.findViewById(R.id.rv_products_available);

        return view;
    }
}