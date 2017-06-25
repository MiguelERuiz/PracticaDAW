package FIlmTube;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit.RestAdapter;

import java.util.List;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    final static String api_key = "cd6bed2eae72dcb90e226bf4beba0da1";

    public Movie getPelicula(Long id){
        Movie m = movieRepository.findOne(id);
        return m;
    }

    public Iterable<Movie> listarPeliculas(){
        return movieRepository.findAll();
    }

    public boolean añadirPelicula(String title, String url, Long imdbId){
        boolean resultado = false;
        if (movieRepository.findByTitle(title) == null){
            Movie m = new Movie(title,url,imdbId);
            m.setPoster("../images/defaultImage.jpg");
            movieRepository.save(m);
            resultado = true;
        }
        return resultado;
    }

    public boolean actualizarPelicula(Long id, String title, String url, String description, String year, String director, String actors, String poster, String rating){
        boolean vacios = false;

        if(!(title.equals("") && url.equals("") && description.equals("") && year.equals("") && director.equals("") &&
                actors.equals("") && poster.equals("") && rating.equals(""))) {
            Movie m = movieRepository.findOne(id);

            if (title.equals("")) {
                title = m.getTitle();
            }
            if (url.equals("")) {
                url = m.getUrl();
            }
            if (description.equals("")) {
                description = m.getDescription();
            }
            if (year.equals("")) {
                year = m.getYear();
            }
            if (director.equals("")) {
                director = m.getDirector();
            }
            if (actors.equals("")) {
                actors = m.getActors();
            }
            if (poster.equals("")) {
                poster = m.getPoster();
            }
            if (rating.equals("")) {
                rating = m.getRating();
            }
            m.setTitle(title);
            m.setUrl(url);
            m.setDescription(description);
            m.setYear(year);
            m.setDirector(director);
            m.setActors(actors);
            m.setPoster(poster);
            m.setRating(rating);

            movieRepository.save(m);
            vacios = true;
        }
        return vacios;
    }
    public void borrarPelicula(Long id){
        movieRepository.delete(id);
    }

    public boolean camposVacios(Long id){
        Movie m = movieRepository.findOne(id);
        return (m.getActors() == null && m.getDescription() == null && m.getDirector() == null
                && m.getRating() == null && m.getYear() == null);
    }

    public Results getResults(String titulo) {

        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint("http://api.themoviedb.org/3/").build();
        SearchMovieRestRepository searchMovies = restAdapter.create(SearchMovieRestRepository.class);
        MovieRest requestMovie = searchMovies.getMovieByName(api_key, titulo);
        List<Results> results = requestMovie.getResults();

        return results.get(0);
    }

    public MovieRest_1 getMovieByImdbId(Long imdbId) {

        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint("https://api.themoviedb.org/3/").build();
        SearchMovieRestRepository searchMovieRestRepository = restAdapter.create(SearchMovieRestRepository.class);
        MovieRest_1 movie = searchMovieRestRepository.getMovieByImdbId(imdbId, api_key);
        movie.setPoster_path("http://image.tmdb.org/t/p/w342" + movie.getPoster_path());

        return movie;
    }

    public Credits getCreditsByImdbId(Long imdbId) {

        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint("https://api.themoviedb.org/3/").build();
        SearchMovieRestRepository searchMovieRestRepository = restAdapter.create(SearchMovieRestRepository.class);
        Credits credits = searchMovieRestRepository.getCreditsByImdbId(imdbId, api_key);

        return credits;
    }

    public TheMovieDB getMovieFromTMDB(Movie movie){
        Long imdbId = movie.getImdbId();
        TheMovieDB result = new TheMovieDB();
        Credits credits = getCreditsByImdbId(imdbId);
        MovieRest_1 movieRest_1 =getMovieByImdbId(imdbId);
        result.setCredits(credits);
        result.setMovieRest_1(movieRest_1);

        return  result;
    }

    public Movie completeInformation(Movie movie, TheMovieDB movieDB){
        if(movie.getDescription()==null){
            movie.setDescription(movieDB.getMovieRest_1().getOverview());
        }
        if(movie.getYear()==null){
            movie.setYear(movieDB.getMovieRest_1().getRelease_date());
        }
        if(movie.getDirector()==null){
            movie.setDirector(movieDB.getCredits().getDirector());
        }
        if(movie.getActors()==null){
            movie.setActors(movieDB.getCredits().castToString());
        }
        if(movie.getPoster().equals("../images/defaultImage.jpg")){
            movie.setPoster(movieDB.getMovieRest_1().getPoster_path());
        }
        if(movie.getRating()==null){
            movie.setRating(movieDB.getMovieRest_1().getVote_average());
        }
        movieRepository.save(movie);
        return movie;
    }

    public Iterable<Movie> getMoviesByTitle(String title){
        return movieRepository.findByTitleContaining(title);
    }
}
