package com.dev.android.mta.androiduserauthentication.Activities;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dev.android.mta.androiduserauthentication.R;
import com.facebook.AccessToken;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AccountInfoActivity extends AppCompatActivity {
    private TextView mUsername;
    private TextView mEmail;
    private ImageView mProfilePic;
    private GoogleSignInClient mAccount ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_account_info);
       // account.getSignInIntent();
        mUsername = findViewById(R.id.textViewUsername);
        mEmail = findViewById(R.id.textEmail);
        mProfilePic =  findViewById(R.id.imageView);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user!=null) {
            if(user.isAnonymous()) {
                mUsername.setText("Anonnymouse User");
            }
            else {
                mEmail.setText("Email: " + user.getEmail());
                mUsername.setText("Name : " + user.getDisplayName());
                if (user.getPhotoUrl() != null) {

                    Glide.with(this)
                            .load(user.getPhotoUrl().toString())
                            .into(mProfilePic);
                }
            }
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.profile_menu,menu);
        //     findViewById(R.id.sign_out_appMenu).setText()
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        if(item.getItemId()== R.id.log_out_profile)
        {
            signOut();
        }
        else if(item.getItemId() == R.id.item_menu_profile)
        {
            Intent intent = new Intent(AccountInfoActivity.this, ItemsMenu.class);
            startActivity(intent);
        }
        return true;
    }

    private void signOut() {
        FirebaseAuth.getInstance().signOut();
        if(mAccount!=null) {
            mAccount.signOut();
        }
        disconnectFromFacebook();
        Intent intent = new Intent(AccountInfoActivity.this,MainActivity.class);
        startActivity(intent);
    }

    public void disconnectFromFacebook() {

        if (AccessToken.getCurrentAccessToken() == null) {
            return; // already logged out
        }
        LoginManager.getInstance().logOut();
    }

}
