package Social.Sociallogin.repository;

import Social.Sociallogin.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
