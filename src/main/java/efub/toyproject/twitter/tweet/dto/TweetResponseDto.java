package efub.toyproject.twitter.tweet.dto;

import efub.toyproject.twitter.account.dto.AccountResponseDto;
import efub.toyproject.twitter.tweet.domain.Tweet;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TweetResponseDto {

    private Long tweetId;
    private Long writerId;
    private String writerHandle;
    private String writerNickname;
    private String content;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    // 각 필드값을 모두 받아와 TweetResponseDto 객체를 생성하는 기본 생성자
    @Builder
    public TweetResponseDto(Long tweetId, Long writerId, String writerHandle, String writerNickname, String content, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.tweetId = tweetId;
        this.writerId = writerId;
        this.writerHandle = writerHandle;
        this.writerNickname = writerNickname;
        this.content = content;
        this.createdDate =  createdDate;
        this.modifiedDate = modifiedDate;
    }

    // Tweet 객체를 받아와 TweetResponseDto 객체를 생성하는 생성자
    public static TweetResponseDto from(Tweet tweet) {
        return new TweetResponseDto(
                tweet.getTweetId(),
                tweet.getWriter().getAccountId(),
                tweet.getWriter().getHandle(),
                tweet.getWriter().getNickname(),
                tweet.getContent(),
                tweet.getCreatedDate(),
                tweet.getModifiedDate()
        );
    }

}
