package efub.toyproject.twitter.tweet.controller;

import efub.toyproject.twitter.tweet.domain.Tweet;
import efub.toyproject.twitter.tweet.dto.TweetListResponseDto;
import efub.toyproject.twitter.tweet.dto.TweetRequestDto;
import efub.toyproject.twitter.tweet.dto.TweetResponseDto;
import efub.toyproject.twitter.tweet.service.TweetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tweets")
public class TweetController {

    private final TweetService tweetService;

    // 트윗 생성
    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public TweetResponseDto write (@RequestBody @Valid final TweetRequestDto requestDto) {
        // 새로운 트윗 데이터를 데이터베이스에 저장하고
        Long id = tweetService.write(requestDto);
        // 방금 저장된 트윗 정보를 데이터베이스에서 찾아온 뒤
        Tweet findTweet = tweetService.findTweetByTweetId(id);
        // 찾아온 트윗 정보를 DTO에 담아 확인용으로 리턴
        return TweetResponseDto.from(findTweet);
    }

    // ID를 기준으로 트윗 하나 조회
    @GetMapping("/{tweetId}")
    @ResponseStatus(value = HttpStatus.OK)
    public TweetResponseDto getTweet(@PathVariable Long tweetId) {
        //데이터베이스에서 해당 ID를 가진 트윗 하나를 찾아옴
        Tweet findTweet = tweetService.findTweetByTweetId(tweetId);
        // 찾아온 트윗 정보를 DTO에 담아 리턴
        return TweetResponseDto.from(findTweet);
    }

    // 사용자 ID를 기준으로 특정 사용자가 작성한 모든 트윗 조회
    @GetMapping("/writer/{accountId}")
    @ResponseStatus(value = HttpStatus.OK)
    public TweetListResponseDto getAccountTweets (@PathVariable Long accountId) {
        List<Tweet> tweetList = tweetService.findAllByWriter(accountId);
        return TweetListResponseDto.from(tweetList);
    }

    // 모든 트윗 리스트 조회
    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public TweetListResponseDto getAllTweets () {
        List<Tweet> tweetList = tweetService.findAll();
        return TweetListResponseDto.from(tweetList);
    }

    // ID를 기준으로 트윗 하나 삭제
    @DeleteMapping("/{tweetId}")
    @ResponseStatus(value = HttpStatus.OK)
    public String deleteTweet (@PathVariable Long tweetId) {
        tweetService.delete(tweetId);
        return "성공적으로 트윗 삭제가 완료되었습니다.";
    }

}
