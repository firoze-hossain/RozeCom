package com.roze.admin.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public UserDetailsService userDetailsService() {
        return new RozeEcomUserDetailsService();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder auth = http.getSharedObject(AuthenticationManagerBuilder.class);
        auth.authenticationProvider(authenticationProvider());
        return auth.build();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .requestMatchers("/states/list_by_country/**").hasAnyAuthority("Admin", "Salesperson")
                .requestMatchers("/users/**", "/settings/**", "/countries/**", "/states/**").hasAuthority("Admin")
                .requestMatchers("/categories/**", "/brands/**").hasAnyAuthority("Admin", "Editor")
                .requestMatchers("/products/new", "/products/delete/**").hasAnyAuthority("Admin", "Editor")
                .requestMatchers("/products/edit/**", "/products/save", "/products/check_unique")
                .hasAnyAuthority("Admin", "Editor", "Salesperson")
                .requestMatchers("/products", "/products/", "/products/detail/**", "/products/page/**")
                .hasAnyAuthority("Admin", "Editor", "Salesperson", "Shipper")
                .requestMatchers("/products/**").hasAnyAuthority("Admin", "Editor")
                .requestMatchers("/orders", "/orders/", "/orders/page/**", "/orders/detail/**")
                .hasAnyAuthority("Admin", "Salesperson", "Shipper")
                .requestMatchers("/products/detail/**", "/customers/detail/**")
                .hasAnyAuthority("Admin", "Editor", "Salesperson", "Assistant")
                .requestMatchers("/customers/**", "/orders/**", "/get_shipping_cost", "/reports/**")
                .hasAnyAuthority("Admin", "Salesperson")
                .requestMatchers("/orders_shipper/update/**").hasAuthority("Shipper")
                .requestMatchers("/reviews/**").hasAnyAuthority("Admin", "Assistant")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .usernameParameter("email")
                .permitAll()
                .and()
                .logout()
                .permitAll()
                .and()
                .rememberMe()
                .key("AbcDefgHijKlmnOpqrs_1234567890")
                .tokenValiditySeconds(7 * 24 * 60 * 60)
                .and()
                .headers().frameOptions().sameOrigin();

        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/images/**", "/js/**", "/webjars/**");
    }


}
