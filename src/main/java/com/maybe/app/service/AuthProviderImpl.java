package com.maybe.app.service;

import com.maybe.app.dao.RestDao;
import com.maybe.app.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


@Component
public class AuthProviderImpl implements AuthenticationProvider {

    private final RestDao restDao;

    @Autowired
    public AuthProviderImpl(RestDao restDao) {
        this.restDao = restDao;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        User user = restDao.getByName(authentication.getName());
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        String password = authentication.getCredentials().toString();
//        String password = encoder.encode(authentication.getCredentials().toString());
        String userPassword = user.getPassword();
        if (!password.equals(userPassword)) {
            throw new BadCredentialsException("Bad credentials");
        }

        return new UsernamePasswordAuthenticationToken(user, password, AuthorityUtils.createAuthorityList("ROLE_USER", "ROLE_ADMIN"));
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}
