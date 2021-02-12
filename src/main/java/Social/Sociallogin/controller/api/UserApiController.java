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

@RestController
public class UserApiController {

    UserService userService;

    @Autowired
    public UserApiController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("api/user")
    public ResponseDto<Integer> save(@RequestBody User user){
        System.out.println("UserApiController : save호출");
        userService.join(user);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }
    @PostMapping("api/user/login")
    public ResponseDto<Integer> find(@RequestBody User user, HttpSession session){
        System.out.println("UserApiController : login호출");
        User principal = userService.login(user); //principal(접근 주체)

        if(principal != null){
            session.setAttribute("principal", principal);
        }
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

}
