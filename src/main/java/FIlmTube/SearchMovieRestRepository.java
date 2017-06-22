package FIlmTube;

/**
 * Created by Nacho on 20/06/2017.
 */
import org.springframework.stereotype.Repository;

import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;


@Repository
public interface SearchMovieRestRepository {

    // Obtener una peli por nombre
    @GET("/search/movie")
    MovieRest getMovieByName(@Query("api_key") String api_key, @Query("query") String q);

    // Obtener una peli por id
    @GET("/movie/{id}")
    MovieRest_1 getMovieByImdbId(@Path("id") Long id, @Query("api_key") String api_key);

    // Obtener los créditos de una peli por id
    @GET("/movie/{id}/credits")
    Credits getCreditsByImdbId(@Path("id") Long id,@Query("api_key") String api_key);

}

