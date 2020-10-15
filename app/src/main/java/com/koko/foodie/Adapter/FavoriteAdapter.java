package com.koko.foodie.Adapter;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.koko.foodie.Activities.Favorite.Favorite;
import com.koko.foodie.Activities.Favorite.FavoritesActivity;
import com.koko.foodie.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>{

    Context context;
    List<Favorite> favoriteList;
    private static FavoriteAdapter.ClickListener clickListener;


    public FavoriteAdapter(Context context, List<Favorite> favoritesList) {
        this.context = context;
        this.favoriteList = favoritesList;
    }

    @NonNull
    @Override
    public FavoriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.favorite_item,parent, false);
        return new FavoriteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteViewHolder holder, int position) {
        Picasso.get().load(favoriteList.get(position).image).into(holder.favImage);
        holder.favname.setText(favoriteList.get(position).name);

    }

    @Override
    public int getItemCount() {
        return favoriteList.size();
    }

    public class FavoriteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

//        public RelativeLayout view_foreground;
//        public RelativeLayout view_background;

        @BindView(R.id.mealThumb) ImageView favImage;
        @BindView(R.id.mealName) TextView favname;
      public   @BindView(R.id.view_foreground) RelativeLayout view_foreground ;
        public  @BindView(R.id.view_background) RelativeLayout view_background ;




        public FavoriteViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);


        }

        @Override
        public void onClick(View v) {
            clickListener.onClick(v, getAdapterPosition());
        }
    }

    public void setOnItemClickListener(FavoriteAdapter.ClickListener clickListener) {
        FavoriteAdapter.clickListener = clickListener;
    }



    public void  removeItem(int position)
    {
        favoriteList.remove(position);
        notifyItemRemoved(position);
    }

    public void restoreItem(Favorite item , int position){
        favoriteList.add(position,item);
        notifyItemInserted(position);
    }

    public interface ClickListener {
        void onClick(View view, int position);
    }
}
