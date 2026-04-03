package com.slide1.Filter;

import java.io.IOException;
import javax.servlet.*;

public class Filter1 implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        // Thiết lập thuộc tính "hello"
        request.setAttribute("hello", "Tôi là filter 1");
        
        // Cho phép đi tiếp đến Filter tiếp theo hoặc Servlet
        chain.doFilter(request, response);
    }
    @Override public void init(FilterConfig fConfig) throws ServletException {}
    @Override public void destroy() {}
}