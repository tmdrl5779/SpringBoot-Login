package Social.Sociallogin.controller;

import Social.Sociallogin.config.auth.PrincipalDetail;
import Social.Sociallogin.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    private BoardService boardService;

    @Autowired
    public MainController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/")
    public String mainPage(Model model, @PageableDefault(size = 3, sort = "id", direction = Sort.Direction.DESC)Pageable pageable){// @AuthenticationPrincipal PrincipalDetail principalDetail 컨트롤러에서 세션을 어떻게 찾는지??
        //System.out.println("로그인 사용자 아이디:" + principalDetail.getUsername());

        model.addAttribute("boards", boardService.postList(pageable));
        return "main";
    }


}
