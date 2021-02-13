package Social.Sociallogin.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 100)
    private String title;

    @Lob //대용량 데이터
    private String content;

    private int count;

    @ManyToOne// Many = Board, User = One
    @JoinColumn(name="userId")
    private User user; //DB는 오브젝트를 저장할 수 없어서 FK사용 but
                       //자바는 오브젝트를 저장할 수 있다.
                       //User의 id를 FK로 함

    @CreationTimestamp
    private Timestamp createDate;



}
