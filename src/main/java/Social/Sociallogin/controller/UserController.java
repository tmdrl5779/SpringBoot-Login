package Social.Sociallogin.controller;

import Social.Sociallogin.config.auth.PrincipalDetail;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

//인증이 안된 사용자들 출입허용경로 /auth/**
//main
//static 이하

@Controller
public class UserController {

    @GetMapping("/auth/joinForm")
    public String joinForm(@AuthenticationPrincipal PrincipalDetail principalDetail){
        if(Optional.ofNullable(principalDetail).isPresent()){
            return "redirect:/";
        }else{
            return "user/joinForm";
        }
    }

    @GetMapping("/auth/loginForm")
    public String loginForm(@AuthenticationPrincipal PrincipalDetail principalDetail){
        if(Optional.ofNullable(principalDetail).isPresent()){
            return "redirect:/";
        }else{
            return "user/loginForm";
        }
    }
}
