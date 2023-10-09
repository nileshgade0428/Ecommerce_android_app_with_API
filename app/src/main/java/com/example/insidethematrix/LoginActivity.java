package com.example.insidethematrix;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.insidethematrix.Model.UserModel;
import com.example.insidethematrix.RetrofitAPI.ProductAPI;
import com.example.insidethematrix.RetrofitAPI.RetrofitClientinstance;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    EditText email,password;
    ProductAPI productAPI;
    ArrayList<UserModel> arrayList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.inputEmail);
        password = findViewById(R.id.inputPassword);

    }

    public void btnLogin(View view)
    {
        getUserData();

        if (email.getText().toString().equals("") && password.getText().toString().equals(""))
        {
            email.setError("Enter Your Email");
            password.setError("Enter Your Password");

        }
        else
        {
            for (int i=0;i<arrayList.size();i++)
            {
                if (arrayList.get(i).getEmail().equals(email.getText().toString()) &&
                        arrayList.get(i).getPassword().equals(password.getText().toString()))
                {
                    Intent intent=new Intent(getApplicationContext(),HomeActivity.class);
                    startActivity(intent);

                    Toast.makeText(getApplicationContext(),"Login Successful",Toast.LENGTH_SHORT).show();
                    return;
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Invalid Credentials",Toast.LENGTH_SHORT).show();
                }
            }
        }
//        startActivity(new Intent(this,MainActivity.class));
    }

    public void getUserData(){

        productAPI= RetrofitClientinstance.getRetrofitInstance().create(ProductAPI.class);
        productAPI.getUsers().enqueue(new Callback<ArrayList<UserModel>>() {
            @Override
            public void onResponse(Call<ArrayList<UserModel>> call, Response<ArrayList<UserModel>> response) {
                if (response.body().size()>0)
                {
                    arrayList=response.body();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Error in Fetching data",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<UserModel>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void forgetpwd(View view) {
        Uri uri = Uri.parse("https://fakestoreapi.com/users");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
        Toast.makeText(this, "Fake store Api", Toast.LENGTH_SHORT).show();
    }

    public void guest(View view) {
        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
        startActivity(intent);

    }
}