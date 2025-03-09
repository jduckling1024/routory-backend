package com.project.routorybackend.token.service;

import com.project.routorybackend.security.JwtTokenProvider;
import com.project.routorybackend.token.dto.CreateTokenResult;
import com.project.routorybackend.token.dto.ReIssueResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;

@Slf4j
@Service
@RequiredArgsConstructor
public class TokenApiService {
    private final JwtTokenProvider jwtTokenProvider;

    public CreateTokenResult createToken(String identification) {
        final String accessToken = createAccessToken(identification);
        final String refreshToken = createRefreshToken(identification);

        return new CreateTokenResult(accessToken, refreshToken);
    }

    public ReIssueResult reissue(String token) {
        try {
            if (token == null || jwtTokenProvider.isTokenExpired(token)) {
                throw new BadRequestException("유효하지 않은 토큰입니다.");
            }

            final String accountId = jwtTokenProvider.getAccountId(token);
            final String savedRefreshToken = jwtTokenProvider.getRefreshToken(accountId);

            if (!token.equals(savedRefreshToken)) {
                jwtTokenProvider.invalidateToken(token);
                throw new BadRequestException("토큰이 유효하지 않습니다");
            }

            final String accessToken = createAccessToken(accountId);
            final String refreshToken = createRefreshToken(accountId);

            return new ReIssueResult(accessToken, refreshToken);
        } catch (Exception e) {
            log.warn("토큰 재발급 실패. message={}", e.getMessage(), e);
            throw new InvalidParameterException("토큰 재발급 실패");
        }
    }

    private String createAccessToken(String identification) {
        return jwtTokenProvider.generateAccessToken(identification);
    }

    private String createRefreshToken(String identification) {
        return jwtTokenProvider.generateRefreshToken(identification);
    }
}
