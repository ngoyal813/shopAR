package com.example.shopar;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.shopar.Prevalent.Prevalent;
import com.example.shopar.Viewholder.CartViewHolder;
import com.example.shopar.databinding.FragmentCartBinding;
import com.example.shopar.models.CartModel;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import io.paperdb.Paper;


public class CartFragment extends Fragment {

    private RecyclerView recyclerView;
    private FragmentCartBinding binding;
    private DatabaseReference cartref;
    private TextView tv_cartisempty;
    private Integer overallamount = 0;
    private Integer productamount;
    private Button btn_proceedtocheckout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding =FragmentCartBinding.inflate(inflater,container,false);
        View view = binding.getRoot();
        Paper.init(getContext());
        cartref = FirebaseDatabase.getInstance("https://shopar-497ee-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference().child("Users").child(Paper.book().read(Prevalent.userphonekey)).child("Cart");
        recyclerView = binding.rvCartproucts;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        tv_cartisempty = binding.tvCartisempty;
        btn_proceedtocheckout = binding.btnProceedtocheckout;
        btn_proceedtocheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("overallamount",overallamount.toString());
                Navigation.findNavController(view).navigate(R.id.orderFragment,bundle);
            }
        });
        cartref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(!snapshot.exists()){
                    tv_cartisempty.setVisibility(View.VISIBLE);
                }else {
                    tv_cartisempty.setVisibility(View.GONE);
                    btn_proceedtocheckout.setVisibility(View.VISIBLE);
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseRecyclerOptions<CartModel> options =
                new FirebaseRecyclerOptions.Builder<CartModel>()
                .setQuery(cartref,CartModel.class)
                .build();
        FirebaseRecyclerAdapter<CartModel, CartViewHolder> adapter =
                new FirebaseRecyclerAdapter<CartModel, CartViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull CartViewHolder holder, int position, @NonNull CartModel model) {
                        Glide.with(getContext()).load(model.getPImage()).into(holder.ProductImage);
                        holder.tvProductName.setText(model.getPName());
                        holder.tvProductPrice.setText(model.getPPrice());
                        holder.quantity.setText("Quantity : "+model.getQuantity());
                        productamount = ((Integer.valueOf(model.getPPrice()))*(Integer.valueOf(model.getQuantity())));
                        overallamount = overallamount+productamount;
                        holder.deleteitem.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                cartref.child(model.getPId()).removeValue();
                                if(!tv_cartisempty.isShown()){
                                    btn_proceedtocheckout.setVisibility(View.GONE);
                                }
                            }
                        });
                    }

                    @NonNull
                    @Override
                    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.cartitemlayout,parent,false);
                        CartViewHolder holder =new CartViewHolder(view);
                        return holder;
                    }
                };
        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }
}