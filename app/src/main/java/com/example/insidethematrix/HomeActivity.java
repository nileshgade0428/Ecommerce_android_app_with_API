package com.example.insidethematrix;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.insidethematrix.Adapters.CategoryRecycler;
import com.example.insidethematrix.Adapters.ProductRecycler;
import com.example.insidethematrix.Model.ProductModel;
import com.example.insidethematrix.RetrofitAPI.ProductAPI;
import com.example.insidethematrix.RetrofitAPI.RetrofitClientinstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    RecyclerView recyclerViewCategory;
    EditText searchView;
    ArrayList<ProductModel> arrayList;
    List<String> categoryList=new ArrayList<>();
    ProductAPI productAPI;
    public static ProductRecycler productRecycler;
    CategoryRecycler categoryRecycler;
    String[] sorting={"Desc","Asec","Limit 5"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getIDs();
        getProducts();
        getCategory();
    }

    public void cart(View view){
        Intent i = new Intent(HomeActivity.this, ProductsActivity.class);
        startActivity(i);
    }
    public void getIDs()
    {
        recyclerView = findViewById(R.id.recyclerview);
        recyclerViewCategory = findViewById(R.id.recyclerHorizontal);
        searchView = findViewById(R.id.edittextSearch);

        searchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());

            }
        });

    }

    private void filter(String text) {
        ArrayList<ProductModel> filterData=new ArrayList<>();

        for (ProductModel item : arrayList)
        {
            if (item.getTitle().toLowerCase().contains(text.toLowerCase()))
            {
                filterData.add(item);
            }

        }
        productRecycler.filteredList(filterData);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_cart,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int item_id=item.getItemId();
        if (item_id == R.id.action_desc)
        {
            getDescindingOrderProduct();
        }
        else if (item_id == R.id.action_asec)
        {
            getAsecindingProduct();
        }
        else if (item_id == R.id.action_limit)
        {
            getLimitedProduct();
        }
        return true;
    }

    public void getProducts(){

        productAPI = RetrofitClientinstance.getRetrofitInstance().create(ProductAPI.class);
        productAPI.getMyProduct().enqueue(new Callback<ArrayList<ProductModel>>() {
            @Override
            public void onResponse(Call<ArrayList<ProductModel>> call, Response<ArrayList<ProductModel>> response) {
                if (response.body().size()>0)
                {
                    arrayList=response.body();
                    productRecycler=new ProductRecycler(getApplicationContext(),arrayList);
                    recyclerView.setAdapter(productRecycler);
                    recyclerView.setLayoutManager(new GridLayoutManager(HomeActivity.this,1));
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Error in Fetching Data",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ProductModel>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });

    }
    public void getCategory(){

        productAPI = RetrofitClientinstance.getRetrofitInstance().create(ProductAPI.class);
        productAPI.getMYCategory().enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                if (response.body().size()>0)
                {
                    categoryList.add("All Product");
                    categoryList.addAll(response.body());
                    categoryRecycler=new CategoryRecycler(getApplicationContext(),categoryList,arrayList,recyclerView);
                    recyclerViewCategory.setAdapter(categoryRecycler);
                    recyclerViewCategory.setLayoutManager(new LinearLayoutManager(HomeActivity.this,LinearLayoutManager.HORIZONTAL,false));
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Category not Found",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });

    }

    public void getDescindingOrderProduct()
    {
        productAPI=RetrofitClientinstance.getRetrofitInstance().create(ProductAPI.class);
        productAPI.getDescending().enqueue(new Callback<ArrayList<ProductModel>>() {
            @Override
            public void onResponse(Call<ArrayList<ProductModel>> call, Response<ArrayList<ProductModel>> response) {
                if (response.body().size()>0)
                {
                    HomeActivity.productRecycler.updateList(response.body());
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Error in Fetching Data",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ProductModel>> call, Throwable t) {

            }
        });
    }

    public void getAsecindingProduct(){
        productAPI=RetrofitClientinstance.getRetrofitInstance().create(ProductAPI.class);
        productAPI.getAsecinding().enqueue(new Callback<ArrayList<ProductModel>>() {
            @Override
            public void onResponse(Call<ArrayList<ProductModel>> call, Response<ArrayList<ProductModel>> response) {
                if (response.body().size()>0)
                {
                    HomeActivity.productRecycler.updateList(response.body());
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Error in Fetching Data",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ProductModel>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
    public void getLimitedProduct(){
        productAPI=RetrofitClientinstance.getRetrofitInstance().create(ProductAPI.class);
        productAPI.getLimited().enqueue(new Callback<ArrayList<ProductModel>>() {
            @Override
            public void onResponse(Call<ArrayList<ProductModel>> call, Response<ArrayList<ProductModel>> response) {
                if (response.body().size()>0)
                {
                    HomeActivity.productRecycler.updateList(response.body());
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Error in fetching Data",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ProductModel>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }


}