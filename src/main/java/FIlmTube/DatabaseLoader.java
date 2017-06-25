package FIlmTube;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;

/**
 * Created by Nacho on 28/05/2017.
 */
@Component
public class DatabaseLoader {

    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    private void initDatabase(){

        GrantedAuthority[] adminRoles = {
                new SimpleGrantedAuthority("ROLE_USER"),
                new SimpleGrantedAuthority("ROLE_ADMIN")
        };
        //Create
//        userRepository.save(new User("admin","admin","admin@email.com", Arrays.asList(adminRoles)));

    }
}
