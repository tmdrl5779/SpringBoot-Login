package Social.Sociallogin.controller;

import Social.Sociallogin.config.auth.PrincipalDetail;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
    @RequestMapping("/")
    public String mainPage(@AuthenticationPrincipal PrincipalDetail principalDetail){// 컨트롤러에서 세션을 어떻게 찾는지??
        //System.out.println("로그인 사용자 아이디:" + principalDetail.getUsername());
        return "main";
    }
}
