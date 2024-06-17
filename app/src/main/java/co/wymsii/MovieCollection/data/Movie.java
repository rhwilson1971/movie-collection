package co.wymsii.MovieCollection.data;

import java.util.Date;

public class Movie {
    private String title;
    private Date premiered;
    private Date added;
    private Long id;
    private String genre;
    private String description;
    private String mediaType;

    public Movie(Long id, String title, String description, Long premeireTimestamp, Long addedTimestamp, String genre, String mediaType){
        this.id= id;
        this.title=title;
        this.description = description;
        this.premiered = new Date(premeireTimestamp);
        this.added = new Date(addedTimestamp);
        this.genre = genre;
        this.mediaType = mediaType;
    }


    public Movie(){
        title = "";
        id = 0L;
        genre = "None";
        description = "";
        mediaType = "None";
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
}
