package Social.Sociallogin.dto.reply;

import Social.Sociallogin.domain.Board;
import Social.Sociallogin.domain.Reply;
import Social.Sociallogin.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReplyResponseDto {

    private int id;

    private String content;

    private Board board;

    private User user;

    private Timestamp createDate;

    public ReplyResponseDto(Reply entity) {
        this.id = entity.getId();
        this.content = entity.getContent();
        this.board = entity.getBoard();
        this.user = entity.getUser();
        this.createDate = entity.getCreateDate();
    }


}
