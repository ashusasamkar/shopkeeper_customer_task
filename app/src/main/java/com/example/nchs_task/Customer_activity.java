package com.example.nchs_task;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Date;

public class Customer_activity extends AppCompatActivity {

    private static final String TAG="Customer_activity";
    ListView listView;
    Button order_btn,logout_btn;
    TextView total_price_tv;

    ArrayList<Customer> customers_list = new ArrayList<>();
    DatabaseHelper db = new DatabaseHelper(this);




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_activity);
        logout_btn=(Button)findViewById(R.id.logout_btn);
        listView = (ListView)findViewById(R.id.list_item);
        order_btn = (Button)findViewById(R.id.order_btn);
        total_price_tv = (TextView)findViewById(R.id.total_quantity_price_tv);

        showItemList();//Items list
        orderItems();//Order Item

        logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Customer_activity.this,MainActivity.class);
                startActivity(intent);

            }
        });
    }



    private void orderItems() {
        order_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    onClickOrder();
                }
                catch (Exception e){
                    Log.e(TAG,"Error while ordering items "+e);
                }
            }
        });

    }

    public void onClickOrder(){
        try{
            if(listView != null){
                int count = 0;
                for(int i = 0; i< listView.getChildCount();i++){
                    View vie = listView.getChildAt(i);
                    TextView item_id_tv = (TextView)vie.findViewById(R.id.item_id_tv);
                    TextView item_name_tv = (TextView)vie.findViewById(R.id.item_name_tv);
                    TextView item_price_tv = (TextView)vie.findViewById(R.id.item_price_tv);
                    EditText ordered_quantity_ed= (EditText) vie.findViewById(R.id.order_quantity_ed);
                    TextView  total_price_tv= (TextView) vie.findViewById(R.id.total_quantity_price_tv);

                    Date date=java.util.Calendar.getInstance().getTime();


                        if(!StringUtils.isNullOrEmpty(ordered_quantity_ed.getText().toString())){

                            ContentValues values = new ContentValues();
                            values.put(DatabaseHelper.COL_ITEM_ID, item_id_tv.getText().toString());
                            values.put(DatabaseHelper.COL_ITEM_NAME, item_name_tv.getText().toString());
                            values.put(DatabaseHelper.COL_ORDERED_QUANTITY, ordered_quantity_ed.getText().toString());
                            values.put(DatabaseHelper.COL_ORDER_TOTAL, total_price_tv.getText().toString());
                            values.put(DatabaseHelper.COL_DATE_TIME, date.toString());
                            long result = db.insertData(DatabaseHelper.CUSTOMER_TABLE, values);
                            count++;
                            if (result == -1) {
                                Toast.makeText(this, "Problem in Order items !", Toast.LENGTH_SHORT).show();

                            }
                        }

                }
                if(count>0){
                Toast.makeText(this, "Items Ordered successfully..", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(this, "Please Enter Quantity to order items. !", Toast.LENGTH_SHORT).show();
                }
            }


        }
        catch (Exception e){
            Log.e(TAG,"Error while ordering items "+e);
        }
    }

    private void showItemList() {
        try {
        String selectQuery = "select * from "+DatabaseHelper.SHOPKEEPER_TABLE;
        Cursor cursor = db.getData(selectQuery);


            if (cursor != null && cursor.getCount()!=0) {
                if (cursor.moveToFirst()) {
                    do {
                        String item_id = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_ITEM_ID));
                        String item_name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_ITEM_NAME));
                        String item_price = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_ITEM_PRICE));
                        //create the Customer object
                        Customer customer = new Customer(item_id, item_name, item_price);
                        customers_list.add(customer);


                    }while (cursor.moveToNext());
                }
                cursor.close();

            }


        Customer_listAdapter adapter = new Customer_listAdapter(this,R.layout.list_item_adapter,customers_list);
        listView.setAdapter(adapter);
        }
        catch (Exception e){
            Log.e(TAG,"Error whlie getting data from shopkeeper table" + e);
        }

    }
}