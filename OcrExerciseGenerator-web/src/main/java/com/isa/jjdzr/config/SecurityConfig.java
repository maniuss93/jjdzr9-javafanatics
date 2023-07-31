package com.isa.jjdzr.config;

import com.isa.jjdzr.repository.UserRepository;
import com.isa.jjdzr.service.DatabaseUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.UserDetailsServiceFactoryBean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    final UserRepository userRepository;
    final DataSource dataSource;

    public SecurityConfig(UserRepository userRepository, DataSource dataSource) {
        this.userRepository = userRepository;
        this.dataSource = dataSource;
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
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
        http.authorizeHttpRequests((authz) -> {
            try {
                authz
                        .requestMatchers("/", "/user/new", "/calendar", "/user/login").permitAll()
                        .requestMatchers("/css/**", "/js/**", "/images/**").permitAll()
                        .anyRequest().authenticated()
                        .and()
                        .formLogin()
                        .loginPage("/user/login")
                        .defaultSuccessUrl("/user/1/userpanel", true)
                        .usernameParameter("userName")
                        .passwordParameter("userPassword")
                        .and()
                        .rememberMe()
                        .rememberMeParameter("remember-me")
                        .tokenRepository(persistentTokenRepository())
                        .tokenValiditySeconds(86400)
                        .and()
                        .csrf()
                        .and()
                        .exceptionHandling()
                        .and()
                        .logout();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
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
