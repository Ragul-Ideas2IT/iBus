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

    private final AccountDetailService accountDetailService;

    @Autowired
    public SecurityConfiguration(AccountDetailService accountDetailService) {
        this.accountDetailService = accountDetailService;
    }

    /**
     * A function that is used to create a security filter chain.
     *
     * @param httpSecurity This is the object that is used to configure the security of the application.
     * @return A SecurityFilterChain
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity)
            throws Exception {
        httpSecurity.csrf().disable().authorizeRequests()
                .antMatchers("/api/v1/accounts").permitAll()
                .antMatchers(HttpMethod.POST, "/api/v1/users").hasAuthority("user")
                .antMatchers(HttpMethod.GET, "/api/v1/users").hasAuthority("user")
                .antMatchers(HttpMethod.PUT, "/api/v1/users/{id}").hasAuthority("user")
                .antMatchers(HttpMethod.DELETE, "/api/v1/users/{id}").hasAuthority("user")
                .antMatchers("/api/v1/operators/**").hasAuthority("operator")
                .antMatchers("/api/v1/buses/**").hasAuthority("operator")
                .antMatchers(HttpMethod.POST, "/api/v1/bookings/**").hasAuthority("user")
                .antMatchers(HttpMethod.GET, "/api/v1/bookings/users/**").hasAuthority("user")
                .antMatchers(HttpMethod.GET, "/api/v1/bookings/{id}")
                        .hasAnyAuthority("user", "operator")
                .antMatchers(HttpMethod.DELETE, "/api/v1/bookings/**")
                        .hasAnyAuthority("user", "operator")
                .antMatchers(HttpMethod.GET, "/api/v1/bookings/buses/**").hasAuthority("operator")
                .antMatchers("/api/v1/payments/**").hasAuthority("user")
                .and().authorizeRequests().anyRequest().authenticated()
                .and().httpBasic();
        return httpSecurity.build();
    }

    /**
     * > This function is used to create an AuthenticationManager bean that is used to authenticate the user credentials
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
     * This function is called by the Spring Security framework to configure the authentication manager.
     * The authentication manager is responsible for authenticating users. The authentication manager is configured
     * to use the account detail
     * service and the password encoder.
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
