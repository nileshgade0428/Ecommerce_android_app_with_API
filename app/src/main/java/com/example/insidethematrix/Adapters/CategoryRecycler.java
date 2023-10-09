package com.example.insidethematrix.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.insidethematrix.HomeActivity;
import com.example.insidethematrix.Model.ProductModel;
import com.example.insidethematrix.R;
import com.example.insidethematrix.RetrofitAPI.ProductAPI;
import com.example.insidethematrix.RetrofitAPI.RetrofitClientinstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryRecycler extends RecyclerView.Adapter<CategoryRecycler.MyViewHolder> {
    Context context;
    List<String> categoryList;
    ProductAPI productAPI;
    ArrayList<ProductModel> arrayList;
    ProductRecycler productRecycler;
    RecyclerView recyclerView;
    public CategoryRecycler(Context context, List<String> categoryList, ArrayList<ProductModel> arrayList, RecyclerView recyclerView){
        this.context=context;
       this.categoryList=categoryList;
       this.recyclerView=recyclerView;
       this.arrayList=arrayList;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.recycler_horizontal,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        String myCategory=categoryList.get(position);
        holder.txtCategory.setText(myCategory);

        holder.parent_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (categoryList.get(position).equals("Electronics") )
                {
                    getElectronics(categoryList.get(position));
                }
                else if (categoryList.get(position).equals("Jewellery") )
                {
                    getJewellery(categoryList.get(position));
                }
                else if (categoryList.get(position).equals("Men's clothing") )
                {
                    getMens(categoryList.get(position));
                }
                else if (categoryList.get(position).equals("Women's clothing") )
                {
                    getWomens(categoryList.get(position));
                }
                else
                    {
                        getAllProduct();
                    }
            }
        });

    }


    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView txtCategory;
        ConstraintLayout parent_layout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            txtCategory=itemView.findViewById(R.id.textCategory);
            parent_layout = itemView.findViewById(R.id.constraint_parent);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context,getAdapterPosition(),Toast.LENGTH_SHORT).show();

                }
            });
        }
    }

    public void getMens(String type)
    {
        productAPI=RetrofitClientinstance.getRetrofitInstance().create(ProductAPI.class);
        productAPI.getSpecificCatagory(type).enqueue(new Callback<ArrayList<ProductModel>>() {
            @Override
            public void onResponse(Call<ArrayList<ProductModel>> call, Response<ArrayList<ProductModel>> response) {
                if (response.body().size()>0)
                {
                    HomeActivity.productRecycler.updateList(response.body());
                    arrayList=response.body();
                    productRecycler=new ProductRecycler(context.getApplicationContext(), arrayList);
                    recyclerView.setAdapter(productRecycler);
                    recyclerView.setLayoutManager(new LinearLayoutManager(context));
                }
                else
                {
                    Toast.makeText(context,"Error in Fetching Data",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ProductModel>> call, Throwable t) {
                Toast.makeText(context,t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }

    public void getWomens(String type)
    {
        productAPI=RetrofitClientinstance.getRetrofitInstance().create(ProductAPI.class);
        productAPI.getSpecificCatagory(type).enqueue(new Callback<ArrayList<ProductModel>>() {
            @Override
            public void onResponse(Call<ArrayList<ProductModel>> call, Response<ArrayList<ProductModel>> response) {
                if (response.body().size()>0)
                {
                    HomeActivity.productRecycler.updateList(response.body());
                    arrayList=response.body();
                    productRecycler=new ProductRecycler(context.getApplicationContext(), arrayList);
                    recyclerView.setAdapter(productRecycler);
                    recyclerView.setLayoutManager(new LinearLayoutManager(context));
                }
                else
                {
                    Toast.makeText(context,"Error in Fetching Data",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ProductModel>> call, Throwable t) {
                Toast.makeText(context,t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }

    public void getJewellery(String type)
    {
        productAPI=RetrofitClientinstance.getRetrofitInstance().create(ProductAPI.class);
        productAPI.getSpecificCatagory(type).enqueue(new Callback<ArrayList<ProductModel>>() {
            @Override
            public void onResponse(Call<ArrayList<ProductModel>> call, Response<ArrayList<ProductModel>> response) {
                if (response.body().size()>0)
                {
                    HomeActivity.productRecycler.updateList(response.body());
                    arrayList=response.body();
                    productRecycler=new ProductRecycler(context.getApplicationContext(), arrayList);
                    recyclerView.setAdapter(productRecycler);
                    recyclerView.setLayoutManager(new LinearLayoutManager(context));
                }
                else
                {
                    Toast.makeText(context,"Error in Fetching Data",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ProductModel>> call, Throwable t) {
                Toast.makeText(context,t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }

    public void getElectronics(String type)
    {
        productAPI=RetrofitClientinstance.getRetrofitInstance().create(ProductAPI.class);
        productAPI.getSpecificCatagory(type).enqueue(new Callback<ArrayList<ProductModel>>() {
            @Override
            public void onResponse(Call<ArrayList<ProductModel>> call, Response<ArrayList<ProductModel>> response) {
                if (response.body().size()>0)
                {
                    HomeActivity.productRecycler.updateList(response.body());
                    arrayList=response.body();
                    productRecycler=new ProductRecycler(context.getApplicationContext(), arrayList);
                    recyclerView.setAdapter(productRecycler);
                    recyclerView.setLayoutManager(new LinearLayoutManager(context));
                }
                else
                {
                    Toast.makeText(context,"Error in Fetching Data",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ProductModel>> call, Throwable t) {
                Toast.makeText(context,t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }

    public void getAllProduct()
    {
        productAPI=RetrofitClientinstance.getRetrofitInstance().create(ProductAPI.class);
        productAPI.getMyProduct().enqueue(new Callback<ArrayList<ProductModel>>() {
            @Override
            public void onResponse(Call<ArrayList<ProductModel>> call, Response<ArrayList<ProductModel>> response) {
                if (response.body().size()>0)
                {
                    HomeActivity.productRecycler.updateList(response.body());
                }
                else
                {
                    Toast.makeText(context,"Error in Fetching Data",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ProductModel>> call, Throwable t) {
                Toast.makeText(context,t.getMessage(),Toast.LENGTH_LONG).show();

            }
        });
    }
}