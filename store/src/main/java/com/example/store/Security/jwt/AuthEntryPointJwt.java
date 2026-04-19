package com.example.store.Security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.web.AuthenticationEntryPoint;

import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class AuthEntryPointJwt implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException)
            throws IOException {

        String exception = (String) request.getAttribute("exception");
        String message;

        if ("TOKEN_EXPIRED".equals(exception)) {
            System.out.println("*************************");
            System.out.println(exception);
            message = "TOKEN_EXPIRED";
        } else if ("INVALID_TOKEN".equals(exception)) {
            message = "INVALID_TOKEN";
        } else {
            message = "Unauthorized";
        }

        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        Map<String, Object> body = new HashMap<>();
        body.put("status", HttpServletResponse.SC_UNAUTHORIZED);
        body.put("error", "Unauthorized");
        body.put("message", message);
        body.put("path", request.getServletPath());
        body.put("timestamp", java.time.LocalDateTime.now().toString());

        new ObjectMapper().writeValue(response.getOutputStream(), body);
    }
}
