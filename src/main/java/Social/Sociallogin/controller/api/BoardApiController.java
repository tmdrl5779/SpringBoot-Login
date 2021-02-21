package Social.Sociallogin.controller.api;

import Social.Sociallogin.config.auth.PrincipalDetail;
import Social.Sociallogin.domain.Board;
import Social.Sociallogin.dto.board.BoardDto;
import Social.Sociallogin.dto.reply.ReplyResponseDto;
import Social.Sociallogin.dto.reply.ReplySaveRequestDto;
import Social.Sociallogin.dto.ResponseDto;
import Social.Sociallogin.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
public class BoardApiController {

    private BoardService boardService;

    @Autowired
    public BoardApiController(BoardService boardService) {
        this.boardService = boardService;
    }

    @PostMapping("/api/board")
    public ResponseDto<Integer> save(@RequestBody BoardDto boardDto, @AuthenticationPrincipal PrincipalDetail principal) {
        boardService.write(boardDto, principal.getUserDto());
        //boardService.write(board, principal.getUser);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    @DeleteMapping("/api/board/{id}")
    public ResponseDto<Integer> deleteById(@PathVariable int id, @AuthenticationPrincipal PrincipalDetail principal) throws Exception {
        if (boardService.delete(id, principal.getUserDto())) {
        //if (boardService.delete(id, principal.getUser())) {
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

    @DeleteMapping("/api/board/{boardId}/reply/{replyId}")
    public ResponseDto<Integer> replyDelete(@PathVariable int replyId){
        boardService.replyRemove(replyId);
        return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
    }
}
