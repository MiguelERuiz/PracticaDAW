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

    public void añadirPelicula(String title, String url, Long imdbId){
        Movie m = new Movie(title,url,imdbId);
        movieRepository.save(m);
    }

    public void actualizarPelicula(Long id, String title, String url){
        Movie m = movieRepository.findOne(id);

        if(title.equals("")){
            title = m.getTitle();
        }
        if(url.equals("")){
            url = m.getUrl();
        }

        m.setTitle(title);
        m.setUrl(url);

        movieRepository.save(m);
    }
    public void borrarPelicula(Long id){
        movieRepository.delete(id);
    }

    public boolean camposVacios(Long id){
        Movie m = movieRepository.findOne(id);
        return (m.getActors() == null && m.getDescription() == null && m.getDirector() == null
                && m.getPoster() == null && m.getRating() == null && m.getYear() == null);
    }

    public Results getResults(String titulo) {

        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint("http://api.themoviedb.org/3/").build();
        SearchMovieRestRepository searchMovies = restAdapter.create(SearchMovieRestRepository.class);
        MovieRest requestMovie = searchMovies.getMovieByName(api_key, titulo);
        List<Results> results = requestMovie.getResults();
        System.out.println(results.toString());

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
        if(movie.getPoster()==null){
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
