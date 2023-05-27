package efub.toyproject.twitter.account.repository;

import efub.toyproject.twitter.account.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
    // 계정 사이 중복 불가능한 값: 이메일, 핸들
    Boolean existsByEmail(String email);

    Boolean existsByHandle(String handle);
}
