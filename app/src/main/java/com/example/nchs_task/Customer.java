package com.example.nchs_task;

import android.content.Context;

public class Customer {

    private String item_id;
    private String item_name;
    private String item_price;
    private String ordered_quantity;
    private String ordered_price;//As per total Quantity
    private String ordered_dateTime;
    private Context context;

    public Customer(Context context){
        this.context = context;
    }
    public Customer(String item_id, String item_name, String item_price) {
        this.item_id = item_id;
        this.item_name = item_name;
        this.item_price = item_price;

    }
    public Customer(String item_id, String item_name, String item_price, String ordered_quantity, String ordered_price/*, String ordered_dateTime*/) {
        this.item_id = item_id;
        this.item_name = item_name;
        this.item_price = item_price;
        this.ordered_quantity = ordered_quantity;
        this.ordered_price = ordered_price;
        //this.ordered_dateTime = ordered_dateTime;
    }

    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getItem_price() {
        return item_price;
    }

    public void setItem_price(String item_price) {
        this.item_price = item_price;
    }

    public String getOrdered_quantity() {
        return ordered_quantity;
    }

    public void setOrdered_quantity(String ordered_quantity) {
        this.ordered_quantity = ordered_quantity;
    }

    public String getOrdered_price() {
        return ordered_price;
    }

    public void setOrdered_price(String ordered_price) {
        this.ordered_price = ordered_price;
    }

    public String getOrdered_dateTime() {
        return ordered_dateTime;
    }

    public void setOrdered_dateTime(String ordered_dateTime) {
        this.ordered_dateTime = ordered_dateTime;
    }
}
