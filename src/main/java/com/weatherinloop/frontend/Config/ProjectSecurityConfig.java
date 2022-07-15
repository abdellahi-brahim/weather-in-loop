package com.weatherinloop.frontend.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;

@Configuration
public class ProjectSecurityConfig {

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/**");
    }
    
/*     @Override
    protected void configure(HttpSecurity http) throws Exception {

            http.csrf().ignoringAntMatchers("/register").and()
                .authorizeRequests()
                //.mvcMatchers("/dashboard").authenticated()
                .mvcMatchers("/login").permitAll()
                .mvcMatchers("/register").permitAll()
                .and().formLogin().loginPage("/login")
                .defaultSuccessUrl("/home").failureUrl("/login?error=true").permitAll()
                .and().logout().logoutSuccessUrl("/login?logout=true").invalidateHttpSession(true).permitAll()
                .and().httpBasic();


    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
            .withUser("user").password("12345");
    } */

}
