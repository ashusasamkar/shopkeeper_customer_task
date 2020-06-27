package com.example.nchs_task;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class Customer_listAdapter extends ArrayAdapter<Customer> {
    private static final String TAG = "Customer_listAdapter";

    private Context mcontext;
    private int mresource;
    private ArrayList<Customer> customer_obj;

    public Customer_listAdapter(Context context, int resource, ArrayList<Customer> objects) {
        super(context, resource, objects);
        mcontext = context;
        mresource = resource;
        customer_obj = objects;


    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //get the Customer Information
        final String item_id= getItem(position).getItem_id();
        final String item_name = getItem(position).getItem_name();
        final String item_price = getItem(position).getItem_price();

        //String ordered_quantity = getItem(position).getOrdered_quantity();
        //String ordered_price = getItem(position).getOrdered_price();


        if(convertView==null) {

            LayoutInflater inflater = LayoutInflater.from(mcontext);
            convertView = inflater.inflate(mresource, parent, false);
        }

        TextView id= (TextView) convertView.findViewById(R.id.item_id_tv);
        TextView name = (TextView)convertView.findViewById(R.id.item_name_tv);
        TextView price = (TextView)convertView.findViewById(R.id.item_price_tv);
        EditText ordered_quantity_ed = (EditText)convertView.findViewById(R.id.order_quantity_ed);
        final TextView ordered_price_tv = (TextView)convertView.findViewById(R.id.total_quantity_price_tv);

        id.setText(item_id);
        name.setText(item_name);
        price.setText(item_price);

       ordered_quantity_ed.addTextChangedListener(new TextWatcher() {
           @Override
           public void beforeTextChanged(CharSequence s, int start, int count, int after) {

           }

           @Override
           public void onTextChanged(CharSequence s, int start, int before, int count) {

           }

           @Override
           public void afterTextChanged(Editable s) {
               String quantity = s.toString();
               double total = Integer.parseInt(quantity) * Double.parseDouble(item_price);
               ordered_price_tv.setText(""+total);

               Customer customer = new Customer(item_id,item_name,item_price,quantity,String.valueOf(total));
               customer.setOrdered_quantity(quantity);
               customer.setOrdered_price(String.valueOf(total));

           }
       });



        return convertView;

    }
}
