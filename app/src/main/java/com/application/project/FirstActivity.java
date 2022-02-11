package com.application.project;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;


import androidx.appcompat.app.AppCompatActivity;


import java.util.ArrayList;
import java.util.List;

public class FirstActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 3000;

    List<AddProductModel> list;

    DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        dbHelper=new DbHelper(FirstActivity.this);

        list=new ArrayList<>();

        insertProduct();



    }

    private void insertProduct() {
       saveProduct("New Product","Description","20","26.2006","92.9376");

    }

    private void saveProduct(String name, String descripton,String price,String lat,String lon) {
        long primaryKey = dbHelper.insertCategory(name, descripton,price,lat,lon);
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                Intent i = new Intent(FirstActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        }, SPLASH_TIME_OUT);

    }
}