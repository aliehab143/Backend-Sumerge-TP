package com.example.springSecurityTask.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class HeaderValidationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
            HttpServletRequest request, 
            HttpServletResponse response, 
            FilterChain filterChain) throws ServletException, IOException {
        
        String requestPath = request.getRequestURI();
        
        if (requestPath.startsWith("/swagger-ui") || 
            requestPath.startsWith("/v3/api-docs") || 
            requestPath.startsWith("/api-docs") || 
            requestPath.startsWith("/swagger-resources") || 
            requestPath.startsWith("/webjars") ||
            requestPath.startsWith("/h2-console")) {
            
            filterChain.doFilter(request, response);
            return;
        }
        
        String validationHeader = request.getHeader("x-validation-report");
        
        if (validationHeader == null || !validationHeader.equals("true")) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Missing or invalid x-validation-report header. Must be 'true'.");
            return;
        }
        
        filterChain.doFilter(request, response);
    }
}
