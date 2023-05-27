package efub.toyproject.twitter.account.service;

import efub.toyproject.twitter.account.domain.Account;
import efub.toyproject.twitter.account.dto.SignUpRequestDto;
import efub.toyproject.twitter.account.repository.AccountRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

public class AccountSerivce {

    private AccountRepository accountRepository;

    public Long signup(SignUpRequestDto requestDto) {
        // 한 번에 한 가지 Exception만 떠야 관리하기 편할 것 같아서 두 개의 else문이 아닌 else if 문으로 처리했음
        if(existsByEmail(requestDto.getEmail()))
            throw new IllegalArgumentException("이미 존재하는 이메일입니다." + requestDto.toEntity());
        else if(existsByHandle(requestDto.getHandle()))
            throw new IllegalArgumentException("이미 존재하는 핸들입니다." + requestDto.toEntity());

        Account account = accountRepository.save(requestDto.toEntity());
        return account.getAccountId();
    }

    // ID를 기준으로 계정 하나 찾기
    @Transactional(readOnly = true)
    public Account findAccountById(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("해당 ID를 가진 계정이 없습니다."));
    }

    // 이메일 중복체크
    @Transactional(readOnly = true)
    public boolean existsByEmail(String email) {
        return accountRepository.existsByEmail(email);
    }

    // 핸들 중복체크
    @Transactional(readOnly = true)
    public boolean existsByHandle(String handle) {
        return accountRepository.existsByHandle(handle);
    }
}
