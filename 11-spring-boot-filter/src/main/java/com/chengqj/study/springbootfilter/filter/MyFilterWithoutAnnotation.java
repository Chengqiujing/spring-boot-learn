package com.chengqj.study.springbootfilter.filter;


import javax.servlet.*;
import java.io.IOException;

public class MyFilterWithoutAnnotation implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("request before in servlet ... --  without Annotation");

        filterChain.doFilter(servletRequest,servletResponse);

        System.out.println("request after servlet --  without Annotation");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("container init --  without Annotation");
    }

    @Override
    public void destroy() {
        System.out.println("container destroy --  without Annotation");
    }
}
