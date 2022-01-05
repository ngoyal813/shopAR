package com.example.shopar;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.bumptech.glide.Glide;
import com.example.shopar.Viewholder.Productviewholder;
import com.example.shopar.databinding.FragmentProductAvailableBinding;
import com.example.shopar.models.productavailablemodel;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProductAvailableFragment extends Fragment {

    private String categoryName;
    private DatabaseReference productsref;
    private FragmentProductAvailableBinding binding;
    private RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentProductAvailableBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        categoryName = getArguments().getString("Category").toLowerCase();
        productsref = FirebaseDatabase.getInstance("https://shopar-497ee-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference().child("Products").child(categoryName);
        recyclerView = binding.rvProductavailablescreen;
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        return root;

    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseRecyclerOptions<productavailablemodel> options =
                new FirebaseRecyclerOptions.Builder<productavailablemodel>()
                .setQuery(productsref,productavailablemodel.class)
                .build();
        FirebaseRecyclerAdapter<productavailablemodel, Productviewholder> adapter =
                new FirebaseRecyclerAdapter<productavailablemodel, Productviewholder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull Productviewholder holder, int position, @NonNull productavailablemodel model) {
                        Glide.with(getContext()).load(model.getImage()).into(holder.ProductImage);
                        holder.tvProductName.setText(model.getName());
                        holder.tvProductPrice.setText(model.getPrice());

                        holder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Bundle bundle = new Bundle();
                                bundle.putString("productId",model.getPid());
                                bundle.putString("categpryname",model.getCategory());
                                Navigation.findNavController(view).navigate(R.id.productDetailsFragment,bundle);
                            }
                        });
                    }

                    @NonNull
                    @Override
                    public Productviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.productsavailableitemlayout,parent,false);
                        Productviewholder holder = new Productviewholder(view);
                        return holder;
                    }
                };
        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }

}