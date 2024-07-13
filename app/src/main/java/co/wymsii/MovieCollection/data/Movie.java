package co.wymsii.MovieCollection.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import java.util.Date;

@Entity(tableName = "movies")
public class Movie {
    private String title;
    private Date premiered;
    private Date added;
    @PrimaryKey(autoGenerate = true)
    private long id;
    private String genre;
    private String description;
    private String mediaType;
    private String imageURL;
    private String showFormat;

    public Movie(long id, String title, String description, Long premiereTimestamp, Long addedTimestamp, String genre, String mediaType, String imageURL, String showFormat){
        this.id= id;
        this.title=title;
        this.description = description;
        this.premiered = new Date(premiereTimestamp);
        this.added = new Date(addedTimestamp);
        this.genre = genre;
        this.mediaType = mediaType;
        this.setImageURL(imageURL);
        this.setShowFormat(showFormat);
    }


    public Movie(){
        title = "";
        //id = 0L;
        genre = "None";
        description = "";
        mediaType = "None";
        premiered = new Date();
        added = new Date();
        imageURL = "";
        showFormat = "";
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getPremiered() {
        return premiered;
    }

    public void setPremiered(Date premiered) {
        this.premiered = premiered;
    }

    public Date getAdded() {
        return added;
    }

    public void setAdded(Date added) {
        this.added = added;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getShowFormat() {
        return showFormat;
    }

    public void setShowFormat(String showFormat) {
        this.showFormat = showFormat;
    }
}
