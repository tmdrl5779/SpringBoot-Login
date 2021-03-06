package Social.Sociallogin.dto.reply;

import Social.Sociallogin.domain.Reply;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReplySaveRequestDto {
    private int userId;
    private int boardId;
    private String content;

}
