package Social.Sociallogin.service;

import Social.Sociallogin.domain.User;
import Social.Sociallogin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void join(User user){
        validateDuplicateUser(user);
        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public void validateDuplicateUser(User user){
        userRepository.findByUsername(user.getUsername())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원");
                });
    }

    @Transactional(readOnly = true)
    public User login(User user){
        return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
    }


}
