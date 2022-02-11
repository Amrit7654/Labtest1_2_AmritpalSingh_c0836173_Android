package com.application.project;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class ListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    DbHelper dbHelper;
    ProductsAdapter productsAdapter;
    TextInputEditText searchEt;
    BottomSheetDialog editDialog,addDialog;
    FloatingActionButton mFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        dbHelper=new DbHelper(ListActivity.this);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mFab = (FloatingActionButton) findViewById(R.id.fab);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(ListActivity.this));

        getAllProducts();


        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ListActivity.this,AddNewProduct.class));

            }
        });
    }

    private void saveProduct(String name, String descripton,String price,String lat,String lon) {
        long primaryKey = dbHelper.insertCategory(name, descripton,price,lat,lon);


    }



    private void getAllProducts() {
        List<AddProductModel> allProductsList= dbHelper.getAllCategories();
        productsAdapter=new ProductsAdapter(ListActivity.this,allProductsList);
        recyclerView.setAdapter(productsAdapter);
        productsAdapter.notifyDataSetChanged();
    }


    //---------------------------lEDGER lIST Adapter------------------------------------//
    public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.MyViewHolder> {

        Context context;
        List<AddProductModel> childFeedList;


        public ProductsAdapter(Context context, List<AddProductModel> childFeedList) {
            this.context = context;
            this.childFeedList = childFeedList;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_list_design, parent, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, final int position) {
            AddProductModel model = childFeedList.get(position);
            holder.mName.setText(model.getProductName());
            holder.mDescription.setText(model.getProductDescription());
            holder.mPrice.setText("$"+model.getProductPrice());



            holder.mDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dbHelper.deleteCategory(model);
                    getAllProducts();
                }
            });

            holder.mEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(ListActivity.this,EditProductActivity.class);
                    intent.putExtra("id",model.getId());
                    startActivity(intent);
                }
            });


        }


        @Override
        public int getItemCount() {
            return childFeedList.size();
        }




        public class MyViewHolder extends RecyclerView.ViewHolder {

            TextView mName,mDescription,mPrice;
            ImageView mDelete,mEdit;


            public MyViewHolder(View itemView) {
                super(itemView);

                mName=itemView.findViewById(R.id.Name_tv);
                mDescription=itemView.findViewById(R.id.description_tv);
                mPrice=itemView.findViewById(R.id.price_tv);
                mDelete=itemView.findViewById(R.id.mDeleteBtn);
                mEdit=itemView.findViewById(R.id.mEditBtn);



            }
        }
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(ListActivity.this,MainActivity.class));
        finish();
    }
}