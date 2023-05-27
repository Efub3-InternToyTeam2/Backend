package efub.toyproject.twitter.tweet.repository;

import efub.toyproject.twitter.account.domain.Account;
import efub.toyproject.twitter.tweet.domain.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TweetRepository extends JpaRepository<Tweet, Long> {

    // 작성자를 기준으로 해당 작성자가 쓴 모든 트윗을 리턴
    List<Tweet> findAllByWriter(Account writer);
}
