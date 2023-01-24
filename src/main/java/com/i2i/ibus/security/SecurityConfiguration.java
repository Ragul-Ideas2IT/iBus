/*
 * Copyright (c) 2022, Ideas2It and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.i2i.ibus.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * The SecurityConfiguration class is used to configure the security of the application
 * @author Ragul
 * @version 1.0
 * @since Dec 12 2022
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Autowired
    private AccountDetailService accountDetailService;

    /**
     * To create a security filter chain with the URL by using their authorities which is stored in account. Creating
     * user and operator are permitted by all.
     *
     * @param httpSecurity This is the object that is used to configure the security of the application.
     * @return A SecurityFilterChain
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity)
            throws Exception {
        httpSecurity.csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.POST,"/api/v1/users").permitAll()
                .antMatchers(HttpMethod.GET,"/api/v1/users").hasAuthority("ROLE_USER")
                .antMatchers(HttpMethod.GET,"/api/v1/users/{id}").hasAuthority("ROLE_USER")
                .antMatchers(HttpMethod.PUT,"/api/v1/users/{id}").hasAuthority("ROLE_USER")
                .antMatchers(HttpMethod.DELETE,"/api/v1/users/{id}").hasAuthority("ROLE_USER")
                .antMatchers(HttpMethod.POST,"/api/v1/operators").permitAll()
                .antMatchers(HttpMethod.GET,"/api/v1/operators").hasAuthority("ROLE_OPERATOR")
                .antMatchers(HttpMethod.GET,"/api/v1/operators/{id}").hasAuthority("ROLE_OPERATOR")
                .antMatchers(HttpMethod.PUT,"/api/v1/operators/{id}").hasAuthority("ROLE_OPERATOR")
                .antMatchers(HttpMethod.DELETE,"/api/v1/operators/{id}").hasAuthority("ROLE_OPERATOR")
                .antMatchers("/api/v1/buses/**").hasAuthority("ROLE_OPERATOR")
                .antMatchers(HttpMethod.POST,"/api/v1/bookings").hasAuthority("ROLE_USER")
                .antMatchers(HttpMethod.GET,"/api/v1/bookings").hasAuthority("ROLE_OPERATOR")
                .antMatchers(HttpMethod.GET,"/api/v1/bookings/users/**").hasAuthority("ROLE_USER")
                .antMatchers(HttpMethod.GET,"/api/v1/bookings/buses/**").hasAuthority("ROLE_OPERATOR")
                .antMatchers(HttpMethod.GET,"/api/v1/bookings/{id}")
                        .hasAnyAuthority("ROLE_USER", "ROLE_OPERATOR")
                .antMatchers(HttpMethod.DELETE,"/api/v1/bookings/**")
                        .hasAnyAuthority("ROLE_USER", "ROLE_OPERATOR")
                .antMatchers("/api/v1/payments/**").hasAuthority("ROLE_USER")
                .and().authorizeRequests().anyRequest().authenticated()
                .and().httpBasic();
        return httpSecurity.build();
    }

    /**
     * Creates an AuthenticationManager bean that is used to authenticate the user credentials
     *
     * @param authenticationConfiguration This is the bean that is created by the @EnableAuthorizationServer annotation.
     * @return An AuthenticationManager
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    /**
     * Configure the authentication manager. The authentication manager is responsible for authenticating users. The
     * authentication manager is configured to use the account detail service and the password encoder.
     *
     * @param authenticationManagerBuilder This is the object that will be used to configure the authentication manager.
     */
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(accountDetailService).passwordEncoder(passwordEncoder());
    }

    /**
     * The password encoder is a function that takes a password and returns a hash of that password
     *
     * @return A new instance of BCryptPasswordEncoder.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
