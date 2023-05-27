package efub.toyproject.twitter.account.domain;

import efub.toyproject.twitter.global.entity.BaseTimeEntity;
import efub.toyproject.twitter.tweet.domain.Tweet;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Account extends BaseTimeEntity {

    // 각 계정을 구별하는 고유 번호. 자동생성, Auto_increment
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id", updatable = false)
    private Long accountId;

    // 트윗 및 사용자 페이지에 @abcd와 같이 표시되는 문자열
    @Column(nullable = false, length = 15)
    private String handle;

    // 이메일
    @Column(nullable = false, length = 100)
    private String email;

    // 닉네임. null 가능
    @Column(length = 15)
    private String nickname;

    // 비밀번호
    @Column(nullable = false)
    private String encodedPassword;


    // 소개글. null 가능. 따로 설정할 사항(null 불가 여부나 길이 제한 등)이 없어서 @Column 어노테이션 생략
    private String profile;

    // 이 유저가 쓴 트윗
    // mappedBy : 연관 관계의 주인
    // cascade : 엔티티 삭제 시 연관된 엔티티의 처리 방식
    // orphanRemoval : 고아 객체의 처리 방식
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Tweet> tweetList = new ArrayList<>();

    @Builder
    public Account(String handle, String email, String nickname, String password, String profile) {
        this.handle = handle;
        this.email = email;
        // 사용자가 닉네임을 입력하지 않았다면 핸들을 닉네임으로 설정
        if(nickname.isBlank() || nickname.isEmpty() || nickname == null) this.nickname = handle;
        else this.nickname = nickname;
        this.encodedPassword = nickname;
        this.profile = profile;
    }

}