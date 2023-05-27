package efub.toyproject.twitter.tweet.dto;

import efub.toyproject.twitter.account.domain.Account;
import efub.toyproject.twitter.tweet.domain.Tweet;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TweetRequestDto {

    // 필요한 정보: 작성자 ID, 트윗 내용

    @NotNull(message = "작성자 ID를 입력해주세요")
    private Long accountId;

    @NotNull(message = "트윗 내용을 입력해주세요")
    private String content;

    // 각 필드의 값을 직접 받아와 TweetRequestDto 객체를 생성하는 생성자
    @Builder
    public TweetRequestDto (Long accountId, String content) {
        this.accountId = accountId;
        this.content = content;
    }

    // TweetRequestDto 객체를 Tweet 객체로 바꾸는 함수
    public Tweet toEntity(Account writer) {
        return Tweet.builder()
                .content(this.content)
                .writer(writer)
                .build();
    }
}
