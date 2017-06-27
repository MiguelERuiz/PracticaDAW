package FIlmTube.Repositories;

import FIlmTube.Movie;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Nacho on 13/06/2017.
 */
public interface MovieRepository extends CrudRepository<Movie,Long> {

        Movie findByTitle(String title);
        List<Movie> findByTitleContaining(String title);
}
