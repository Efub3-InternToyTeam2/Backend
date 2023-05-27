package efub.toyproject.twitter.account.controller;

import efub.toyproject.twitter.account.domain.Account;
import efub.toyproject.twitter.account.dto.AccountResponseDto;
import efub.toyproject.twitter.account.dto.SignUpRequestDto;
import efub.toyproject.twitter.account.service.AccountSerivce;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {
    private final AccountSerivce accountSerivce;

    // 계정 생성
    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public AccountResponseDto signup (@RequestBody @Valid final SignUpRequestDto requestDto) {
        // 회원가입을 진행하여 데이터베이스에 저장하고
        Long id = accountSerivce.signup(requestDto);
        // 방금 저장된 계정 정보를 데이터베이스에서 찾아온 뒤
        Account findAccount = accountSerivce.findAccountById(id);
        // 그 계정 정보를 확인용으로 리턴
        return AccountResponseDto.from(findAccount);
    }

    // ID를 기준으로 계정 하나의 정보 조회
    @GetMapping("/{accountId}")
    @ResponseStatus(value = HttpStatus.OK)
    public AccountResponseDto getAccount(@PathVariable Long accountId) {
        // 데이터베이스에서 해당 ID를 가진 계정 하나를 찾아옴
        Account findAccount = accountSerivce.findAccountById(accountId);
        // 찾아온 계정 정보를 DTO에 담아 리턴
        return AccountResponseDto.from(findAccount);
    }

    // ID를 기준으로 계정 하나의 정보 삭제
    @DeleteMapping("/{accountId}")
    @ResponseStatus(value = HttpStatus.OK)
    public String delete(@PathVariable Long accountId) {
        accountSerivce.delete(accountId);
        return "성공적으로 탈퇴가 완료되었습니다.";
    }

}
