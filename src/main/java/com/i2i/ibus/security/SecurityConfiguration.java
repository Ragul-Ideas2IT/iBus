package com.i2i.ibus.security;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.function.Supplier;

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
        httpSecurity.csrf().disable().authorizeRequests().antMatchers("/login").permitAll()
                .antMatchers("/**").permitAll().anyRequest().authenticated()
//                .antMatchers(HttpMethod.POST,"**/api/v1/users").hasAnyRole("ADMIN","USER","user")
//                .antMatchers(HttpMethod.GET,"**/api/v1/users").hasAnyRole("ADMIN","USER","user")
//                .antMatchers(HttpMethod.PUT,"**/api/v1/users/{id}").hasRole("user")
//                .antMatchers(HttpMethod.DELETE,"**/api/v1/users/{id}").hasRole("user")
//                .antMatchers(HttpMethod.POST, "**/api/v1/operator").hasRole("operator")
//                .antMatchers(HttpMethod.GET, "**/api/v1/operator").hasRole("operator")
//                .antMatchers(HttpMethod.PUT, "**/api/v1/operator/{id}").hasRole("operator")
//                .antMatchers(HttpMethod.DELETE, "**/api/v1/operator/{id}").hasRole("operator")
//                .antMatchers("/bus/**").hasRole("operator")
//                .antMatchers(HttpMethod.POST,"*/bookings/**").hasRole("user")
//                .antMatchers(HttpMethod.GET,"*/bookings/users/**").hasRole("user")
//                .antMatchers(HttpMethod.GET,"*/bookings/{id}").hasAnyRole()
//                .antMatchers(HttpMethod.DELETE,"*/bookings/**").hasAnyRole()
//                .antMatchers(HttpMethod.GET,"*/bookings/buses/**").hasRole("operator")
//                .antMatchers("*/payments/**").hasRole("user")
                .and().httpBasic();
        return httpSecurity.build();
    }

//    /**
//     * This function creates a DaoAuthenticationProvider object, sets the userDetailsService to the AccountDetailService
//     * object, and sets the passwordEncoder to NoOpPasswordEncoder.getInstance().
//     *
//     * @return A DaoAuthenticationProvider object.
//     */
//    @Bean
//    public DaoAuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//        authProvider.setUserDetailsService(accountDetailService);
//        authProvider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
//        return authProvider;
//    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(accountDetailService).passwordEncoder(passwordEncoder());
    }
    @Bean
    public static PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
