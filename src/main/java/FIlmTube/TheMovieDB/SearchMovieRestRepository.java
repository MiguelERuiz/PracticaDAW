package FIlmTube.TheMovieDB;

import org.springframework.stereotype.Repository;

import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

@Repository
public interface SearchMovieRestRepository {

    // Obtener una peli por nombre
    @GET("/search/movie")
    Data getMovieByName(@Query("api_key") String api_key, @Query("query") String q);

    // Obtener una peli por id
    @GET("/movie/{id}")
    MovieInfo getMovieByImdbId(@Path("id") Long id, @Query("api_key") String api_key);

    // Obtener los créditos de una peli por id
    @GET("/movie/{id}/credits")
    Credits getCreditsByImdbId(@Path("id") Long id,@Query("api_key") String api_key);

}

