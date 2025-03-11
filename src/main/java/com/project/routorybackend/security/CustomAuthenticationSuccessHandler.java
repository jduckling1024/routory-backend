package com.project.routorybackend.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.routorybackend.account.dto.SignInResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    ObjectMapper objectMapper = new ObjectMapper();

    @Value("${cookie.domain}")
    private String cookieDomain;

    private final JwtTokenProvider jwtTokenProvider;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");

        final String identification = authentication.getName();
        setRefreshToken(response, identification);

        final ResponseEntity<SignInResponse> responseEntity = new ResponseEntity<>(new SignInResponse(jwtTokenProvider.generateAccessToken(identification)), HttpStatus.OK);
        response.getWriter().write(objectMapper.writeValueAsString(responseEntity));
    }

    private void setRefreshToken(HttpServletResponse response, String email) {
        ResponseCookie cookie = ResponseCookie.from("Refresh-Token", jwtTokenProvider.generateRefreshToken(email))
                .domain(cookieDomain)
                .path("/")
                .httpOnly(true)
                .secure(true)
                .sameSite("None")
                .build();

        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
    }
}

