package Social.Sociallogin.dto.board;

import Social.Sociallogin.domain.Board;
import Social.Sociallogin.domain.Reply;
import Social.Sociallogin.dto.ResponseDto;
import Social.Sociallogin.dto.reply.ReplyResponseDto;
import Social.Sociallogin.dto.user.UserDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BoardDto {
    private int id;

    private String title;

    private String content;

    private int count;

    private UserDto userDto;

    private List<ReplyResponseDto> replys;

    private Timestamp createDate;

    public BoardDto(Board entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.count = entity.getCount();
        this.userDto = new UserDto(entity.getUser());
        this.replys = toListDto(entity.getReplys());
        this.createDate = entity.getCreateDate();
    }

    public Board toEntity(){
        return Board.builder()
                .title(title)
                .content(content)
                .user(userDto.toEntity())
                .build();
    }

    public void init(int count, UserDto userDto){
        this.count = count;
        this.userDto = userDto;
    }

    public List<ReplyResponseDto> toListDto(List<Reply> replys){
        return replys.stream()
                .map(Reply::toDto)
                .collect(Collectors.toList());
    }


}
