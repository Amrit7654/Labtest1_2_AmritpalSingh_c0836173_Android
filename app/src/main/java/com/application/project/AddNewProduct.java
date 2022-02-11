package com.application.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

public class AddNewProduct extends AppCompatActivity {

    EditText name ;
    EditText des ;
    EditText price ;
    EditText lat ;
    EditText lng ;
    Button mSaveBtn;


    DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_product);

         name = findViewById(R.id.mNameEt);
         des = findViewById(R.id.mDesEt);
         price = findViewById(R.id.mPriceEt);
         lat = findViewById(R.id.mLatEt);
         lng = findViewById(R.id.mLngEt);
        mSaveBtn=findViewById(R.id.saveBtn);

        dbHelper=new DbHelper(AddNewProduct.this);


        mSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveProduct(name.getText().toString().trim(),des.getText().toString().trim(),
                        price.getText().toString().trim(),lat.getText().toString().trim(),lng.getText().toString().trim());
            }
        });
    }


    private void saveProduct(String name, String descripton,String price,String lat,String lon) {
        long primaryKey = dbHelper.insertCategory(name, descripton,price,lat,lon);
        Toast.makeText(AddNewProduct.this, "Product Added", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(AddNewProduct.this,ListActivity.class));
        finish();

    }
}