package com.springboot.auth.authorization.rest;

import com.springboot.auth.authorization.entity.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * AuthorizationController
 *
 * @author D.jin
 * @date 2019/10/31
 */

@RestController
@RequestMapping("/api")
public class AuthorizationController {

    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;

    @Autowired
    TokenStore tokenStore;


    @PostMapping("/login")
    public OAuth2AccessToken userLogin(@RequestBody LoginRequest loginRequest){

    /*    UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(), loginRequest.getPassword());
        setDetails(request, authRequest);
        tokenStore.
        authenticationManager.authenticate();*/
        return null;
    }
}
