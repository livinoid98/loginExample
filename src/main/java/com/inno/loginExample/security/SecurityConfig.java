package com.inno.loginExample.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@EnableWebSecurity
@Slf4j
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    @Value("${project.security.login-processing-url}")
    private String loginProcessingUrl;

    @Value("${project.security.username-parameter:username}")
    private String usernameParameter;

    @Value("${project.security.password-parameter:password}")
    private String passwordParameter;

    @Value("${project.security.logout-url}")
    private String logoutUrl;


    private final CustomAuthenticationEntryPoint authenticationEntryPoint;

    @Bean
    public SecurityFilterChain filterChain(final @NotNull HttpSecurity http) throws Exception {
        http
                .httpBasic(HttpBasicConfigurer::disable)
                .csrf(CsrfConfigurer::disable)
                .cors(Customizer.withDefaults())
                .sessionManagement(configurer -> configurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize ->
                        authorize
                                .requestMatchers("/login").permitAll()
                                .anyRequest().authenticated()
                )
                .exceptionHandling(authenticationManager ->
                        authenticationManager
                                .authenticationEntryPoint(authenticationEntryPoint)
                )
                ;
        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOriginPattern("*");
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod(HttpMethod.POST);
        configuration.addAllowedMethod(HttpMethod.GET);
        configuration.addAllowedMethod(HttpMethod.HEAD);
        configuration.setAllowCredentials(true);
        configuration.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }

    private CustomUsernamePasswordAuthenticationFilter getAuthenticationFilter(AuthenticationManager authenticationManager) throws Exception {
        CustomUsernamePasswordAuthenticationFilter authenticationFilter = new CustomUsernamePasswordAuthenticationFilter();
        authenticationFilter.setFilterProcessesUrl(loginProcessingUrl);
        authenticationFilter.setUsernameParameter(usernameParameter);
        authenticationFilter.setPasswordParameter(passwordParameter);
        return authenticationFilter;
    }
}
