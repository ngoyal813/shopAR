package com.example.shopar.Viewholder;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.shopar.Interfaces.ItemClickListener;
import com.example.shopar.R;

public class CartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public ImageView ProductImage;
    public TextView tvProductName,tvProductPrice,quantity;
    public Button deleteitem;
    public ItemClickListener listener;
    public  CartViewHolder(View itemview){
        super(itemview);
        ProductImage = itemview.findViewById(R.id.iv_cartproductimage);
        tvProductName = itemview.findViewById(R.id.tv_cartproducttitle);
        tvProductPrice = itemview.findViewById(R.id.tv_cartproductprice);
        deleteitem = itemview.findViewById(R.id.btn_cartdeleteitem);
        quantity = itemview.findViewById(R.id.tv_cartqty);
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
