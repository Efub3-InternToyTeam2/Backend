package efub.toyproject.twitter.account.dto;

import efub.toyproject.twitter.account.domain.Account;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AccountResponseDto {

    private Long accountId;

    private String handle;

    private String email;

    private String nickname;

    private String profile;

    // 각 필드값을 모두 받아와 AccountResponseDto 객체를 생성하는 기본 생성자
    @Builder
    public AccountResponseDto(Long accountId, String handle, String email, String nickname, String profile) {
        this.accountId = accountId;
        this.handle = handle;
        this.email = email;
        this.nickname = nickname;
        this.profile = profile;
    }

    // Account 객체를 받아와 AccountResponseDto 객체를 생성하는 생성자
    public static AccountResponseDto from(Account account) {
        return new AccountResponseDto(
                account.getAccountId(),
            account.getHandle(),
            account.getEmail(),
            account.getNickname(),
            account.getProfile()
        );
    }

}
