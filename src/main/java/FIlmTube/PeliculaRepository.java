package FIlmTube;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by Nacho on 13/06/2017.
 */
public interface PeliculaRepository extends CrudRepository<Pelicula,Long> {

        Pelicula findByTitulo(String titulo);
}
