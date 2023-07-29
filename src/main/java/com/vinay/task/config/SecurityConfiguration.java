package com.vinay.task.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@Configuration
public class SecurityConfiguration extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    private final DataSource dataSource;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public SecurityConfiguration(BCryptPasswordEncoder bCryptPasswordEncoder, DataSource dataSource) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.dataSource = dataSource;
    }


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .jdbcAuthentication()
                .usersByUsernameQuery("select email as username, password as password, true from user where email=?")
                .authoritiesByUsernameQuery("select u.email as username, r.role as authority from user u inner join user_role ur on(u.user_id=ur.user_id) inner join role r on(ur.role_id=r.role_id) where u.email=?")
                .dataSource(dataSource)
                .passwordEncoder(bCryptPasswordEncoder);
    }

//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeUrls()
//                .antMatchers("/register").permitAll()
//                .antMatchers("/", "/login", "/about", "/css/**", "/webjars/**").permitAll()
//                .antMatchers("/profile/**", "/tasks/**", "/task/**", "/users", "/user/**", "/h2-console/**").hasAnyRole("USER", "ADMIN")
//                .antMatchers("/assignment/**").hasAnyRole("ADMIN")
//                .and()
//                .formLogin()
//                .loginPage("/login")
//                .permitAll()
//                .defaultSuccessUrl("/profile")
//                .and()
//                .logout()
//                .logoutSuccessUrl("/login");
//    }
    
    
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/register", "/", "/login", "/about", "/css/**", "/webjars/**").permitAll()
                .antMatcher("/profile/**").hasAnyRole("USER", "ADMIN")
                .antMatcher("/tasks/**").hasAnyRole("USER", "ADMIN")
                .antMatcher("/task/**").hasAnyRole("USER", "ADMIN")
                .antMatcher("/users").hasAnyRole("USER", "ADMIN")
                .antMatcher("/user/**").hasAnyRole("USER", "ADMIN")
                .antMatcher("/h2-console/**").hasAnyRole("USER", "ADMIN")
                .antMatcher("/assignment/**").hasAnyRole("ADMIN")
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .defaultSuccessUrl("/profile")
                .and()
                .logout()
                .logoutSuccessUrl("/login");
    }
}
