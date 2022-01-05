package com.example.shopar.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.shopar.R;
import com.example.shopar.models.categoryModel;

import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<ArrayList<categoryModel>> categoryList;
    ArrayList<categoryModel> usecategorylist;

    public HomeViewModel() {
        categoryList = new MutableLiveData<>();
        init();
    }
    public MutableLiveData<ArrayList<categoryModel>> getCategoryList(){
        return categoryList;
    }

    private void init() {
        populateList();
        categoryList.setValue(usecategorylist);
    }

    private void populateList() {
        usecategorylist = new ArrayList<>();
        usecategorylist.add(new categoryModel(R.drawable.chair,"Furniture"));
        usecategorylist.add(new categoryModel(R.drawable.footwearicon,"Shoes"));
        usecategorylist.add(new categoryModel(R.drawable.toysicon,"Toys"));
        usecategorylist.add(new categoryModel(R.drawable.sunglassesicon,"Sunglasses"));
    }

}