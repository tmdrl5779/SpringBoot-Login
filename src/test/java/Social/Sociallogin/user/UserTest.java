package Social.Sociallogin.user;

import Social.Sociallogin.domain.User;
import Social.Sociallogin.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class UserTest {

    @Autowired private UserRepository userRepository;

    @Test
    public void 회원가입(){
        User user = new User();
        user.setUserId("test1234");
        user.setUserPassword("asdfasdf");
        userRepository.save(user);
    }
}
