package FIlmTube;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Nacho on 28/05/2017.
 */
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserRepository userRepository;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException{
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();

        User user = userRepository.findByUser(username);

        if(user == null){
            throw new BadCredentialsException("User No Encontrado");
        }

        if(!new BCryptPasswordEncoder().matches(password,user.getPassword())){
            throw new BadCredentialsException("Contraseña Incorrecta");
        }

        List<GrantedAuthority> roles = user.getRoles();

        return new UsernamePasswordAuthenticationToken(username,password,roles);
    }

    @Override
    public boolean supports (Class<?> arg0){
        return true;
    }
}
