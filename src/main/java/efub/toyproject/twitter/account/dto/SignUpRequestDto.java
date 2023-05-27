package efub.toyproject.twitter.account.dto;


import efub.toyproject.twitter.account.domain.Account;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SignUpRequestDto {

    // 필요한 정보: 핸들, 이메일, 닉네임, 비밀번호, 소개글

    @NotBlank(message = "핸들은 필수로 입력해야 합니다.")
    private String handle;

    @NotBlank(message = "이메일은 필수로 입력해야 합니다.")
    @Email(message = "유효하지 않은 이메일 형식입니다.",
            regexp = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$")
    private String email;

    // null 가능
    private String nickname;

    @NotBlank(message = "비밀번호는 필수로 입력해야 합니다.")
    // 회원가입 화면은 구성하지 않을 것이므로 비밀번호의 형식 제한은 걸지 않았음
    private String password;

    // null 가능
    private String profile;

    // 각 필드의 값을 직접 받아와 SignUpRequestDto 객체를 생성하는 기본 생성자
    @Builder
    public SignUpRequestDto (String handle, String email, String nickname, String password, String profile) {
        this.handle = handle;
        this.email = email;
        // 사용자가 닉네임을 입력하지 않았다면 핸들을 닉네임으로 설정
        if(nickname.isBlank() || nickname.isEmpty() || nickname == null) this.nickname = handle;
        else this.nickname = nickname;
        this.password = password;
        this.profile = profile;
    }

    // SignUpRequestDto 객체를 Account 객체로 바꾸는 함수
    public Account toEntity() {
        return Account.builder()
                .handle(this.handle)
                .email(this.email)
                .nickname(this.nickname)
                .password(this.password)
                .profile(this.profile)
                .build();
    }
}
