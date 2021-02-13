package Social.Sociallogin.repository;

import Social.Sociallogin.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);

    //전통 로그인//
    //User findByUsernameAndPassword(String username, String password);
}
