package com.fastcampus.projectboard.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecuityConfig {

//    antMatchers == mvcMatchers
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(auth -> auth
                        .mvcMatchers(
                                HttpMethod.GET,
                                "/",
                                "articles",
                                "/articles/search-hashtag"
                        ).permitAll()
                        .anyRequest().authenticated()

//                        .anyRequest().permitAll() // 전체 다 허용
                )
                .formLogin().and()
                .csrf().disable()
                .build();
    }
}
