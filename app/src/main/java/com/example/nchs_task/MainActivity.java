package com.example.nchs_task;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
 RadioGroup select_login;
 RadioButton selected_login;
 Button login;
 EditText uname,pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        select_login = (RadioGroup)findViewById(R.id.select);
        login = (Button)findViewById(R.id.login_btn);
        uname = (EditText)findViewById(R.id.username);
        pass = (EditText)findViewById(R.id.password);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId= select_login.getCheckedRadioButtonId();
                check_login(selectedId);
            }
        });


    }

    private void check_login(int selectedId) {

        switch (selectedId){
            case R.id.shopkeeper_radio_btn :
                validate_login_shop(uname.getText().toString(),pass.getText().toString());

                break;

            case R.id.customer_radio_btn :
                validate_login_cust(uname.getText().toString(),pass.getText().toString());
                break;
            default :
                Toast.makeText(this, "Please select Valid option", Toast.LENGTH_SHORT).show();


        }

    }

    //Check validation for Customer login
    private void validate_login_cust(String username, String password) {
        if(!StringUtils.isNullOrEmpty(uname.getText().toString()) && !StringUtils.isNullOrEmpty(pass.getText().toString())) {
            if (uname.getText().toString().equalsIgnoreCase("rahul") && pass.getText().toString().equals("rahul123")) {
                Toast.makeText(this, "Customer login Successfully", Toast.LENGTH_SHORT).show();

                Intent i = new Intent(this,Customer_activity.class);
                startActivity(i);
                uname.setText("");
                pass.setText("");
            }
            else {
                Toast.makeText(this, "Username or Password is Incorrect !", Toast.LENGTH_SHORT).show();

            }
        }
        else {
            Toast.makeText(this, "Please enter Username and Password !", Toast.LENGTH_SHORT).show();
        }
    }

    //Check validation for Shopkeeper Login
    private void validate_login_shop(String username, String password) {
        if(!StringUtils.isNullOrEmpty(uname.getText().toString()) && !StringUtils.isNullOrEmpty(pass.getText().toString())) {
            if (uname.getText().toString().equalsIgnoreCase("ashwini") && pass.getText().toString().equals("ash123")) {
                Toast.makeText(this, "Shopkeeper login Successfully", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(this,ShopKeeper_activity.class);
                startActivity(i);
                uname.setText("");
                pass.setText("");

            }
            else {
                Toast.makeText(this, "Username or Password is Incorrect !", Toast.LENGTH_SHORT).show();

            }
        }
        else {
            Toast.makeText(this, "Please enter Username and Password !", Toast.LENGTH_SHORT).show();
        }
    }


}