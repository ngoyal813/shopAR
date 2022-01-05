package com.example.shopar.Viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.shopar.Interfaces.ItemClickListener;
import com.example.shopar.R;
import com.example.shopar.databinding.ProductsavailableitemlayoutBinding;

public class Productviewholder  extends RecyclerView.ViewHolder implements View.OnClickListener {

    public ImageView ProductImage;
    public TextView tvProductName,tvProductPrice;
    public  ItemClickListener listener;
    public  Productviewholder(View itemview){
        super(itemview);
        ProductImage = itemview.findViewById(R.id.iv_product_image);
        tvProductName = itemview.findViewById(R.id.tv_product_name);
        tvProductPrice = itemview.findViewById(R.id.tv_product_price);
    }

    public void setItemClickListener(ItemClickListener listener)
    {
        this.listener = listener;
    }
    @Override
    public void onClick(View view) {

        listener.onClick(view, getAdapterPosition(),false);
    }
}
