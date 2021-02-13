package Social.Sociallogin.repository;

import Social.Sociallogin.domain.Board;
import Social.Sociallogin.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Integer> {

}
