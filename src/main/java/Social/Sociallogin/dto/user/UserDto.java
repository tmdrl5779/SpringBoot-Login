package Social.Sociallogin.dto.user;

import Social.Sociallogin.domain.User;
import lombok.*;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class UserDto {

    private int id;

    private String username;

    private String password;

    private String oauth;

    private Timestamp createDate;

    public UserDto(User entity) {
        this.id = entity.getId();
        this.username = entity.getUsername();
        this.password = entity.getPassword();
        this.oauth = entity.getOauth();
        this.createDate = entity.getCreateDate();
    }


    public User toEntity(){
        return User.builder()
                .id(id)
                .username(username)
                .password(password)
                .oauth(oauth)
                .createDate(createDate)
                .build();

    }


    public void updatePassword(String password) {
        this.password = password;
    }
}
