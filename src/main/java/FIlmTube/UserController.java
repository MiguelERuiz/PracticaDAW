package FIlmTube;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Secured("ROLE_ADMIN")
    @RequestMapping("/admin")
    public ModelAndView admin(){
        return new ModelAndView("admin");
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping("/users")
    public ModelAndView showUsers(){
        Iterable<User> users = userService.listarUsuarios();
        return new ModelAndView("users").addObject("usuarios",users);
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping("/addUser")
    public ModelAndView addUser(){
        return new ModelAndView("/addUser");
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value="/addedUser", method={RequestMethod.GET,RequestMethod.POST})
    public String addedUser(@RequestParam String nickname, @RequestParam String password, @RequestParam String email){
        userService.añadirUsuario(nickname,password,email);
        return "redirect:/users";
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping("/editUser")
    public ModelAndView editUser(@RequestParam Long id){
        return new ModelAndView("/editUser").addObject("id",id);
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value="/editedUser", method={RequestMethod.GET,RequestMethod.POST})
    public String editedUser(@RequestParam Long id,@RequestParam String nickname, @RequestParam String password, @RequestParam String email){
        userService.actualizarUsuario(id, nickname, password, email);
        return "redirect:/users";
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value="/deleteUser", method={RequestMethod.GET,RequestMethod.POST})
    public String remove(@RequestParam Long id){
        if(id != 1) {
            userService.borrarUsuario(id);
        }
        return "redirect:/users";
    }
}



