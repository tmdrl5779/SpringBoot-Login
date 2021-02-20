package Social.Sociallogin.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 100)
    private String title;

    @Lob //대용량 데이터
    private String content;

    private int count;

    //기본전략 : EAGER, user가 한건밖에 없으니까 바로가져오는 전략
    @ManyToOne(fetch = FetchType.EAGER)// Many = Board, User = One
    @JoinColumn(name="user_id")
    private User user; //DB는 오브젝트를 저장할 수 없어서 FK사용 but
                       //자바는 오브젝트를 저장할 수 있다.
                       //User의 id를 FK로 함

    //기본전략 : LAZY ,필요하면 들고오고 안필요하면 안들고옴
    @OneToMany(mappedBy = "board", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)// One = Board, Many = Reply , FK가 필요없다
    @JsonIgnoreProperties({"board"}) //무한참조 방지
    @OrderBy("id desc")
    private List<Reply> replys;    // mappedBy 연관관계의 주인이 아니다 (FK가 아니다, DB에 컬럼을 만들지마라)
                                  // join 문을 통해 값을 얻기위해 필요한 것

    @CreationTimestamp
    private Timestamp createDate;



}
