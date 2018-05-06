package com.dev.android.mta.androiduserauthentication.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.dev.android.mta.androiduserauthentication.R;
import Model.Item;
import com.dev.android.mta.androiduserauthentication.implementaion.ItemAdapter;
import com.facebook.AccessToken;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class ItemsMenu extends AppCompatActivity {
    private final String TAG = "MusicPlayerMain";
    private DatabaseReference mAllItemsRef;
    private DatabaseReference mMyUserRef;
    private FirebaseUser mFbUser;
    private RecyclerView mRecyclerView;
    private ItemAdapter mItemAdapter;
    private List<Item> mItemList;
    private Toolbar mToolBar;
    private GoogleSignInClient mAccount ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items_menu);

        mItemList = new ArrayList<>();
        mRecyclerView = (RecyclerView)findViewById(R.id.recycleView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mFbUser =  FirebaseAuth.getInstance().getCurrentUser();
        //allItemsRef = FirebaseDatabase.getInstance().getReference("Songs");
        mToolBar = (Toolbar)findViewById(R.id.main_page_toolbar);
        setSupportActionBar(mToolBar);
        //getSupportActionBar().setTitle();
        mItemAdapter = new ItemAdapter(this, mItemList);
        mRecyclerView.setAdapter(mItemAdapter);
        getAllSongsUsingChildListenrs();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
         super.onCreateOptionsMenu(menu);
         getMenuInflater().inflate(R.menu.main_menu,menu);
    //     findViewById(R.id.sign_out_appMenu).setText()
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
         super.onOptionsItemSelected(item);
         if(item.getItemId()== R.id.sign_out_appMenu)
         {
             signOut();
         }
         else if(item.getItemId() == R.id.profile)
         {
             Intent intent = new Intent(ItemsMenu.this, AccountInfoActivity.class);
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
        Intent intent = new Intent(ItemsMenu.this,MainActivity.class);
        startActivity(intent);
    }

    public void disconnectFromFacebook() {

        if (AccessToken.getCurrentAccessToken() == null) {
            return; // already logged out
        }
        LoginManager.getInstance().logOut();
    }

    private void getAllSongsUsingChildListenrs() {

        mAllItemsRef = FirebaseDatabase.getInstance().getReference("Songs");

        mAllItemsRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot snapshot, String previousChildName) {

                Log.e(TAG, "onChildAdded(Item) >> " + snapshot.getKey());
                Item item=null;
                try {
                    item = snapshot.getValue(Item.class);
                }catch (Exception e){
                    e.getMessage();
                }
                mItemList.add(item);
                mRecyclerView.getAdapter().notifyDataSetChanged();

                Log.e(TAG, "onChildAdded(Songs) <<");

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

                Log.e(TAG, "onChildRemoved(Songs) >> " + dataSnapshot.getKey());

                Item itemToRemove =dataSnapshot.getValue(Item.class);
                String key = dataSnapshot.getKey();

                for (int i = 0 ; i < mItemList.size() ; i++) {
                    if (mItemList.get(i).equals(itemToRemove)) {
                        mItemList.remove(i);
                        mRecyclerView.getAdapter().notifyDataSetChanged();
                        Log.e(TAG, "onChildRemoved(Songs) >> i="+i);
                        break;
                    }
                }

                Log.e(TAG, "onChildRemoved(Songs) <<");
            }


            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
            private void updateSongsList(DataSnapshot snapshot) {


        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
            Item item = dataSnapshot.getValue(Item.class);
            Log.e(TAG, "updateItemsList() >> adding Item: " + item.getName());
            String key = dataSnapshot.getKey();
            mItemList.add(item);
        }
        mRecyclerView.getAdapter().notifyDataSetChanged();
    }
}
