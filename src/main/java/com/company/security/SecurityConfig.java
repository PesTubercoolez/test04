package com.company.security;

import com.company.service.EntitiesService.UserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserEntityService userService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/main/registration").permitAll()
                .antMatchers("/profile/**").authenticated()
                    .and()
                    .formLogin()
                        .and()
                            .logout().logoutSuccessUrl("/main");
    }

    /*@Bean
    public PasswordEncoder getEncoder() {
        return new BCryptPasswordEncoder();
    }*/

    @Bean
    public DaoAuthenticationProvider getDaoProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        //provider.setPasswordEncoder(getEncoder());
        provider.setUserDetailsService(userService);

        return provider;
    }
}
