package com.inno.loginExample.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Map;

@Slf4j
public class CustomUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private boolean postOnly = true;
    private Map<String, String> jsonRequest;

    
}
