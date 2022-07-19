package com.weatherinloop.frontend.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import com.weatherinloop.frontend.Security.UserAuthentication;

@Configuration
public class ProjectSecurityConfig {

    /*
     * @Bean
     * public InMemoryUserDetailsManager userDetailsService() {
     * UserDetails user = User
     * .withUsername("user")
     * .password("password")
     * .roles("USER")
     * .build();
     * return new InMemoryUserDetailsManager(user);
     * }
     */

    @Autowired
    UserAuthentication userAuthentication;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().ignoringAntMatchers("/home").and()
                .authorizeRequests()
                // .mvcMatchers("/dashboard").authenticated()
                .mvcMatchers("/login").permitAll()
                .mvcMatchers("/register").permitAll()
                .and().formLogin().loginPage("/login")
                .defaultSuccessUrl("/home").failureUrl("/login?error=true").permitAll()
                .and().logout().logoutSuccessUrl("/login?logout=true").invalidateHttpSession(true).permitAll()
                .and().httpBasic();
        return http.build();
    }

    /*
     * @Bean
     * public AuthenticationManagerBuilder
     * authenticateMethod(AuthenticationManagerBuilder auth) {
     * auth.authenticationProvider(userAuthentication);
     * return(auth);
     * }
     */

}
