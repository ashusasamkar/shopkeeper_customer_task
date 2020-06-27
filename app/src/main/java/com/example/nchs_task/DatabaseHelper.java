package com.example.nchs_task;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String TAG = "DatabaseHelper";

    //Database name
    public static final String DATABASE_NAME="shop_details.db";

    //Database Version
    public static final int DB_VERSION = 1;

    //Tables name
    public static final String SHOPKEEPER_TABLE  ="shopkeeper_table";
    public static final String CUSTOMER_TABLE="customer_table";

    //Common columns
    public static final String COL_ITEM_ID = "item_id";//primary key in shopkeeper table
    public static final String COL_ITEM_NAME ="item_name";
    public static final String COL_ITEM_PRICE ="item_price";

    //Columns Name for Shopkeeper Table
    public static final String COL_ITEM_QUANTITY = "item_quantity";

    //Columns Name for Customer Table
    public static final String COL_ORDER_ID = "order_id";//primary key in customer table
    public static final String COL_DATE_TIME = "ordered_DateTime";
    public static final String COL_ORDERED_QUANTITY = "ordered_quantity";
    public static final String COL_ORDER_TOTAL = "order_total_price";//quantity-wise Price

    //Create Tables
    //Create shopkeeper table
    public static final String CREATE_SHOPKEEPER_TABLE ="CREATE TABLE "
            + SHOPKEEPER_TABLE + "(" + COL_ITEM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COL_ITEM_NAME
            + " TEXT," + COL_ITEM_QUANTITY + " INTEGER," + COL_ITEM_PRICE
            + " REAL" + ")";

    //Create customer table
    public static final String CREATE_CUSTOMER_TABLE ="CREATE TABLE "
            + CUSTOMER_TABLE + "(" + COL_ORDER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COL_ITEM_ID + " INTEGER," + COL_ITEM_NAME
            + " TEXT," + COL_ORDERED_QUANTITY + " INTEGER," + COL_ORDER_TOTAL
            + " REAL," +COL_DATE_TIME + " TEXT "+ ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DB_VERSION);
        //SQLiteDatabase db=getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        try {
            //creating required tables
            db.execSQL(CREATE_SHOPKEEPER_TABLE);
            db.execSQL(CREATE_CUSTOMER_TABLE);
        }
        catch (Exception e){
            Log.e(TAG,"Error in table creation");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Log the version upgrade.
        Log.w(TAG, "Upgrading from version " +oldVersion + " to " +newVersion + ", which will destroy all old data");

        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + SHOPKEEPER_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + CUSTOMER_TABLE);

        // create new tables
        onCreate(db);
    }

    //Insert data in table
    public long insertData(String table_name,ContentValues values){
        SQLiteDatabase db ;
        long result = 0;
        try{
            db = this.getWritableDatabase();
            result = db.insert(table_name,null,values);
        }
        catch (Exception e){
            e.printStackTrace();
            Log.e(TAG, "DatabaseHelper Class(insertData) : "+e);

        }
        return result;
    }


    //get data from tables
    public Cursor getData(String selectQuery){
        SQLiteDatabase db ;
        Cursor cursor;
        try{
            db = this.getReadableDatabase();
            cursor = db.rawQuery(selectQuery,null);
            return cursor;

        }
        catch (Exception e){
            e.printStackTrace();
            Log.e(TAG, "DatabaseHelper Class(getData) : "+e);
            return null;
        }

    }
    // Method to Update an Existing
    public boolean updateData(String table_name,ContentValues updatedValues, String where,String[] whereArguments){
        SQLiteDatabase db;
        try{
            db=this.getReadableDatabase();
            db.update(table_name,updatedValues, where, whereArguments);

        }
        catch (Exception e){
            e.printStackTrace();
            Log.e(TAG, "DatabaseHelper Class(updateData) : "+e);
        }
        return true;
    }

}
