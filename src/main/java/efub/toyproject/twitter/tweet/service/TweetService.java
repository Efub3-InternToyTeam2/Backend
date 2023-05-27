package efub.toyproject.twitter.tweet.service;

import efub.toyproject.twitter.account.domain.Account;
import efub.toyproject.twitter.account.repository.AccountRepository;
import efub.toyproject.twitter.tweet.domain.Tweet;
import efub.toyproject.twitter.tweet.dto.TweetRequestDto;
import efub.toyproject.twitter.tweet.repository.TweetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TweetService {

    private final TweetRepository tweetRepository;
    private final AccountRepository accountRepository;


    public Long write(TweetRequestDto requestDto) {
        // 작성자 데이터 찾아오기
        Account writer = accountRepository.findById(requestDto.getAccountId())
                .orElseThrow(() -> new EntityNotFoundException("해당 ID를 가진 계정이 없습니다."));
        // 찾아온 작성자 데이터를 넣어 저장
        Tweet tweet = tweetRepository.save(requestDto.toEntity(writer));
        // 저장된 트윗의 ID를 리턴
        return tweet.getTweetId();
    }

    public Tweet findTweetByTweetId(Long id) {
        return tweetRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("해당 ID를 가진 트윗이 없습니다."));
    }

    // 특정 작성자의 모든 트윗 리스트를 리턴
    public List<Tweet> findAllByWriter(Long accountId) {
        // 해당 ID를 가진 계정 정보 찾아오기
        Account writer = accountRepository.findById(accountId)
                .orElseThrow(() -> new EntityNotFoundException("해당 ID를 가진 계정이 없습니다."));
        // 찾아온 계정을 기준으로 트윗 리스트 받아오기
        return tweetRepository.findAllByWriter(writer);
    }

    // 존재하는 모든 트윗의 리스트를 리턴
    public List<Tweet> findAll() {
        return tweetRepository.findAll();
    }

    // ID를 기준으로 트윗 하나 삭제
    public void delete(Long tweetId) {
        // 해당 ID를 가진 트윗 하나를 데이터베이스에서 찾은 후
        Tweet tweet = findTweetByTweetId(tweetId);
        // 그 트윗을 삭제
        tweetRepository.delete(tweet);
    }
}
