package com.dev.android.mta.androiduserauthentication.implementaion;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dev.android.mta.androiduserauthentication.R;

import java.util.List;
import java.util.Random;

import Model.Item;

/**
 * Created by shahar on 05/05/2018.
 */

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {
    private List<Item> mItemList;
    private Context mCtx;


    public ItemAdapter(Context mCtx,List<Item> mItemList) {
        this.mItemList = mItemList;
        this.mCtx = mCtx;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.item_list, null);
        ItemViewHolder holder = new ItemViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        Item item = mItemList.get(position);
        Random rand = new Random();

        holder.textViewName.setText(item.getLastName());
        holder.gender.setText(item.getGender());
        holder.rating.setRating(rand.nextInt(5));
        Glide.with(mCtx)
                .load(item.getImageUrl().toString())
                .into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }



    class ItemViewHolder extends  RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textViewName,gender;
        RatingBar rating;
        public ItemViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.song_thumb_image);
            textViewName = itemView.findViewById(R.id.song_name);
            gender = itemView.findViewById(R.id.song_genre);
            rating = itemView.findViewById(R.id.song_rating);

        }
    }
}
