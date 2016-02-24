package com.example.tqi.androidtvdemo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v17.leanback.app.BackgroundManager;
import android.support.v17.leanback.app.BrowseFragment;
import android.support.v17.leanback.widget.ArrayObjectAdapter;
import android.support.v17.leanback.widget.HeaderItem;
import android.support.v17.leanback.widget.ListRow;
import android.support.v17.leanback.widget.ListRowPresenter;
import android.support.v17.leanback.widget.OnItemViewClickedListener;
import android.support.v17.leanback.widget.OnItemViewSelectedListener;
import android.support.v17.leanback.widget.Presenter;
import android.support.v17.leanback.widget.Row;
import android.support.v17.leanback.widget.RowPresenter;
import android.util.DisplayMetrics;
import android.util.Log;

import com.example.tqi.androidtvdemo.dao.MoviesGateway;
import com.example.tqi.androidtvdemo.dao.RestClient;
import com.example.tqi.pojo.Movie;
import com.example.tqi.pojo.Pojo;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainFragment extends BrowseFragment implements OnItemViewClickedListener, OnItemViewSelectedListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private BackgroundManager mBackgroundManager;
    private Drawable mDefaultBackground;
    private DisplayMetrics mMetrics;
    private ArrayObjectAdapter objAdapter;
    private ArrayObjectAdapter adapter;
    private CardPresenter presenter;
    private MoviesGateway moviesGateway;
    private Target target = new Target() {
        @Override
        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
            mBackgroundManager.setBitmap(bitmap);
        }

        @Override
        public void onBitmapFailed(Drawable errorDrawable) {

        }

        @Override
        public void onPrepareLoad(Drawable placeHolderDrawable) {

        }
    };

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setBgManager();
        loadData();
        setOnItemViewClickedListener(this);
        setOnItemViewSelectedListener(this);
        moviesGateway = RestClient.getGateway();
        Call<Pojo> call = moviesGateway.getMovies();
        call.enqueue(new Callback<Pojo>() {
            @Override
            public void onResponse(Call<Pojo> call, Response<Pojo> response) {
                loadRows(response.body());
            }

            @Override
            public void onFailure(Call<Pojo> call, Throwable t) {
                Log.e("can't get movie", t.toString());
            }
        });
    }

    private void loadRows(Pojo pojo){
        adapter = new ArrayObjectAdapter( new ListRowPresenter() );

        presenter = new CardPresenter();
        List<String> categorys = getCategory();
//        Set<Method> getters = ReflectionUtils.getAllMethods(pojo.getClass(),
//                ReflectionUtils.withModifier(Modifier.PUBLIC), ReflectionUtils.withPrefix("get"));
//        for(Method get : getters){
//
//            ArrayObjectAdapter arrayAdapter = new ArrayObjectAdapter(presenter);
//            for(String category : categorys){
//                if(get.getName().toString().toLowerCase().indexOf(category) >=0 ) {
//                    try {
//                        Pojo data = new Pojo();
//                        for (Movie m : get.invoke(new Pojo(), null)) {
//
//                        }
//                    } catch (java.lang.InstantiationException e) {
//                        e.printStackTrace();
//                    } catch (IllegalAccessException e) {
//                        e.printStackTrace();
//                    } catch (InvocationTargetException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }

        for(String category : categorys){
            if(category.equals("boxoffice")) {
                addRow(category, pojo.getBoxOffice().getMovies());
            }else if(category.equals("intheater")){
                addRow(category, pojo.getInTheater().getMovies());
            }else if(category.equals("opening")){
                addRow(category, pojo.getOpening().getMovies());
            }else if(category.equals("upcomming")){
                addRow(category, pojo.getUpcomming().getMovies());
            }
        }
    }

    private void addRow(String category, List<Movie> movies){
        ArrayObjectAdapter arrayAdapter = new ArrayObjectAdapter(presenter);

        for (Movie m : movies) {
            arrayAdapter.add(m);
        }

        if (arrayAdapter.size() > 0) {
            HeaderItem headerItem = new HeaderItem(arrayAdapter.size() - 1, category);
            adapter.add(new ListRow(headerItem, arrayAdapter));
        }

        setAdapter(adapter);
    }

    private ArrayList<String> getCategory(){
        ArrayList<String> list = new ArrayList<String>();
        for(Field n : Pojo.class.getDeclaredFields()){
            String className = n.toString().toLowerCase();
            list.add(className.substring(className.lastIndexOf(".")+1, className.length()));
        }
        return list;
    }

    private void setBgManager(){
        mBackgroundManager = BackgroundManager.getInstance(getActivity());
        mBackgroundManager.attach(getActivity().getWindow());
        mDefaultBackground = getResources().getDrawable(R.drawable.default_background);
        mBackgroundManager.setDrawable(mDefaultBackground);
        mMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(mMetrics);
    }

    private void loadData(){
        setTitle("My media player");
        setHeadersState(HEADERS_ENABLED);
        setHeadersTransitionOnBackEnabled(true);
    }

    @Override
    public void onItemClicked(Presenter.ViewHolder itemViewHolder, Object item, RowPresenter.ViewHolder rowViewHolder, Row row) {
        if(item instanceof Movie){
            Movie movie = (Movie) item;
            Intent intent = new Intent(getActivity(), DetailActivity.class);
            intent.putExtra(DetailFragment.movieExtra, new Gson().toJson(movie));
            intent.putExtra(DetailFragment.categoryExtra, row.getHeaderItem().getName());
            startActivity(intent);
        }
    }

    @Override
    public void onItemSelected(Presenter.ViewHolder itemViewHolder, Object item, RowPresenter.ViewHolder rowViewHolder, Row row) {
        if(item instanceof Movie) {
            Movie movie = (Movie) item;
            Picasso.with(getActivity()).load(movie.getPosters().getOriginal()).into(target);
        }
    }
}
