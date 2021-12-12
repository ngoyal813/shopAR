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
import com.example.shopar.models.categoryModel;

import java.util.List;

public class home_recyclerview_adapter extends RecyclerView.Adapter<home_recyclerview_adapter.viewholder> {

    private List<categoryModel> categoriesList;
    private Context context;

    public home_recyclerview_adapter(List<categoryModel> categoriesList, Context context) {
        this.categoriesList = categoriesList;
        this.context = context;
    }

    public void setCategoriesList(List<categoryModel> categoriesList) {
        this.categoriesList = categoriesList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.home_categories_item_layout,parent,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        holder.category_title.setText(this.categoriesList.get(position).getCategory_title());
        Glide.with(context).load(this.categoriesList.get(position).getCategory_image()).into(holder.category_image);
    }

    @Override
    public int getItemCount() {
        if(this.categoriesList !=null){
            return categoriesList.size();
        }
        return 0;
    }

    public class viewholder extends RecyclerView.ViewHolder{
        ImageView category_image;
        TextView category_title;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            category_image = itemView.findViewById(R.id.iv_home_Categories);
            category_title = itemView.findViewById(R.id.tv_home_categories);
        }
    }
}
