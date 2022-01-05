package com.example.shopar;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.TestLooperManager;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.shopar.Prevalent.Prevalent;
import com.example.shopar.databinding.FragmentOrderBinding;
import com.example.shopar.models.CartModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import io.paperdb.Paper;

public class OrderFragment extends Fragment {

    private FragmentOrderBinding binding;
    private TextView tv_details;
    private Button btn_confirmorder;
    private String productname,productprice,productid,productcategory,productimage,productquantity;
    private DatabaseReference userorderref;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentOrderBinding.inflate(inflater,container,false);
        View root = binding.getRoot();
        Paper.init(getContext());
        tv_details = binding.tvPleasenetershipmentdetails;
        userorderref = FirebaseDatabase.getInstance().getReference().child("Users").child(Paper.book().read(Prevalent.userphonekey));
        String amount = getArguments().getString("overallamount");
        tv_details.setText("Cart Total Amount : \t\t\tRs. "+amount+"\n\n\nPlease Enter Shipment Details To Confirm Order");
//        btn_confirmorder.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                fetchcartinfo();
//            }
//        });
        return root;
    }
//
//    private void fetchcartinfo() {
//        userorderref.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                CartModel cartModel = snapshot.child("Cart").getValue(CartModel.class);
//                productname = snapshot.child("Cart").child()
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        })
//    }
}