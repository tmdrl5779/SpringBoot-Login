package Social.Sociallogin.controller.api;

import Social.Sociallogin.config.auth.PrincipalDetail;
import Social.Sociallogin.domain.Board;
import Social.Sociallogin.domain.Reply;
import Social.Sociallogin.dto.ReplyResponseDto;
import Social.Sociallogin.dto.ReplySaveRequestDto;
import Social.Sociallogin.dto.ResponseDto;
import Social.Sociallogin.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.standard.expression.EqualsNotEqualsExpression;

@RestController
public class BoardApiController {

    private BoardService boardService;

    @Autowired
    public BoardApiController(BoardService boardService) {
        this.boardService = boardService;
    }

    @PostMapping("/api/board")
    public ResponseDto<Integer> save(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetail principal) {
        boardService.write(board, principal.getUser());
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    @DeleteMapping("/api/board/{id}")
    public ResponseDto<Integer> deleteById(@PathVariable int id, @AuthenticationPrincipal PrincipalDetail principal) throws Exception {
        if (boardService.delete(id, principal.getUser())) {
            return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
        } else {
            throw new Exception();
        }
    }


    @PostMapping("/api/board/{boardId}/reply")
    public ReplyResponseDto replySave(@RequestBody ReplySaveRequestDto replySaveRequestDto) {

        return boardService.replyWrite(replySaveRequestDto);
        //return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }
}
