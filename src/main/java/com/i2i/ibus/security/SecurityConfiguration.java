package com.i2i.ibus.security;

import com.i2i.ibus.repository.UserRepository;
import com.i2i.ibus.service.impl.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
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


    @Autowired
    private AccountDetailService accountDetailService;


    /**
     * A function that is used to create a security filter chain.
     *
     * @param httpSecurity This is the object that is used to configure the security of the application.
     * @return A SecurityFilterChain
     */
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity)
            throws Exception {
        System.out.println("Security filter chain");
        httpSecurity.csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.GET,"/api/v1/**").permitAll()
//                .antMatchers("*/**").permitAll()//securityMatcher("*/**").authorizeHttpRequests()
                .and().formLogin();
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

//    public AccountServiceImpl accountDetailService() {
//        return new AccountServiceImpl(userRepository) {
//        });
//    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(accountDetailService);
        authProvider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
        return authProvider;
    }
}
