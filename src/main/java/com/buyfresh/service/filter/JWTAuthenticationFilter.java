package com.buyfresh.service.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.buyfresh.service.model.Vendor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import static com.buyfresh.service.constants.SecurityConstants.EXPIRATION_TIME;
import static com.buyfresh.service.constants.SecurityConstants.SECRET;

public class JWTAuthenticationFilter
        extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        setFilterProcessesUrl("/api/v1/auth/vendor");
    }


    @Override
    public Authentication attemptAuthentication(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws AuthenticationException {
        try {
            Vendor vendor = new ObjectMapper()
                    .readValue(request.getInputStream(), Vendor.class);

            UsernamePasswordAuthenticationToken x = new UsernamePasswordAuthenticationToken(
                    vendor.getEmail(),
                    vendor.getPassword(),
                    new ArrayList<>()
            );

            return authenticationManager.authenticate(x);

        } catch (Exception e) {
            try {
                throw new Exception(e);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    @Override
    protected void successfulAuthentication(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain,
            Authentication authResult
    ) throws IOException, ServletException {

        String token = JWT.create()
                .withSubject(
                        ((Vendor) authResult.getPrincipal()).getUsername()
                )
                .withExpiresAt(
                        new Date(System.currentTimeMillis() + EXPIRATION_TIME)
                )
                .sign(Algorithm.HMAC512(SECRET.getBytes()));

        String body = (
                ((Vendor) authResult.getPrincipal()).getUsername()
        ) + " " + token;

        response.getWriter().write(body);
        response.getWriter().flush();
    }

    @Override
    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        super.setAuthenticationManager(authenticationManager);
    }

}
