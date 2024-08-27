package com.sirkaue.demoparkapi.jwt;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface JwtUserDetailsService extends UserDetailsService {

    JwtToken getTokenAuthenticated(String username);
}
