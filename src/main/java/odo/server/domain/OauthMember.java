package odo.server.domain;

import jakarta.persistence.*;
import lombok.*;
import odo.server.store.domain.UserPoint;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Builder
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor(access = PROTECTED)
@Table(name = "oauth_member", uniqueConstraints = {
        // uniqueConstraints를 통해 OauthId의 유일함을 보장
        @UniqueConstraint(name = "oauth_id_unique", columnNames = {
                "oauth_server_id",
                "oauth_server"
        }),
})
public class OauthMember {

    // 자동 증가되는 id 입니다.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 사용자의 별명과 프로필 사진 URL에 대한 정보를 제공받기로 했으므로, 이를 저장하기 위해 필드
    @Embedded
    private OauthId oauthId;
    private String email;
    private String profileImageUrl;
    private String nickname;

    // 리액트에서 받은 추가정보(블로그 이름, 블로그 주소, 닉네임) 등을 저장하기 위한 필드
    private String blogName;
    private String blogAddress;
    private String blogNickname;

    // profile 페이지에서 수정이 가능한 정보
    private String introduction;
    private String socialA;
    private String socialB;
    private String socialC;
    private String socialD;
    private boolean reviewReceived;
    private boolean updateReceived;
    private String actor;

    public Long id() {
        return id;
    }

    public OauthId oauthId() {
        return oauthId;
    }

    public String email() {
        return email;
    }

    public String nickname() {
        return nickname;
    }

    public String profileImageUrl() {
        return profileImageUrl;
    }

    public String blogName() {
        return blogName;
    }

    public String blogAddress() {
        return blogAddress;
    }

    public String blogNickname() {
        return blogNickname;
    }

    public String actor() {
        return actor;
    }

    public void UpdateOauthMember(String email, String nickname, String blogName, String introduction, String socialA,
            String socialB, String socialC, String socialD, boolean reviewReceived, boolean updateReceived) {
        this.email = email;
        // this.profileImageUrl = profileImageUrl;
        this.nickname = nickname;
        this.blogName = blogName;
        this.introduction = introduction;
        this.socialA = socialA;
        this.socialB = socialB;
        this.socialC = socialC;
        this.socialD = socialD;
        this.reviewReceived = reviewReceived;
        this.updateReceived = updateReceived;
    }

    @PostPersist
    public void onPostPersist() {
        // user 테이블에 새로운 값이 추가되면 호출되는 메서드
        UserPoint userPoint = new UserPoint();
        userPoint.setId(this.id);
        userPoint.setPoint(10000);
        // user_point 테이블에 새로운 레코드 추가
        // 이 부분은 해당 로직에 맞게 수정이 필요할 수 있습니다.
        // userRepository.save() 등을 사용하여 JPA를 통해 데이터베이스에 저장할 수 있습니다.
    }
}
