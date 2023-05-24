package com.isa.jjdzr.config;

public class SecurityConfig {
    @Configuration
    @EnableWebSecurity
    public class SecurityConfig {

        @Autowired
        public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
            auth.inMemoryAuthentication()
                    .withUser("admin").password("{noop}password").roles("ADMIN");
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
            return NoOpPasswordEncoder.getInstance();
        }

        @Configuration
        public static class WebSecurityConfig implements WebMvcConfigurer {

            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(new SecurityInterceptor()).addPathPatterns("/admin/**");
            }
        }

        public static class SecurityInterceptor extends HandlerInterceptorAdapter {

            @Override
            public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
                    throws Exception {
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                if (authentication == null || !authentication.isAuthenticated()) {
                    response.sendRedirect("/login");
                    return false;
                }
                return true;
            }
        }
    }

}
