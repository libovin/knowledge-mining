package com.hiekn.knowledge.mining.rbac.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hiekn.boot.autoconfigure.web.exception.handler.JwtAuthenticationEntryPoint;
import com.hiekn.knowledge.mining.rbac.authentication.jwt.JwtAuthenticationConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.web.cors.CorsUtils;


@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    private ObjectMapper objectMapper;

    @Bean
    public AuthenticationEntryPoint jwtAuthenticationEntryPoint() {
        return new JwtAuthenticationEntryPoint(objectMapper);
    }


    @Autowired
    private JwtAuthenticationConfigurer jwtAuthenticationConfigurer;

    @Value("${spring.jersey.application-path}")
    private String url;

    public void configure(HttpSecurity http) throws Exception {
        http
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)

                .and()
                .authorizeRequests()
                .requestMatchers(CorsUtils::isPreFlightRequest)
                .permitAll()
                .antMatchers(url + "/Swagger.html",
                        url + "/swagger.json",
                        "/Swagger/**")
                .permitAll()

                .anyRequest()
                .authenticated()
                .and()
                .apply(jwtAuthenticationConfigurer)
                .and()

                .formLogin()
                .disable()
                .httpBasic()
                .disable();
    }
}
