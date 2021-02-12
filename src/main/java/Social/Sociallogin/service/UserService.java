package Social.Sociallogin.service;

import Social.Sociallogin.domain.User;
import Social.Sociallogin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Transactional
    public void join(User user){
        validateDuplicateUser(user);
        String encPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encPassword);
        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public void validateDuplicateUser(User user){
        userRepository.findByUsername(user.getUsername())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원");
                });
    }

    //////////전통 로그인///////////
    /*@Transactional(readOnly = true)
    public User login(User user){
        return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
    }*/


}
