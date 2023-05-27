package efub.toyproject.twitter.tweet.dto;

import efub.toyproject.twitter.tweet.domain.Tweet;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

// 트윗 하나가 아닌 여러 개의 리스트를 전달하는 응답용 DTO
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TweetListResponseDto {

    // 트윗 리스트 자체의 필드
    private List<TweetListResponseDto.SingleTweet> tweetList;    // 트윗들
    private Integer tweetCount; // 트윗 수


    // 트윗 하나에 대한 클래스
    @Getter
    public static class SingleTweet{
        private Long tweetId;
        private Long writerId;
        private String writerHandle;
        private String writerNickname;
        private String content;
        private LocalDateTime createdDate;
        private LocalDateTime modifiedDate;

        public SingleTweet (Tweet tweet) {
            this.tweetId = tweet.getTweetId();
            this.writerId = tweet.getWriter().getAccountId();
            this.writerHandle = tweet.getWriter().getHandle();
            this.writerNickname = tweet.getWriter().getNickname();
            this.content = tweet.getContent();
            this.createdDate = tweet.getCreatedDate();
            this.modifiedDate = tweet.getModifiedDate();
        }

        public static TweetListResponseDto.SingleTweet of(Tweet tweet) {
            return new TweetListResponseDto.SingleTweet(tweet);
        }
    }

    // 트윗 리스트 객체의 기본 생성자
    @Builder
    public TweetListResponseDto(List<TweetListResponseDto.SingleTweet> tweetList, Integer tweetCount) {
        this.tweetList = tweetList;
        this.tweetCount = tweetCount;
    }


    public static TweetListResponseDto from(List<Tweet> tweets) {
        return TweetListResponseDto.builder()
                .tweetList(tweets.stream().map(TweetListResponseDto.SingleTweet::of).collect(Collectors.toList()))
                .tweetCount(tweets.size())
                .build();
    }

}
