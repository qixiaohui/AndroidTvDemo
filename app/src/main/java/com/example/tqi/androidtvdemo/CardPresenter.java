package com.example.tqi.androidtvdemo;

import android.content.Context;
import android.support.v17.leanback.widget.ImageCardView;
import android.support.v17.leanback.widget.Presenter;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.example.tqi.pojo.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by TQi on 2/18/16.
 */
public class CardPresenter extends Presenter {
    static class ViewHolder extends Presenter.ViewHolder {
        private ImageCardView imageCardView;
        public ViewHolder(View view){
            super(view);
            imageCardView = (ImageCardView) view;
        }

        public ImageCardView getCardView(){
            return this.imageCardView;
        }

        public void updateCardView(Context context, String link){
            Picasso.with(context).load(link)
                    .resize(210, 210).centerCrop()
                    .into(imageCardView.getMainImageView());
        }
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent) {
        ImageCardView mCardView = new ImageCardView(parent.getContext());
        mCardView.setFocusable(true);
        return new ViewHolder(mCardView);
    }

    @Override
    public void onBindViewHolder(Presenter.ViewHolder viewHolder, Object item) {
        Movie movie = (Movie) item;
        String url = movie.getPosters().getDetailed();
        if(url.lastIndexOf("/movie") >0 ) {
            url = "http://content6.flixster.com" + url.substring(url.lastIndexOf("/movie"), url.length());
            movie.getPosters().setDetailed(url);
        }
        if(movie.getTitle().equals("Zoolander 2")){
            Log.i("das", url);
        }
        if(!TextUtils.isEmpty(movie.getPosters().getDetailed())){
            ((ViewHolder)viewHolder).imageCardView.setTitleText(movie.getTitle());
            ((ViewHolder)viewHolder).imageCardView.setMainImageDimensions(210, 210);
            ((ViewHolder)viewHolder).updateCardView(((ViewHolder)viewHolder).getCardView().getContext(), url);
        }
    }

    @Override
    public void onUnbindViewHolder(Presenter.ViewHolder viewHolder) {

    }
}
