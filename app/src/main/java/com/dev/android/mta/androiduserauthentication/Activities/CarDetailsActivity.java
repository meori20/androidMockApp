package com.dev.android.mta.androiduserauthentication.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dev.android.mta.androiduserauthentication.Model.Item;
import com.dev.android.mta.androiduserauthentication.R;

import static com.dev.android.mta.androiduserauthentication.constans.ActivityExtrasConstans.CAR_ITEM_OBJECT;

public class CarDetailsActivity extends AppCompatActivity {

    private Item mCar;

    private ImageView mImageView;

    private TextView mCarMakeText;
    private TextView mCarModelText;
    private TextView mCarYearText;
    private TextView mCarPriceText;

    private EditText mAddReviewEditText;

    private Button mAddReviewButton;
    private Button mPurchaseButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_car_details);
            mCar = getIntent().getParcelableExtra(CAR_ITEM_OBJECT);
            bindClassToLayout();
        }catch (Exception e){
            e.getMessage();
        }
    }



    private void bindClassToLayout() {
         mImageView = findViewById(R.id.carDetailsImageView);
         mCarMakeText = findViewById(R.id.carMakeLabel);
         mCarModelText = findViewById(R.id.carModelLabel);
         mCarYearText = findViewById(R.id.carYearLabel);
         mCarPriceText = findViewById(R.id.carPriceLabel);
         mAddReviewEditText = findViewById(R.id.addReviewEditText);
         mPurchaseButton = findViewById(R.id.carPurchaseButton);
         mAddReviewButton = findViewById(R.id.addReviewButton);

         if(mCar != null){
             Glide.with(this)
                     .load(mCar.getCarImage().toString())
                     .into(mImageView);
             mCarMakeText.setText(mCar.getCarMake());
             mCarModelText.setText(mCar.getCarModel());
             mCarYearText.setText(mCar.getCarModelYear());
             mCarPriceText.setText(mCar.getPrice());
         }

    }
}
