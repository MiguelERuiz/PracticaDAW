package FIlmTube;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by Nacho on 28/05/2017.
 */
public interface UserRepository extends CrudRepository<User,Long>{

    User findByUser(String nickname);
    User findUserByEmail(String email);

}
