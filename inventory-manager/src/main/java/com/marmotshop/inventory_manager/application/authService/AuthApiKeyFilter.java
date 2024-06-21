package com.marmotshop.inventory_manager.application.authService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class AuthApiKeyFilter extends OncePerRequestFilter {

    @Autowired
    @Value("${SECRET_API_KEY}")
    private String SECRET_API_KEY;
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String hasKeyHeader = request.getHeader("API_KEY");

        if(hasKeyHeader == null || !hasKeyHeader.equals(SECRET_API_KEY)){
            response.setStatus(HttpStatus.FORBIDDEN.value());
            response.getWriter().write("GET OUT!");

            return;
        }

        var auth = new ApiKeyAuthenticationToken();
        var newContext = SecurityContextHolder.createEmptyContext();
        newContext.setAuthentication(auth);
        SecurityContextHolder.setContext(newContext);

        filterChain.doFilter(request, response);
    }
}
