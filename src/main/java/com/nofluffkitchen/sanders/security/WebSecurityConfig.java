package com.nofluffkitchen.sanders.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public static BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Qualifier
    private UserDetailsService userDetailService;

    @Autowired
    public void setUserDetailService(UserDetailServiceImpl userDetailService){
        this.userDetailService = userDetailService;

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .and()
                .authorizeRequests()
                .antMatchers("/recipe/**").hasRole("ADMIN")
                .and()
                .authorizeRequests()
                .antMatchers("/recipe/**, /display-recipe/**").hasAnyRole("ADMIN", "USER")
                .and()
                .formLogin().loginPage("/login").defaultSuccessUrl("/", true).permitAll()
                .usernameParameter("email")
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
            auth
                    .userDetailsService(userDetailService)
                    .passwordEncoder(passwordEncoder());


    }
}
