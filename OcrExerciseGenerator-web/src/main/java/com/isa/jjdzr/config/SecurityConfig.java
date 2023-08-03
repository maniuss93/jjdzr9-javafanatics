package com.isa.jjdzr.config;

import com.isa.jjdzr.handler.CustomAccessDeniedHandler;
import com.isa.jjdzr.handler.CustomAuthenticationSuccessHandler;
import com.isa.jjdzr.repository.UserRepository;
import com.isa.jjdzr.service.DatabaseUserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    final CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
    final CustomAccessDeniedHandler customAccessDeniedHandler;
    final UserRepository userRepository;
    final DataSource dataSource;

    public SecurityConfig(CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler, CustomAccessDeniedHandler customAccessDeniedHandler, UserRepository userRepository, DataSource dataSource) {
        this.customAuthenticationSuccessHandler = customAuthenticationSuccessHandler;
        this.customAccessDeniedHandler = customAccessDeniedHandler;
        this.userRepository = userRepository;
        this.dataSource = dataSource;
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailServiceBean());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    UserDetailsService userDetailServiceBean() {
        return new DatabaseUserDetailService(userRepository);
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
                .requestMatchers("/", "/user/new", "/calendar", "/user/login").permitAll()
                .requestMatchers("/css/**", "/js/**", "/images/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling()
                .accessDeniedHandler(customAccessDeniedHandler)
                .and()
                .formLogin()
                .loginPage("/user/login")
                .successHandler(customAuthenticationSuccessHandler)
                .usernameParameter("userName")
                .passwordParameter("userPassword")
                .and()
                .rememberMe()
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(86400)
                .and()
                .csrf()
                .and()
                .exceptionHandling()
                .and()
                .logout()
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .deleteCookies("JSESSIONID");
        return http.build();
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepositoryImpl = new JdbcTokenRepositoryImpl();
        tokenRepositoryImpl.setDataSource(dataSource);
        return tokenRepositoryImpl;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
