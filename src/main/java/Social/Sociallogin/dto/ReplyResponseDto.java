package Social.Sociallogin.dto;

import Social.Sociallogin.domain.Board;
import Social.Sociallogin.domain.Reply;
import Social.Sociallogin.domain.User;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;

@Getter
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
