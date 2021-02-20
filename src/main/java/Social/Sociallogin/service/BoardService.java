package Social.Sociallogin.service;

import Social.Sociallogin.domain.Board;
import Social.Sociallogin.domain.Reply;
import Social.Sociallogin.domain.User;
import Social.Sociallogin.dto.reply.ReplyResponseDto;
import Social.Sociallogin.dto.reply.ReplySaveRequestDto;
import Social.Sociallogin.repository.BoardRepository;
import Social.Sociallogin.repository.ReplyRepository;
import Social.Sociallogin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class BoardService {
    private BoardRepository boardRepository;
    private ReplyRepository replyRepository;
    private UserRepository userRepository;

    @Autowired
    public BoardService(BoardRepository boardRepository, ReplyRepository replyRepository, UserRepository userRepository) {
        this.boardRepository = boardRepository;
        this.replyRepository = replyRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public void write(Board board, User user) { //title, content
        board.setCount(0);
        board.setUser(user);
        boardRepository.save(board);
    }

    @Transactional(readOnly = true)
    public Page<Board> postList(Pageable pageable) {
        return boardRepository.findAll(pageable);

    }

    @Transactional(readOnly = true)
    public Board boardDetail(int id) {
        return boardRepository.findById(id)
                .orElseThrow(() -> {
                    return new IllegalArgumentException("글 상세보기 실패 : 아이디 찾을 수 었습니다.");
                });
    }

    @Transactional
    public Boolean delete(int id, User user) {

        Optional<Board> board = boardRepository.findById(id);

        if (board.get().getUser().getUsername().equals(user.getUsername())) {
            boardRepository.deleteById(id);
            return true;
        }else{
            return false;
        }
    }

    @Transactional
    public ReplyResponseDto replyWrite(ReplySaveRequestDto replySaveRequestDto) {
        Board board = boardRepository.findById(replySaveRequestDto.getBoardId()).orElseThrow(() -> {
            return new IllegalArgumentException("댓글 쓰기 실패 : 게시글 id를 찾을수 없습니다.");
        });

        User user = userRepository.findById(replySaveRequestDto.getUserId()).orElseThrow(() -> {
            return new IllegalArgumentException("댓글 쓰기 실패 : 게시글 id를 찾을수 없습니다.");
        });

        Reply reply = new Reply();
        reply.update(user, board, replySaveRequestDto.getContent());
        replyRepository.save(reply);
        return new ReplyResponseDto(reply);
    }

    @Transactional
    public void replyRemove(int replyId) {
        replyRepository.deleteById(replyId);
    }
}
