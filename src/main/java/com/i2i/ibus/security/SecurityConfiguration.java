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
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
/**
 * SecurityConfiguration Configuration class for Security
 *
 * @author Ragul_V
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
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity)
            throws Exception {
        System.out.println("Security filter chain");
        httpSecurity.csrf().disable().authorizeHttpRequests().antMatchers("*/**").permitAll()//securityMatcher("*/**").authorizeHttpRequests()
                .and().httpBasic();
//                .requestMatchers("*/user/**").hasRole("user")
//                .requestMatchers("*/operator/**").hasRole("operator")
//                .requestMatchers("/bus/**").hasRole("operator")
//                .requestMatchers(HttpMethod.POST,"*/bookings/**").hasRole("user")
//                .requestMatchers(HttpMethod.GET,"*/bookings/users/**").hasRole("user")
//                .requestMatchers(HttpMethod.GET,"*/bookings/{id}").hasAnyRole()
//                .requestMatchers(HttpMethod.DELETE,"*/bookings/**").hasAnyRole()
//                .requestMatchers(HttpMethod.GET,"*/bookings/buses/**").hasRole("operator")
//                .requestMatchers("*/payments/**").hasRole("user").and().httpBasic();
        return httpSecurity.build();
    }

    /**
     * This function is used to create an AuthenticationManager bean that is used to authenticate users
     *
     * @param authenticationConfiguration This is the bean that is created by the @EnableWebSecurity annotation.
     * @return The AuthenticationManager
     */
    public AuthenticationManager authenticationManagerBean(
            AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }


    /**
     * This function is used to configure the AuthenticationManagerBuilder object, which is used to create an
     * AuthenticationManager object
     *
     * @param authBuilder This is the AuthenticationManagerBuilder object that is used to build the AuthenticationManager.
     */
    public void configure(AuthenticationManagerBuilder authBuilder)
            throws Exception {
        authBuilder.userDetailsService(accountDetailService)
                .passwordEncoder(NoOpPasswordEncoder.getInstance());
    }
}
