package com.dev.android.mta.androiduserauthentication.implementaion;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.dev.android.mta.androiduserauthentication.Activities.ItemsMenu;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.dev.android.mta.androiduserauthentication.Model.User;

/**
 * Created by Ori on 4/25/2018.
 */

public abstract class AuthenticationMethod {
    protected FirebaseAuth mAuth;
    protected AppCompatActivity mMainActivity;
    public final String TAG = "AuthenticationMethod";


    public AuthenticationMethod(FirebaseAuth iAuth, AppCompatActivity mainActivity){
        mAuth = iAuth;
        mMainActivity = mainActivity;
    }

    protected void startNewActivityWithUserDetails(FirebaseUser user){

        Intent intent = new Intent(mMainActivity, ItemsMenu.class);
        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference().child("Users");

        if (user == null) {
            Log.e(TAG, "createNewUser() << Error user is null");
            return;
        }
        try {
            userRef.child(user.getUid()).setValue(new User(user.getEmail(), 0 ,null));
        }catch (Exception e){
            Log.e(TAG, e.getMessage());

        }
        Log.e(TAG, "createNewUser() <<");
        mMainActivity.startActivity(intent);
    }
    private void createNewUser() {

        Log.e(TAG, "createNewUser() >>");

        FirebaseUser fbUser = mAuth.getCurrentUser();
        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("Users");

        if (fbUser == null) {
            Log.e(TAG, "createNewUser() << Error user is null");
            return;
        }

        userRef.child(fbUser.getUid()).setValue(new User(fbUser.getEmail(),0,null));

        Log.e(TAG, "createNewUser() <<");
    }
}
