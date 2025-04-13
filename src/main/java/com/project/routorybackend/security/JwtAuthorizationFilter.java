package com.project.routorybackend.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

/**
 * 인가 필터
 */
@Component
@RequiredArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {
    private final String SIGN_IN_ENDPOINT = "/api/accounts/sign-in";
    private final String SIGN_UP_ENDPOINT = "/api/accounts/sign-up";
    private final String REISSUE_ENDPOINT = "/api/tokens/reissue";
    private final List<String> EXCLUDE_ENDPOINTS = List.of(SIGN_IN_ENDPOINT, SIGN_UP_ENDPOINT, REISSUE_ENDPOINT);

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws IOException, ServletException {
        String authorization = request.getHeader("Authorization");

        if (authorization != null) {
            final String jwtToken = jwtTokenProvider.parseToken(authorization);
            final Authentication authentication = jwtTokenProvider.authenticate(jwtToken); // 확인
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return EXCLUDE_ENDPOINTS.stream().anyMatch(endpoint -> request.getServletPath().startsWith(endpoint));
    }
}
