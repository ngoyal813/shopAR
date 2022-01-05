package com.example.shopar;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.shopar.Prevalent.Prevalent;
import com.example.shopar.databinding.FragmentProductDetailsBinding;
import com.example.shopar.models.productavailablemodel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

import io.paperdb.Paper;

public class ProductDetailsFragment extends Fragment {


    private FragmentProductDetailsBinding binding;
    private String productid,productcategory,productimageurl;
    private ImageView productimage;
    private TextView productname,productprice,productdescription;
    private DatabaseReference cartref;
    private FloatingActionButton btn_viewinAR;
    private Button btn_addtocart;
    private String modelurl;
    private ElegantNumberButton btn_qty;
    private String quantity;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProductDetailsBinding.inflate(inflater,container,false);
        View root = binding.getRoot();
        Paper.init(getContext());
        productid = getArguments().getString("productId");
        productcategory = getArguments().getString("categpryname");
        productimage = binding.ivProductimagedetails;
        productname = binding.tvProductnamedetails;
        btn_addtocart= binding.btnBuynow;
        btn_qty = binding.btnQtybutton;
        productprice = binding.tvProductpricedetails;
        productdescription = binding.tvDescriptiondetails;
        btn_viewinAR = binding.vrBtn;
        cartref = FirebaseDatabase.getInstance("https://shopar-497ee-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference().child("Users").child(Paper.book().read(Prevalent.userphonekey)).child("Cart");
        getProductDetails(productid);
        btn_addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                storeorderinfo();
            }
        });
        return root;
    }

    private void storeorderinfo() {
        HashMap<String,Object> cartitems = new HashMap<>();
        cartitems.put("PCategory",productcategory);
        cartitems.put("PId",productid);
        cartitems.put("quantity",btn_qty.getNumber());
        cartitems.put("PImage",productimageurl);
        cartitems.put("PName",productname.getText().toString());
        cartitems.put("PPrice",productprice.getText().toString());
        cartitems.put("PDescription",productdescription.getText().toString());

        cartref.child(productid).updateChildren(cartitems)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getContext(), "Item Added To Cart Successfully", Toast.LENGTH_SHORT).show();
                        } else {
                            String message = task.getException().toString();
                            Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    private void getProductDetails(String productid) {
        DatabaseReference productsRef = FirebaseDatabase.getInstance("https://shopar-497ee-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference().child("Products").child(productcategory);
        productsRef.child(productid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    productavailablemodel productavailablemodel = snapshot.getValue(com.example.shopar.models.productavailablemodel.class);
                    productname.setText(productavailablemodel.getName());
                    productprice.setText(productavailablemodel.getPrice());
                    productdescription.setText(productavailablemodel.getDescription());
                    modelurl = productavailablemodel.getModel();
                    productimageurl = productavailablemodel.getImage();
                    Glide.with(getContext()).load(productavailablemodel.getImage()).into(productimage);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        btn_viewinAR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(productcategory.equals("sunglasses")){
                    Bundle bundle = new Bundle();
                    bundle.putString("modeluri",modelurl);
                    Navigation.findNavController(view).navigate(R.id.faceaugmentation,bundle);
                } else {
                Bundle bundle = new Bundle();
                bundle.putString("modeluri",modelurl);
                Navigation.findNavController(view).navigate(R.id.arFragment,bundle);
                }
            }
        });
    }
}