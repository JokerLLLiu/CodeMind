package com.software.filter;

import com.software.context.BaseContext;
import com.software.properties.JwtProperties;
import com.software.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;

import java.io.IOException;

@Component
@Slf4j
public class TokenFilter implements Filter {

    @Autowired
    private JwtProperties jwtProperties;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String requetURI = request.getRequestURI();

        if(requetURI.contains("/login") || requetURI.contains("/register")){
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }

        String token = request.getHeader("token");
        if(token == null || token.isEmpty()){
            response.setStatus(401);
            return;
        }

        try{
            Claims claims = JwtUtils.parseJWT(jwtProperties.getSecretKey(),token);
            BaseContext.setCurrentId(Long.valueOf(claims.get("id").toString()));
        } catch (Exception e){
            e.printStackTrace();
            response.setStatus(401);
            return;
        }

        filterChain.doFilter(servletRequest,servletResponse);

        BaseContext.remove();
    }
}
