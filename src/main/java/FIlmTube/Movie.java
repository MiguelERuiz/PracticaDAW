package FIlmTube;

import javax.persistence.*;

@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String url;
    private String title;
    @Column(length=1000000)
    private String description;
    private String year;
    private String director;
    @Column(length=1000000)
    private String actors;
    private String poster;
    private String rating;
    private Long imdbId;

    public Movie(){}

    public Movie(String title, String url, Long imdbId){
        this.title = title;
        this.url = url;
        this.imdbId = imdbId;
    }

    public Movie(String url, String title, String description, String year, String director, String actors, String poster, String rating, Long imdbId) {
        this.url = url;
        this.title = title;
        this.description = description;
        this.year = year;
        this.director = director;
        this.actors = actors;
        this.poster = poster;
        this.rating = rating;
        this.imdbId = imdbId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title= title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public Long getImdbId() {
        return imdbId;
    }

    public void setImdb_id(Long imdbId) {
        this.imdbId = imdbId;
    }
}
