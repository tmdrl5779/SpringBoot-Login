package Social.Sociallogin.controller.api;

import Social.Sociallogin.domain.User;
import Social.Sociallogin.dto.ResponseDto;
import Social.Sociallogin.dto.user.UserDto;
import Social.Sociallogin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserApiController {

    private UserService userService;


    @Autowired
    public UserApiController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/auth/joinProc")
    public ResponseDto<Integer> save(@RequestBody UserDto userDto){
        System.out.println("UserApiController : save호출");
        userService.join(userDto);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    @PutMapping("/user")
    public ResponseDto<Integer> update(@RequestBody UserDto userDto){
        userService.userModify(userDto);
        //DB값만 바뀌고 세션값은 바뀌지 않음
        //세션값을 바꿔줘야함

        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);

    }

    ///////////전통적인 로그인인////////////
   /*@PostMapping("api/user/login")
    public ResponseDto<Integer> find(@RequestBody User user, HttpSession httpSession){
        System.out.println("UserApiController : login호출");
        User principal = userService.login(user); //principal(접근 주체)

        Optional<User> optPrincipal = Optional.of(principal);

        httpSession.setAttribute("principal", optPrincipal.get());
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);

    }*/

}
