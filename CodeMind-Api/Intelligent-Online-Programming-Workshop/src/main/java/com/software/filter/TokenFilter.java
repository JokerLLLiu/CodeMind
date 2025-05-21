package com.software.filter;

import com.software.utils.CurrentHolder;
import com.software.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@WebFilter(urlPatterns = "/*")
@Slf4j
public class TokenFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String requestURI = request.getRequestURI();
        if(requestURI.contains("/login")){
            filterChain.doFilter(request,response);
            return;
        }

        String token = request.getHeader("token");
        if(token == null || token.isEmpty()){
            response.setStatus(401);
            return;
        }

        try{
            Claims claims = JwtUtils.parseJWT(token);
            CurrentHolder.setCurrentId(Integer.valueOf(claims.get("id").toString()));
        } catch (Exception e){
            response.setStatus(401);
            return;
        }

        filterChain.doFilter(request,response);

        CurrentHolder.remove();

    }
}
