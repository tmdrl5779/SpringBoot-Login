package Social.Sociallogin.controller;

import Social.Sociallogin.config.auth.PrincipalDetail;
import Social.Sociallogin.domain.User;
import Social.Sociallogin.dto.KakaoProfile;
import Social.Sociallogin.dto.OAuthToken;
import Social.Sociallogin.dto.user.UserDto;
import Social.Sociallogin.service.KaKaoApiService;
import Social.Sociallogin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

//인증이 안된 사용자들 출입허용경로 /auth/**
//main
//static 이하

@Controller
public class UserController {

    private UserService userService;
    private AuthenticationManager authenticationManager;
    private KaKaoApiService kaKaoApiService;

    @Autowired
    public UserController(UserService userService, AuthenticationManager authenticationManager, KaKaoApiService kaKaoApiService) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.kaKaoApiService = kaKaoApiService;
    }

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

    @GetMapping("/user/updateForm")
    public String updateForm(){
        return "user/updateForm";
    }




    @GetMapping("/auth/kakao/callback")
    public String kakaoCallback(@RequestParam("code") String code){

        OAuthToken oAuthToken = kaKaoApiService.tokenRequest(code);

        KakaoProfile kakaoProfile = kaKaoApiService.userInfoRequest(oAuthToken);


        int idx = kakaoProfile.getKakao_account().getEmail().indexOf("@");
        String username = kakaoProfile.getKakao_account().getEmail().substring(0, idx);

        //User kakaoUser
        UserDto kakaoUser = UserDto.builder()  //kakao정보 userdto에 담기
                .username(username)
                .password(kakaoProfile.getKakao_account().getEmail() + kakaoProfile.getId())
                .oauth("kakao")
                .build();

        //회원가입, 로그인
        userService.join(kakaoUser);

        return "redirect:/";
    }
}
