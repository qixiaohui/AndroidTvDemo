package com.example.tqi.pojo;

/**
 * Created by TQi on 2/18/16.
 */
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class AlternateIds {

    @SerializedName("imdb")
    @Expose
    private String imdb;

    /**
     *
     * @return
     * The imdb
     */
    public String getImdb() {
        return imdb;
    }

    /**
     *
     * @param imdb
     * The imdb
     */
    public void setImdb(String imdb) {
        this.imdb = imdb;
    }

}
