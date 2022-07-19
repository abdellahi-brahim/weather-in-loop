package com.weatherinloop.frontend.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.weatherinloop.frontend.Model.User;
import com.weatherinloop.frontend.Repository.UserRepository;

@Component
public class UserAuthentication implements AuthenticationProvider {
    @Autowired
    private UserRepository userRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        String pwd = authentication.getCredentials().toString();
        User user = userRepository.findByName(name);
        System.out.println(user);
        System.out.println("hello '" + name + "' <- heres the name");
        if (null != user && user.getUserID() > 0 && pwd.equals(user.getPassword())) {
            return new UsernamePasswordAuthenticationToken(user.getName(), pwd);
        } else {
            throw new BadCredentialsException("Invalid credentials!");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}