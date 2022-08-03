package com.weatherinloop.frontend.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.ldap.EmbeddedLdapServerContextSourceFactoryBean;
import org.springframework.security.web.SecurityFilterChain;

import com.weatherinloop.frontend.Security.UserAuthentication;

@Configuration
public class ProjectSecurityConfig {
    @Autowired
    UserAuthentication userAuthentication;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().ignoringAntMatchers("/home").and()
                .authorizeRequests()
                .mvcMatchers("/favorites").authenticated()
                .mvcMatchers("/login").permitAll()
                .mvcMatchers("/register").permitAll()
                .and().formLogin().loginPage("/login")
                .defaultSuccessUrl("/home").failureUrl("/login?error=true").permitAll()
                .and().logout().logoutSuccessUrl("/login?logout=true").invalidateHttpSession(true).permitAll()
                .and().httpBasic()
                .and().securityContext((securityContext) ->
                        securityContext.requireExplicitSave(false)
                );
        return http.build();
    }
}
