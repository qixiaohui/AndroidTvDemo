package com.example.tqi.pojo;

/**
 * Created by TQi on 2/18/16.
 */
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Pojo {

    @SerializedName("boxOffice")
    @Expose
    private BoxOffice boxOffice;
    @SerializedName("inTheater")
    @Expose
    private InTheater inTheater;
    @SerializedName("opening")
    @Expose
    private Opening opening;
    @SerializedName("upcomming")
    @Expose
    private Upcomming upcomming;

    /**
     *
     * @return
     * The boxOffice
     */
    public BoxOffice getBoxOffice() {
        return boxOffice;
    }

    /**
     *
     * @param boxOffice
     * The boxOffice
     */
    public void setBoxOffice(BoxOffice boxOffice) {
        this.boxOffice = boxOffice;
    }

    /**
     *
     * @return
     * The inTheater
     */
    public InTheater getInTheater() {
        return inTheater;
    }

    /**
     *
     * @param inTheater
     * The inTheater
     */
    public void setInTheater(InTheater inTheater) {
        this.inTheater = inTheater;
    }

    /**
     *
     * @return
     * The opening
     */
    public Opening getOpening() {
        return opening;
    }

    /**
     *
     * @param opening
     * The opening
     */
    public void setOpening(Opening opening) {
        this.opening = opening;
    }

    /**
     *
     * @return
     * The opening
     */
    public Upcomming getUpcomming() {
        return upcomming;
    }

    /**
     *
     * @param opening
     * The opening
     */
    public void setUpcomming(Upcomming upcomming) {
        this.upcomming = upcomming;
    }

}
