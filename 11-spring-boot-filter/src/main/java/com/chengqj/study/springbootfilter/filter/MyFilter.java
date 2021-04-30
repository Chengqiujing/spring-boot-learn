package com.chengqj.study.springbootfilter.filter;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter("/*")
public class MyFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("request before in servlet ...");

        filterChain.doFilter(servletRequest,servletResponse);

        System.out.println("request after servlet");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("container init");
    }

    @Override
    public void destroy() {
        System.out.println("container destroy");
    }
}
