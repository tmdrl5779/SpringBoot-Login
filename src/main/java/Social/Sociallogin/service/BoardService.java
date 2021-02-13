package Social.Sociallogin.service;

import Social.Sociallogin.domain.Board;
import Social.Sociallogin.domain.User;
import Social.Sociallogin.repository.BoardRepository;
import Social.Sociallogin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.standard.expression.EqualsNotEqualsExpression;

import java.util.List;
import java.util.Optional;

@Service
public class BoardService {
    BoardRepository boardRepository;

    @Autowired
    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
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
    }
