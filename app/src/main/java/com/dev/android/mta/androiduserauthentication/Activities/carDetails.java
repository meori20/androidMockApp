package com.dev.android.mta.androiduserauthentication.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.dev.android.mta.androiduserauthentication.Model.Item;
import com.dev.android.mta.androiduserauthentication.R;

public class carDetails extends AppCompatActivity {
    Item car;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_car_details);
            car = getIntent().getParcelableExtra("car");
        }catch (Exception e){
            e.getMessage();
        }
    }
}
