package com.koko.foodie.DataSource;

import com.koko.foodie.Activities.Favorite.Favorite;

import java.util.List;

import io.reactivex.Flowable;

public interface IFavoriteDataSource {
    Flowable<List<Favorite>> getFavorites();

    int isFavorite (int itemId);

    void insertFav(Favorite...favorites);

    void delete(Favorite favorite);
}
