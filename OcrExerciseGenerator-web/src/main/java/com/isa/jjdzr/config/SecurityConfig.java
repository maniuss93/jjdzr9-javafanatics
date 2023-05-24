package com.isa.jjdzr.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public InMemoryUserDetailsManager inMemoryUsers() {
        UserDetails user = User.withUsername("User")
                .password("user")
                .roles("USER")
                .build();

        UserDetails user2 = User.withUsername("User2")
                .password("user2")
                .roles("USER")
                .build();
        UserDetails admin = User.withUsername("admin")
                .password("admin")
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user,user2,admin);
    }



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests((autz) -> {
                    try {
                        autz
                                .requestMatchers("/","/demo","/user/new","/calendar","/info").permitAll()
                                .requestMatchers("/exercises/accept/**", "/exercises/delete/**").hasRole("ADMIN")
                                .anyRequest().authenticated()
                                .and()
                                .formLogin()
                                .and()
                                .logout()
                                .logoutSuccessUrl("/")
                                .and()
                                .csrf().disable();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
        );
        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
