package FIlmTube;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;

@Controller
public class FilmtubeController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PeliculaRepository peliculaRepository;

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
        return new ModelAndView("home");
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping("/admin")
    public ModelAndView admin(){
        return new ModelAndView("admin");
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping("/addUser")
    public ModelAndView addUser(){ return new ModelAndView("addUser");}

    @Secured("ROLE_ADMIN")
    @RequestMapping("/users")
    public ModelAndView userForm(@RequestParam String nickname, @RequestParam String password, @RequestParam String email){
        GrantedAuthority[] userRoles = {
                new SimpleGrantedAuthority("ROLE_USER")
        };

        User user = new User(nickname,password,email, Arrays.asList(userRoles));
        userRepository.save(user);
        return new ModelAndView("users").addObject("newUser",user);
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping("/addPelicula")
    public ModelAndView addPelicula(){ return new ModelAndView("addPelicula");}

    @Secured("ROLE_ADMIN")
    @RequestMapping("/peliculas")
    public ModelAndView movieForm(@RequestParam String titulo, @RequestParam String url){
        Pelicula pelicula = new Pelicula(titulo,url);
        peliculaRepository.save(pelicula);
        return new ModelAndView("peliculas").addObject("newPelicula",pelicula);
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ModelAndView allUsers(){
        GrantedAuthority[] userRoles = {
                new SimpleGrantedAuthority("ROLE_USER")
        };
       //Iterable<User> usuarios = userRepository.findAll();
        User u1 = new User("paco","paco","paco@email.com",Arrays.asList(userRoles));
        User u2 = new User("pepa","pepa","pepa@email.com",Arrays.asList(userRoles));
        User u3 = new User("apu","apu","apu@email.com",Arrays.asList(userRoles));
        List<User> usuarios = Arrays.asList(u1,u2,u3);
       return new ModelAndView("users").addObject("usuarios",usuarios);
    }
}



