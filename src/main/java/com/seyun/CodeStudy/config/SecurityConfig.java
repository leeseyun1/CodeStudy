package com.seyun.CodeStudy.config;

import com.seyun.CodeStudy.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private MemberService memberService;

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .csrf().disable();

        http
                .authorizeRequests()
                .antMatchers("/auth/login", "/auth/signup").permitAll()
                .antMatchers("/member/**").permitAll()
                .antMatchers("/lecture/list", "/lecture/view").permitAll()
                .antMatchers("/post/list", "/post/view").permitAll()
                .antMatchers("/video/**").permitAll()
                .antMatchers("/comment/**").permitAll() //수정 해야함 ***
                .antMatchers("/img/**", "/css/**", "/js/**", "/upload/**").permitAll()
                .anyRequest().authenticated();

        http
                .formLogin()
                .loginPage("/auth/login")
                .loginProcessingUrl("/login")
                .usernameParameter("id")
                .passwordParameter("password")
                .defaultSuccessUrl("/lecture/list");

        http
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/lecture/list");

        return http.build();
    }

    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(memberService).passwordEncoder(passwordEncoder());
    }
}
