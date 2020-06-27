package com.example.nchs_task;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CustomerOrderDetails extends AppCompatActivity {

    DatabaseHelper db = new DatabaseHelper(this);
    Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_order_details);

        TextView data = (TextView) findViewById(R.id.cust_details_tv);

        String selectQuery = "select * from "+ DatabaseHelper.CUSTOMER_TABLE;

        cursor = db.getData(selectQuery);
        if (cursor != null && cursor.getCount()!=0) {
            if (cursor.moveToFirst()) {
                do {

                    String order_id = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_ORDER_ID));
                    String item_id = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_ITEM_ID));
                    String item_name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_ITEM_NAME));
                    String quantity = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_ORDERED_QUANTITY));
                    String total = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_ORDER_TOTAL));
                    String date_time = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_DATE_TIME));

                    String tempstr = data.getText().toString() + "\n\n";
                    tempstr = "" + tempstr + "\tORDER ID :" + order_id + "\t\tITEM ID :"
                            + item_id + "\t\tITEM NAME :" + item_name +"\t\tQUANTITY :" + quantity +"\t\tPRICE :" + total +"\t\tDATE-TIME :" + date_time ;
                    data.setText(tempstr);



                }while (cursor.moveToNext());
            }
            cursor.close();


        }


    }
}