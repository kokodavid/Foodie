package com.koko.foodie.Activities.Favorite;

import com.koko.foodie.DataSource.IFavoriteDataSource;

import java.util.List;

import io.reactivex.Flowable;

public class FavoriteRespository implements IFavoriteDataSource {

    public IFavoriteDataSource iFavoriteDataSource;

    public FavoriteRespository(IFavoriteDataSource iFavoriteDataSource) {
        this.iFavoriteDataSource = iFavoriteDataSource;
    }

    private static FavoriteRespository instance;
    public static FavoriteRespository getInstance(IFavoriteDataSource favoriteDataSource)
    {
        if (instance == null)
            instance = new FavoriteRespository(favoriteDataSource);
        return instance;
    }


    @Override
    public Flowable<List<Favorite>> getFavorites() {
        return iFavoriteDataSource.getFavorites();
    }


    @Override
    public int isFavorite(int itemId) {
        return iFavoriteDataSource.isFavorite(itemId);
    }

    @Override
    public void insertFav(Favorite... favorites) {
        iFavoriteDataSource.insertFav(favorites);
    }

    @Override
    public void delete(Favorite favorite) {
        iFavoriteDataSource.delete(favorite);
    }
}
