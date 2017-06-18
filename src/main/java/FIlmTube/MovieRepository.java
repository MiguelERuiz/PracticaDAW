package FIlmTube;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by Nacho on 13/06/2017.
 */
public interface MovieRepository extends CrudRepository<Movie,Long> {

        Movie findByTitle(String title);
}
