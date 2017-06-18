package FIlmTube;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * Created by Nacho on 18/06/2017.
 */

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    GrantedAuthority[] userRoles = {
            new SimpleGrantedAuthority("ROLE_USER")
    };

    public Iterable<User> listarUsuarios(){
        return userRepository.findAll();
    }

    public void añadirUsuario(String nickname, String password, String email){
        User u = new User(nickname,password,email, Arrays.asList(userRoles));
        userRepository.save(u);
    }

    public void actualizarUsuario(Long id, String nickname, String password, String email){
        User u = userRepository.findOne(id);

        if(nickname.equals("")){
            nickname = u.getUser();
        }
        if(password.equals("")){
            password = u.getPassword();
        }
        if(email.equals("")){
            email = u.getEmail();
        }

        u.setUser(nickname);
        u.setPassword(password);
        u.setEmail(email);

        userRepository.save(u);
    }

    public void borrarUsuario(Long id){
        userRepository.delete(id);
    }
}
