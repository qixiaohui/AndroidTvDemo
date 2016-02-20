package com.example.tqi.androidtvdemo;

import android.support.v17.leanback.widget.AbstractDetailsDescriptionPresenter;

import com.example.tqi.pojo.Movie;

/**
 * Created by TQi on 2/19/16.
 */
public class DetailDescriptionPresenter extends AbstractDetailsDescriptionPresenter {
    @Override
    protected void onBindDescription(ViewHolder vh, Object item) {
        Movie movie = (Movie) item;
        if(movie != null){
            vh.getTitle().setText(movie.getTitle());
            vh.getSubtitle().setText(movie.getMpaaRating());
            vh.getBody().setText(movie.getSynopsis());
        }
    }
}
