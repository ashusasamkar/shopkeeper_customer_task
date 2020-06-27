package com.example.nchs_task;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ShopKeeper_activity extends AppCompatActivity implements View.OnClickListener {

    Button add_items_btn,view_customerDetails_btn,logout_btn;
    EditText item_name_ed,item_price_ed,item_quantity_ed;
    DatabaseHelper db_helper = new DatabaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_keeper_activity);

        logout_btn =(Button)findViewById(R.id.logout_shop_btn);
        logout_btn.setOnClickListener(this);

        item_name_ed = (EditText)findViewById(R.id.item_name);
        item_price_ed = (EditText)findViewById(R.id.item_price);
        item_quantity_ed = (EditText)findViewById(R.id.item_quantity);

        add_items_btn = (Button)findViewById(R.id.add_btn);
        add_items_btn.setOnClickListener(this);

        view_customerDetails_btn =(Button)findViewById(R.id.cust_details);
        view_customerDetails_btn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.add_btn:
                add_items();
                break;

            case R.id.cust_details:
                get_customerDetails();
                break;

            case R.id.logout_shop_btn:
                Intent intent = new Intent(ShopKeeper_activity.this,MainActivity.class);
                startActivity(intent);

                break;

            default:
                break;
        }
    }

    private void get_customerDetails() {


        Intent intent = new Intent(this,CustomerOrderDetails.class);
        startActivity(intent);

    }

    private void add_items() {

        if(!StringUtils.isNullOrEmpty(item_name_ed.getText().toString()) && !StringUtils.isNullOrEmpty(item_price_ed.getText().toString()) && !StringUtils.isNullOrEmpty(item_quantity_ed.getText().toString())) {

            ContentValues values = new ContentValues();
            values.put(DatabaseHelper.COL_ITEM_NAME, item_name_ed.getText().toString());
            values.put(DatabaseHelper.COL_ITEM_PRICE, item_price_ed.getText().toString());
            values.put(DatabaseHelper.COL_ITEM_QUANTITY, item_quantity_ed.getText().toString());

            long res = db_helper.insertData(DatabaseHelper.SHOPKEEPER_TABLE, values);
            if (res == -1) {
                Toast.makeText(this, "Items not added in table", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Items added Successfully..!", Toast.LENGTH_SHORT).show();
                item_name_ed.setText("");
                item_quantity_ed.setText("");
                item_price_ed.setText("");
            }

        }
        else {
            Toast.makeText(this, "Please enter all details..!", Toast.LENGTH_SHORT).show();
        }

    }

}