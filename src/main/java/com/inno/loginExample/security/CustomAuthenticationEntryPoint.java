package com.inno.loginExample.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * 인증 실패에 대해 처리하는 클래스
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        log.error("{} [{} {}]", authException.getMessage(), request.getMethod(), request.getRequestURI());

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        Map<String, Object> data = ErrorCodes.SESSION_EXPIRE.getResult();
        PrintWriter out = response.getWriter();
        out.print("authentication error");
        out.flush();
        out.close();
    }
}
