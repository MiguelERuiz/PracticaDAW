package FIlmTube.TheMovieDB;

import java.util.Iterator;
import java.util.List;

// Esta clase se utiliza para parsear la informacion de los creditos de la pelicula que te devuelve la API

public class Credits {

    private Long id;
    private List<Cast> cast;
    private List<Crew> crew;

    public List<Cast> getCast() {
        return cast;
    }

    public void setCast(List<Cast> cast) {
        this.cast = cast;
    }

    public List<Crew> getCrew() {
        return crew;
    }

    public void setCrew(List<Crew> crew) {
        this.crew = crew;
    }

    public String castToString(){
        Iterator<Cast> it = this.cast.iterator();
        String res="";
//        int i = 0;
        while(it.hasNext()){
            res = res + it.next().getName() + ", ";
//            i++;
        }
        res = res.substring(0, res.lastIndexOf(", ")) + ".";
        return res;
    }

    public String getDirector(){
        Iterator<Crew> it = this.crew.iterator();
        boolean encontrado = false;
        Crew c;
        String name = "";
        while(it.hasNext() && (c=it.next())!=null && !encontrado){
            if(c.getJob().equals("Director")){
                name = c.getName();
                encontrado = true;
            }
        }
        return name;
    }
}