package FIlmTube;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Nacho on 28/05/2017.
 */
@Entity
public class Pelicula {

    @Id
    private String url;
    private String titulo;

    public Pelicula(){}

    public Pelicula(String titulo, String url){
        this.titulo = titulo;
        this.url = url;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
