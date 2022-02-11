package com.application.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.bottomsheet.BottomSheetDialog;

public class EditProductActivity extends AppCompatActivity {

    String id,name,des,price,lat,lng;

    EditText nameEt;
    EditText desEt;
    EditText priceEt ;
    EditText latEt;
    EditText lngEt;

    Button savebTM;
    DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_product);

        dbHelper=new DbHelper(EditProductActivity.this);

        Bundle bundle=getIntent().getExtras();
        id=bundle.getString("id");
        name=bundle.getString("name");
        des=bundle.getString("des");
        price=bundle.getString("price");
        lat=bundle.getString("lat");
        lng=bundle.getString("lng");

         nameEt = findViewById(R.id.mNameEt);
         desEt = (EditText) findViewById(R.id.mDesEt);
         priceEt = (EditText) findViewById(R.id.mPriceEt);
         latEt = (EditText) findViewById(R.id.mLatEt);
         lngEt = (EditText) findViewById(R.id.mLngEt);
        savebTM = (Button) findViewById(R.id.saveBtn);


        nameEt.setText(name);
        desEt.setText(des);
        priceEt.setText(price);
        latEt.setText(lat);
        lngEt.setText(lng);



        savebTM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.updateCategory(id,nameEt.getText().toString().trim(),desEt.getText().toString().trim(),
                        priceEt.getText().toString().trim(),latEt.getText().toString().trim(),lngEt.getText().toString().trim());

               startActivity(new Intent(EditProductActivity.this,ListActivity.class));
               finish();
            }
        });
    }
}