package com.dev.android.mta.androiduserauthentication.implementaion;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dev.android.mta.androiduserauthentication.Activities.CarDetailsActivity;
import com.dev.android.mta.androiduserauthentication.R;

import java.util.List;
import java.util.Random;

import com.dev.android.mta.androiduserauthentication.Model.Item;

import static com.dev.android.mta.androiduserauthentication.constans.ActivityExtrasConstans.CAR_ITEM_OBJECT;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder>  {
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
        ItemViewHolder holder = new ItemViewHolder(view,mCtx);
        return holder;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        Item item = mItemList.get(position);
        Random rand = new Random();
        Item car = mItemList.get(position);
        holder.carMake.setText(item.getCarMake());
        holder.carYear.setText(item.getCarModelYear());
        holder.carModel.setText(item.getCarModel());
        holder.carColor.setText(item.getCarColor());
        holder.carPrice.setText(item.getPrice());
        holder.rating.setRating(rand.nextInt(5));
        holder.setSelectedCar(car);
        Glide.with(mCtx)
                .load(item.getCarImage().toString())
                .into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }



    class ItemViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView imageView;
        CardView carCardView;
        Context ctx;
        TextView carMake,carColor,carModel,carYear , carPrice;
        RatingBar rating;
        Item selectedCar;

        public ItemViewHolder(View itemView, Context ctx) {
            super(itemView);
            itemView.setOnClickListener(this);
            carCardView = itemView.findViewById(R.id.card_view_car);
            imageView = itemView.findViewById(R.id.car_thumb_image);
            carMake = itemView.findViewById(R.id.car_make);
            carColor = itemView.findViewById(R.id.car_color);
            carModel = itemView.findViewById(R.id.car_model);
            carYear = itemView.findViewById(R.id.car_year);
            carPrice = itemView.findViewById(R.id.car_price);
            rating = itemView.findViewById(R.id.car_rating);
            this.ctx =ctx;
            carCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    //Log.e(TAG, "CardView.onClick() >> name=" + selectedSong.getName());

                    Context context = view.getContext();
                    Intent intent = new Intent(context, CarDetailsActivity.class);
                    intent.putExtra(CAR_ITEM_OBJECT, selectedCar);
                    context.startActivity(intent);
                }
            });
        }

        @Override
        public void onClick(View view) {

        }

        public void setSelectedCar(Item selectedCar) {
            this.selectedCar = selectedCar;
        }
    }
}
