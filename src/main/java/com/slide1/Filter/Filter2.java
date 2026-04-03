package com.slide1.Filter;

import java.io.IOException;
import javax.servlet.*;

public class Filter2 implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        // Lấy thuộc tính "hello" từ Filter1 truyền sang và in ra Console
        Object message = request.getAttribute("hello");
        System.out.println("Filter 2 nhận được: " + message);
        
        chain.doFilter(request, response);
    }
    @Override public void init(FilterConfig fConfig) throws ServletException {}
    @Override public void destroy() {}
}