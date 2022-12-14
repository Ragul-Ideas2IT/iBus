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
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity)
            throws Exception {
        httpSecurity.csrf().disable().authorizeRequests()
                .antMatchers("/api/v1/accounts").permitAll()
                .antMatchers(HttpMethod.POST,"/api/v1/users").hasAuthority("user")
                .antMatchers(HttpMethod.GET,"/api/v1/users").hasAuthority("user")
                .antMatchers(HttpMethod.PUT,"/api/v1/users/{id}").hasAuthority("user")
                .antMatchers(HttpMethod.DELETE,"/api/v1/users/{id}").hasAuthority("user")
                .antMatchers("/api/v1/operators/**").hasAuthority("operator")
                .antMatchers("/api/v1/buses/**").hasAuthority("operator")
                .antMatchers(HttpMethod.POST,"/api/v1/bookings/**").hasAuthority("user")
                .antMatchers(HttpMethod.GET,"/api/v1/bookings/users/**").hasAuthority("user")
                .antMatchers(HttpMethod.GET,"/api/v1/bookings/{id}").hasAnyAuthority("user", "operator")
                .antMatchers(HttpMethod.DELETE,"/api/v1/bookings/**").hasAnyAuthority("user", "operator")
                .antMatchers(HttpMethod.GET,"/api/v1/bookings/buses/**").hasAuthority("operator")
                .antMatchers("/api/v1/payments/**").hasAuthority("user")
                .and().authorizeRequests().anyRequest().authenticated()
                .and().httpBasic();
        return httpSecurity.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(accountDetailService).passwordEncoder(passwordEncoder());
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
