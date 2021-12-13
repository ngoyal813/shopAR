package com.example.shopar.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.shopar.R;
import com.example.shopar.models.productavailablemodel;

import java.util.List;

public class productavailableadapter extends RecyclerView.Adapter<productavailableadapter.viewholder> {

    public productavailableadapter(List<productavailablemodel> productavailableist, Context context) {
        this.productavailableist = productavailableist;
        this.context = context;
    }

    public void setProductavailableist(List<productavailablemodel> productavailableist) {
        this.productavailableist = productavailableist;
        notifyDataSetChanged();
    }

    private List<productavailablemodel> productavailableist;
    private Context context;



    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.productsavailableitemlayout,parent,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {

        Glide.with(context).load(productavailableist.get(position).getProductImage()).into(holder.productImage);
        holder.productPrice.setText(productavailableist.get(position).getProductPrice());
        holder.productName.setText(productavailableist.get(position).getProductName());

    }

    @Override
    public int getItemCount() {
        if (this.productavailableist != null){
            return productavailableist.size();
        }
        return 0;
    }

    public class viewholder extends RecyclerView.ViewHolder {

        ImageView productImage;
        TextView productName;
        TextView productPrice;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.iv_product_image);
            productName = itemView.findViewById(R.id.tv_product_name);
            productPrice = itemView.findViewById(R.id.tv_product_price);
        }
    }
}
