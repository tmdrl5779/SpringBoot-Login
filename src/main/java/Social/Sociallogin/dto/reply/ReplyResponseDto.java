package Social.Sociallogin.dto.reply;

import Social.Sociallogin.domain.Board;
import Social.Sociallogin.domain.Reply;
import Social.Sociallogin.dto.board.BoardDto;
import Social.Sociallogin.dto.user.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReplyResponseDto {

    private int id;

    private int boardId;

    private String content;

    private UserDto user;

    private Timestamp createDate;

    public ReplyResponseDto(Reply entity, int boardId) {
        this.id = entity.getId();
        this.boardId = boardId;
        this.content = entity.getContent();
        this.user = new UserDto(entity.getUser());
        this.createDate = entity.getCreateDate();
    }


}
