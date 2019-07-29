package com.tm470.WoodMacPark.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



public class AdminSecurityConfiguration { //extends WebSecurityConfigurerAdapter{

//    String[] staticResources  =  {
//            "/css/**",
//            "/images/**",
//            "/fonts/**",
//            "/scripts/**",
//    };
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//        http
//                .authorizeRequests()
//                .antMatchers("/resources/staticResources").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/login")
//                .permitAll()
//                .and()
//                .logout()
//                .permitAll();
//    }
//
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("user")
//                .password("password")
//                .roles("USER")
//                .and()
//                .withUser("admin")
//                .password("password1")
//                .roles("ADMIN");
//    }
//
//    @Override
//    @Bean
//    public UserDetailsService userDetailsServiceBean() throws Exception {
//        return super.userDetailsServiceBean();
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return NoOpPasswordEncoder.getInstance();
//    }


}