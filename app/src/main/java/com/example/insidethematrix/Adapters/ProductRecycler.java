package com.example.insidethematrix.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.insidethematrix.Model.ProductModel;
import com.example.insidethematrix.ProductDetail;
import com.example.insidethematrix.ProductsActivity;
import com.example.insidethematrix.R;

import java.util.ArrayList;

public class ProductRecycler extends RecyclerView.Adapter<ProductRecycler.MyViewHolder> {
 Context context;
 ArrayList<ProductModel> arrayList;
 public ProductRecycler(Context context,ArrayList<ProductModel> arrayList)
 {
     this.context=context;
     this.arrayList=arrayList;
 }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.recycler_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

     ProductModel model = arrayList.get(position);
        holder.pTitle.setText(model.getTitle());
        String price = String.valueOf(model.getPrice());
        holder.pPrice.setText("₹"+price);
        Glide.with(context)
                .load(model.getImage())
                .fitCenter()
                .into(holder.pImage);
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addToCart(model);
                Intent intent = new Intent(context, ProductDetail.class);
                intent.putExtra("Product_Title",arrayList.get(position).title);
                intent.putExtra("Product_Desc",arrayList.get(position).description);
                intent.putExtra("Product_Price",arrayList.get(position).price);
                intent.putExtra("Product_Image",arrayList.get(position).image);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });


    }

    private void addToCart(ProductModel model) {
        if (context instanceof ProductsActivity) {
            ((ProductsActivity) context).addToCart(model);
        }
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public void filteredList(ArrayList<ProductModel> filterData) {
     arrayList=filterData;
     notifyDataSetChanged();

    }


    public class MyViewHolder extends RecyclerView.ViewHolder{
     ImageView pImage;
     TextView pTitle,pPrice;
     RelativeLayout parentLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            pImage=itemView.findViewById(R.id.imageProductDetail);
            pTitle=itemView.findViewById(R.id.textTitle);
            //pDesc=itemView.findViewById(R.id.textDescription);
            pPrice=itemView.findViewById(R.id.textPrice);
            parentLayout = itemView.findViewById(R.id.ParentLayout);

        }
    }
    public  void updateList(ArrayList<ProductModel> arrayList2){
        this.arrayList=arrayList2;
        notifyDataSetChanged();
    }


}
