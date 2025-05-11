package com.project.routorybackend.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Set;

/**
 * 인가 필터
 */
@Component
@RequiredArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {
    private final Set<String> EXCLUDE_ENDPOINTS = Set.of("/api/accounts/sign-in", "/api/accounts/sign-up", "/api/tokens/reissue");

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws IOException, ServletException {
        String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (authorization != null) {
            final String jwtToken = jwtTokenProvider.parseToken(authorization);
            final Authentication authentication = jwtTokenProvider.authenticate(jwtToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return EXCLUDE_ENDPOINTS.contains(request.getServletPath());
    }
}
