package com.example.tqi.pojo;

/**
 * Created by TQi on 2/18/16.
 */
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class InTheater {

    @SerializedName("movies")
    @Expose
    private List<Movie> movies = new ArrayList<Movie>();

    /**
     *
     * @return
     * The movies
     */
    public List<Movie> getMovies() {
        return movies;
    }

    /**
     *
     * @param movies
     * The movies
     */
    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

}
