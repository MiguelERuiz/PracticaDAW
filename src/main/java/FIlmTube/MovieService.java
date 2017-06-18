package FIlmTube;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Nacho on 18/06/2017.
 */
@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    public Movie getPelicula(Long id){
        Movie m = movieRepository.findOne(id);
        return m;
    }

    public Iterable<Movie> listarPeliculas(){
        return movieRepository.findAll();
    }

    public void añadirPelicula(String title, String url){
        Movie m = new Movie(title,url);
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

}
