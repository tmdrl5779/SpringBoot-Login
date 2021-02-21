package Social.Sociallogin.domain;

import Social.Sociallogin.dto.board.BoardDto;
import Social.Sociallogin.dto.reply.ReplyResponseDto;
import Social.Sociallogin.dto.user.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Reply {
    @Id //Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 200)
    private String content;

    @ManyToOne //Many : Reply , One : Board
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne //Many : Reply, One : User 한 유저는 여러 댓글을 쓸 수 있음
    @JoinColumn(name = "user_id")
    private User user;

    @CreationTimestamp
    private Timestamp createDate;

    public void update(User user, Board board, String content){
        this.user = user;
        this.board = board;
        this.content = content;
    }

    public ReplyResponseDto toDto(){
        return ReplyResponseDto.builder()
                .id(id)
                .content(content)
                .user(new UserDto(user))
                .createDate(createDate)
                .build();
    }
}
