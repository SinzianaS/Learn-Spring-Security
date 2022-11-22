package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()  //authorize requests
                .antMatchers("/", "index", "/css/*","/js/*")// whitelisted url, no need to add credentials for these
                .permitAll()
                .anyRequest()
                .authenticated()     //any request must be authenticated
                .and()
                .httpBasic();       //enforce authenticity using basic auth
                                    //no way to logout because the username and password
                                    // are sent with every single request
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails annaSmithUser = User.builder()
                .username("annasmith")
                .password("password")
                .roles("STUDENT")   //ROLE_STUDENT
                .build();
        return new InMemoryUserDetailsManager(
                annaSmithUser
        );
    }
}
