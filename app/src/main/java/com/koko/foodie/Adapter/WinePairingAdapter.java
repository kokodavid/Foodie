package com.koko.foodie.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.koko.foodie.Models.Wine;
import com.koko.foodie.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WinePairingAdapter extends RecyclerView.Adapter<WinePairingAdapter.RecyclerViewHolder> {

    private List<Wine.ProductMatch> wines;
    private List<Wine> winess;
    private Context context;

    public WinePairingAdapter(List<Wine.ProductMatch> wines, Context context) {
        this.wines = wines;
        this.winess = winess;
        this.context = context;
    }



    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.test,
                parent, false);
        return new WinePairingAdapter.RecyclerViewHolder(view);    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        String wineImage = wines.get(position).getImageUrl();
        Picasso.get().load(wineImage).placeholder(R.drawable.ic_circle).into(holder.wineImage);

        String WineName = wines.get(position).getTitle();
        holder.WineTitle.setText(WineName);

        String WineDescription = wines.get(position).getDescription();
        holder.WineDescription.setText(WineDescription);

        String WinePrice = wines.get(position).getPrice();
        holder.WinePrice.setText(WinePrice);


//        String pairedWines = winess.get(position).getPairedWines();
//        holder.pairedWines.setText((CharSequence) pairedWines);

        String pairingText = winess.get(position).getPairingText();
        holder.pairedWines.setText(pairingText);

    }


    @Override
    public int getItemCount() {
        return wines.size();
    }

    static class RecyclerViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.WineImage)
        ImageView wineImage;
        @BindView(R.id.WineTitle)
        TextView WineTitle;
        @BindView(R.id.WineDescription)
        TextView WineDescription;
        @BindView(R.id.WinePrice)
        TextView WinePrice;


        @BindView(R.id.WineText)
        TextView pairedWines;


        RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }
}
