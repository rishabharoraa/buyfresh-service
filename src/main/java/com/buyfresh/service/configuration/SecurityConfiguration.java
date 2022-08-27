package com.buyfresh.service.configuration;

import com.buyfresh.service.filter.JWTAuthenticationFilter;
import com.buyfresh.service.filter.JWTAuthorizationFilter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfiguration {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration
    ) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .cors().and()
                .authorizeRequests()

                .antMatchers(HttpMethod.POST, "api/v1/vendor").authenticated()
                .and()

                .addFilter(
                        new JWTAuthenticationFilter(
                                this.authenticationManager(
                                        http.getSharedObject(
                                                AuthenticationConfiguration.class
                                        )
                                )
                        )
                )
                .addFilter(
                        new JWTAuthorizationFilter(
                                this.authenticationManager(
                                        http.getSharedObject(
                                                AuthenticationConfiguration.class
                                        )
                                )
                        )
                )

                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        return http.build();
    }
}