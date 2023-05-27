package efub.toyproject.twitter.tweet.domain;

import efub.toyproject.twitter.account.domain.Account;
import efub.toyproject.twitter.global.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Tweet extends BaseTimeEntity {

    //각 트윗을 구별하는 고유 번호. 자동생성, Auto_increment
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tweet_id", updatable = false)
    private Long tweetId;

    // 트윗의 내용
    private String content;

    // 작성자
    // mappedBy, cascade, orphanRemoval 은 부모 객체 측에만 적나 봄
    // 여기는 자식이라서 fetch type 만 적는 것 같음
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false, updatable = false)
    private Account writer;

    @Builder
    public Tweet(String content, Account writer) {
        this.content = content;
        this.writer = writer;
    }

}
