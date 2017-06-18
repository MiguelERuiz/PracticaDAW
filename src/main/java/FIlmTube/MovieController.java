package FIlmTube;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nacho on 18/06/2017.
 */

@Controller
public class MovieController {

    @Autowired
    private MovieService movieService;

    @RequestMapping("/")
    public ModelAndView index(){
        return new ModelAndView("index");
    }

    @RequestMapping("/login")
    public ModelAndView login(){
        return new ModelAndView("login");
    }

    @Secured({"ROLE_ADMIN","ROLE_USER"})
    @RequestMapping("/home")
    public ModelAndView home(){
        Iterable<Movie> movies = movieService.listarPeliculas();
        return new ModelAndView("home").addObject("movies",movies);
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value="/movies", method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView showMovies(){
        Iterable<Movie> movies = movieService.listarPeliculas();
        return new ModelAndView("/movies").addObject("movies",movies);
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping("/addMovie")
    public ModelAndView addMovie(){ return new ModelAndView("/addMovie");}


    @Secured("ROLE_ADMIN")
    @RequestMapping(value="/addedMovie",method={RequestMethod.GET,RequestMethod.POST})
    public String addedMovie(@RequestParam String title, @RequestParam String url){
        movieService.añadirPelicula(title,url);
        return "redirect:/movies";
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping("/viewMovie")
    public ModelAndView viewMovie(@RequestParam Long id){
       Movie m = movieService.getPelicula(id);
       return new ModelAndView("/viewMovie").addObject("movie",m);
    }


    @Secured("ROLE_ADMIN")
    @RequestMapping("/editMovie")
    public ModelAndView editMovie(@RequestParam Long id){
        return new ModelAndView("/editMovie").addObject("id",id);
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value="/editedMovie", method={RequestMethod.GET,RequestMethod.POST})
    public String editedMovie(@RequestParam Long id,@RequestParam String title, @RequestParam String url){
        movieService.actualizarPelicula(id, title, url);
        return "redirect:/movies";
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value="/deleteMovie", method={RequestMethod.GET,RequestMethod.POST})
    public String remove(@RequestParam Long id){
        if(id != 1) {
            movieService.borrarPelicula(id);
        }
        return "redirect:/movies";
    }
}
