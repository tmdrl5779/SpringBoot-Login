package Social.Sociallogin.controller.api;

import Social.Sociallogin.domain.User;
import Social.Sociallogin.dto.ResponseDto;
import Social.Sociallogin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@RestController
public class UserApiController {

    UserService userService;
    HttpSession httpSession; //세션은 빈에 이미 등록되어있음

    @Autowired
    public UserApiController(UserService userService, HttpSession httpSession) {
        this.userService = userService;
        this.httpSession = httpSession;
    }

    @PostMapping("api/user")
    public ResponseDto<Integer> save(@RequestBody User user){
        System.out.println("UserApiController : save호출");
        userService.join(user);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }
    @PostMapping("api/user/login")
    public ResponseDto<Integer> find(@RequestBody User user){
        System.out.println("UserApiController : login호출");
        User principal = userService.login(user); //principal(접근 주체)

        Optional<User> optPrincipal = Optional.of(principal);

        httpSession.setAttribute("principal", optPrincipal.get());
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);


    }

}
