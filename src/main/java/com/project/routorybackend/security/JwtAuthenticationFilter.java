package com.project.routorybackend.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.routorybackend.account.dto.SignInRequest;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.io.IOException;

public class JwtAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    public JwtAuthenticationFilter() {
        super(new AntPathRequestMatcher("/api/accounts/sign-in", HttpMethod.POST.name()));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) throws AuthenticationException, IOException, ServletException {
        SignInRequest signInRequest = MAPPER.readValue(req.getReader(), SignInRequest.class);

        final UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(signInRequest.getIdentification(), signInRequest.getPassword());

        return this.getAuthenticationManager().authenticate(token);
    }
}
