package com.pet.springwebshop.security;

import com.pet.springwebshop.entity.Role;
import com.pet.springwebshop.repo.CustomerRepo;
import com.pet.springwebshop.security.filters.AuthenticationFilter;
import com.pet.springwebshop.security.filters.AuthorizationFilter;
import com.pet.springwebshop.security.service.UserDetailsImplService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class WebSecurity {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserDetailsImplService userDetailsImplService;
    private final CustomerRepo customerRepo;

    @Autowired
    public WebSecurity(BCryptPasswordEncoder bCryptPasswordEncoder, UserDetailsImplService userDetailsImplService, CustomerRepo customerRepo) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userDetailsImplService = userDetailsImplService;
        this.customerRepo = customerRepo;
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder
                .userDetailsService(userDetailsImplService)
                .passwordEncoder(bCryptPasswordEncoder);
        AuthenticationManager authenticationManager = authenticationManagerBuilder.build();
        AuthenticationFilter authenticationFilter = new AuthenticationFilter(authenticationManager);
        authenticationFilter.setFilterProcessesUrl(SecurityConstants.LOGIN_URL);
        httpSecurity
                .httpBasic().disable()
                .authenticationManager(authenticationManager)
                .cors(Customizer.withDefaults())
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, SecurityConstants.AUTH_URLs).permitAll()
                .antMatchers(HttpMethod.GET, SecurityConstants.PUBLIC_URLs).permitAll()
                .antMatchers(SecurityConstants.ADMIN_URLs).hasRole(Role.ADMIN.name())
                .and()
                .addFilter(authenticationFilter)
                .addFilterBefore(new AuthorizationFilter(), AuthenticationFilter.class)
                .headers().frameOptions().disable();
        return httpSecurity.getOrBuild();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedOrigins(SecurityConstants.ALLOWED_ORIGINS);
        corsConfiguration.setAllowedMethods(SecurityConstants.ALLOWED_METHODS);
        corsConfiguration.setAllowedHeaders(SecurityConstants.ALLOWED_HEADERS);
        corsConfiguration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }
}