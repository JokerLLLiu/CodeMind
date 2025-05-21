package com.software.config;

import com.software.filter.TokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

@Configuration
public class FilterConfig {

    @Autowired
    private TokenFilter tokenFilter;

    /**
     * 注册过滤器
     * @return
     */
    public FilterRegistrationBean<TokenFilter> addFilter() {
        FilterRegistrationBean<TokenFilter> bean = new FilterRegistrationBean<>();
        bean.setFilter(tokenFilter);
        bean.addUrlPatterns("/*"); // 设置拦截路径
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE); // 可设置优先级
        return bean;
    }
}