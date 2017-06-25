package FIlmTube;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Arrays;

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

    public int añadirUsuario(String nickname, String password, String email){
        int resultado = 1;
        if(userRepository.findByUser(nickname)==null){
            if(userRepository.findUserByEmail(email)!=null){
                return 2;
            }
            User u = new User(nickname,password,email, Arrays.asList(userRoles));
            userRepository.save(u);
            resultado = 0;
        }
        return resultado;
    }

    public boolean actualizarUsuario(Long id, String nickname, String password, String email){
       boolean vacios = false;
        if(!(nickname.equals("") && password.equals("") && email.equals(""))) {
            User u = userRepository.findOne(id);

            if (nickname.equals("")) {
                nickname = u.getUser();
            }
            if (password.equals("")) {
                password = u.getPassword();
            }
            if (email.equals("")) {
                email = u.getEmail();
            }

            u.setUser(nickname);
            u.setPassword(password);
            u.setEmail(email);

            userRepository.save(u);
            vacios = true;
        }
        return vacios;
    }

    public void borrarUsuario(Long id){
        userRepository.delete(id);
    }
}
