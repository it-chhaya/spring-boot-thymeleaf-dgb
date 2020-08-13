package com.chhaya.thymeleaf.configuration;

import com.chhaya.thymeleaf.service.admin.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

    private UserServiceImpl userDetailsService;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public void setCustomAuthenticationSuccessHandler(CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler) {
        this.customAuthenticationSuccessHandler = customAuthenticationSuccessHandler;
    }

    @Autowired
    public void setUserDetailsService(UserServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Autowired
    public void setbCryptPasswordEncoder(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.formLogin()
                .loginPage("/admin/login")
                .successHandler(customAuthenticationSuccessHandler)
                .permitAll();

        http.logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/admin/logout"))
                .logoutSuccessUrl("/admin/login");

        http.authorizeRequests()
                .antMatchers("/admin/categories/**").hasAnyRole("USER", "ADMIN")
                .antMatchers("/admin/articles/**").hasAnyRole("USER", "ADMIN")
                .antMatchers("/admin/users/**").hasRole("ADMIN")
                .anyRequest().authenticated();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {

        /* Ignore on resources */
        web.ignoring().antMatchers("/resources/**");

        /* Ignore on file system */
        web.ignoring().antMatchers("/images/**");

    }

}
