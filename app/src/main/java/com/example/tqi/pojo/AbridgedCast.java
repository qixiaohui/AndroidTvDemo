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
public class AbridgedCast {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("characters")
    @Expose
    private List<String> characters = new ArrayList<String>();

    /**
     *
     * @return
     * The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     * The id
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The characters
     */
    public List<String> getCharacters() {
        return characters;
    }

    /**
     *
     * @param characters
     * The characters
     */
    public void setCharacters(List<String> characters) {
        this.characters = characters;
    }

}
