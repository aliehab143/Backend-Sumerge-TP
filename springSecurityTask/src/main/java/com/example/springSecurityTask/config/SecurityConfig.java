package com.example.springSecurityTask.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity  
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;
    private final HeaderValidationFilter headerValidationFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        
        http
            .csrf(csrf -> csrf.disable())
            .headers(headers -> headers.frameOptions().sameOrigin()) 
            
             .authorizeHttpRequests(auth -> auth
                .requestMatchers(HttpMethod.GET, "/courses").permitAll()
                .requestMatchers(HttpMethod.GET, "/courses/{id}").permitAll()
                .requestMatchers(HttpMethod.GET, "/courses/author").permitAll()
                
                .requestMatchers(HttpMethod.POST, "/courses").hasAuthority("USER")
                .requestMatchers(HttpMethod.PUT, "/courses/{id}").hasAuthority("USER")
                .requestMatchers(HttpMethod.DELETE, "/courses/{id}").hasAuthority("USER")
                
                .requestMatchers(
                    "/swagger-ui/**",
                    "/swagger-ui.html",
                    "/v3/api-docs/**",
                    "/api-docs/**",
                    "/swagger-resources/**",
                    "/webjars/**",
                    "/h2-console/**"
                ).permitAll()
                
                .anyRequest().authenticated()
            )
            
        
            .httpBasic(basic -> basic
                .realmName("Course Management API") 
            )
            
          
            .userDetailsService(userDetailsService)
        
            .addFilterBefore(headerValidationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance(); 
    }
}
