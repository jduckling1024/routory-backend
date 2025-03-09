package com.project.routorybackend.security;

import com.project.routorybackend.account.model.Account;
import com.project.routorybackend.account.service.AccountQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 사용자에 관한 세부 정보 관리
 * UserDetailsService의 기본 구현체는 메모리에 자격 증명을 등록한다.
 */
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final AccountQueryService accountQueryService;

    @Override
    public UserDetails loadUserByUsername(String identification) {
        final Account account = accountQueryService.findByIdentification(identification)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("(identification : %s) 유저를 찾을 수 없습니다", identification)));

        return new AccountAdapter(account);
    }
}
