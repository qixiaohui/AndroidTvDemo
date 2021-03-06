package com.example.tqi.androidtvdemo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v17.leanback.app.DetailsFragment;
import android.support.v17.leanback.widget.Action;
import android.support.v17.leanback.widget.ArrayObjectAdapter;
import android.support.v17.leanback.widget.ClassPresenterSelector;
import android.support.v17.leanback.widget.DetailsOverviewRow;
import android.support.v17.leanback.widget.FullWidthDetailsOverviewRowPresenter;
import android.support.v17.leanback.widget.HeaderItem;
import android.support.v17.leanback.widget.ListRow;
import android.support.v17.leanback.widget.ListRowPresenter;
import android.support.v17.leanback.widget.OnActionClickedListener;
import android.support.v17.leanback.widget.OnItemViewClickedListener;
import android.support.v17.leanback.widget.Presenter;
import android.support.v17.leanback.widget.Row;
import android.support.v17.leanback.widget.RowPresenter;
import android.support.v17.leanback.widget.SparseArrayObjectAdapter;
import android.util.Log;

import com.example.tqi.androidtvdemo.dao.MoviesGateway;
import com.example.tqi.androidtvdemo.dao.RestClient;
import com.example.tqi.pojo.Movie;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.lang.reflect.Type;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by TQi on 2/19/16.
 */
public class DetailFragment extends DetailsFragment implements OnItemViewClickedListener, OnActionClickedListener{
    public static final String movieExtra = "movie_extra";
    public static final String categoryExtra = "category_extra";
    public static final long ACTION_WATCH = 1;
    private Movie movie;
    private ArrayObjectAdapter adapter;
    private String categroy;
    private DetailsOverviewRow mRow;
    private MoviesGateway moviesGateway;
    private Target target = new Target() {
        @Override
        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
            mRow.setImageBitmap(getActivity(), bitmap);
        }

        @Override
        public void onBitmapFailed(Drawable errorDrawable) {

        }

        @Override
        public void onPrepareLoad(Drawable placeHolderDrawable) {

        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        movie = new Gson().fromJson(getActivity().getIntent().getStringExtra(movieExtra), Movie.class);
        categroy = getActivity().getIntent().getStringExtra(categoryExtra);
        mRow = new DetailsOverviewRow(movie);
        initAction();
        ClassPresenterSelector presenterSelector = createPresenterSelector();
        adapter = new ArrayObjectAdapter(presenterSelector);
        adapter.add(mRow);
        loadRelatedMedia(adapter);
        setAdapter(adapter);
        Picasso.with(getActivity()).load(movie.getPosters().getDetailed()).resize(300,400).into(target);
        setOnItemViewClickedListener(this);
        moviesGateway = RestClient.getGateway();
        Call<List<Movie>> call = moviesGateway.getRelated(categroy);
        call.enqueue(new Callback<List<Movie>>() {
            @Override
            public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
                loadRows(response.body());
            }

            @Override
            public void onFailure(Call<List<Movie>> call, Throwable t) {
                Log.e("Error", t.toString());
            }
        });
    }

    private void loadRelatedMedia(ArrayObjectAdapter adapter){

    }

    private ClassPresenterSelector createPresenterSelector(){
        ClassPresenterSelector selector = new ClassPresenterSelector();
        FullWidthDetailsOverviewRowPresenter presenter = new FullWidthDetailsOverviewRowPresenter(new DetailDescriptionPresenter());
        presenter.setOnActionClickedListener(this);
        selector.addClassPresenter(DetailsOverviewRow.class, presenter);
        selector.addClassPresenter(ListRow.class, new ListRowPresenter());
        return selector;
    }

    private void initAction(){
        mRow.setActionsAdapter(new SparseArrayObjectAdapter() {
            @Override
            public int size() {
                return 3;
            }

            @Override
            public Object get(int position) {
                if (position == 0) {
                    return new Action(ACTION_WATCH, "Watch", "");
                } else if (position == 1) {
                    return new Action(42, "Rent", "");
                } else if (position == 2) {
                    return new Action(42, "Preview", "");
                } else {
                    return null;
                }
            }
        });
    }

    @Override
    public void onActionClicked(Action action) {

    }

    @Override
    public void onItemClicked(Presenter.ViewHolder itemViewHolder, Object item, RowPresenter.ViewHolder rowViewHolder, Row row) {
        if(item instanceof Movie){
            Movie movie = (Movie) item;
            Intent intent = new Intent(getActivity(), DetailActivity.class);
            intent.putExtra(movieExtra, new Gson().toJson(movie));
            intent.putExtra(categoryExtra, categroy);
            startActivity(intent);
        }
    }

    private void loadRows(List<Movie> movies){
        if(movies == null){
            return;
        }
        ArrayObjectAdapter arrayAdapter = new ArrayObjectAdapter(new CardPresenter());
        for(Movie movie : movies){
            arrayAdapter.add(movie);
        }
        HeaderItem headerItem = new HeaderItem(0, "Related");
        adapter.add(new ListRow(headerItem, arrayAdapter));

    }
}
