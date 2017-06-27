package FIlmTube.TheMovieDB;

// Esta clase se utiliza para obtener toda la informacion relevante de una pelicula

public class MovieRest {

    private Credits credits;
    private MovieInfo movieInfo;

    public Credits getCredits() {
        return credits;
    }

    public void setCredits(Credits credits) {
        this.credits = credits;
    }

    public MovieInfo getMovieInfo() {
        return movieInfo;
    }

    public void setMovieInfo(MovieInfo movieInfo) {
        this.movieInfo = movieInfo;
    }
}
