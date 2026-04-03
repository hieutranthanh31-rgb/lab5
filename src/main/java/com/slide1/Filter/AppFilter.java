package com.slide1.Filter;

import java.io.IOException;
import java.util.Date;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import com.slide1.Dao.LogDao;
import com.slide1.Entity.Log;
import com.slide1.Entity.User;

@WebFilter("/*") // Đánh dấu đây là Filter và cho nó chạy ở TẤT CẢ các trang
public class AppFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest req = (HttpServletRequest) request;
        
        // 1. Ép kiểu tiếng Việt (UTF-8) - Xong yêu cầu 1
        req.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        // 2. Ghi Log vào MariaDB - Xong yêu cầu 2
        try {
            Log log = new Log();
            log.setUri(req.getRequestURI());
            log.setAccessTime(new Date());
            
            // Lấy user từ session (đã làm ở Bài 1)
            User user = (User) req.getSession().getAttribute("user");
            log.setUsername(user != null ? user.getId() : "Khách");

            // Lưu vào bảng logs thông qua DAO
            new LogDao().create(log);
        } catch (Exception e) {
            // Nếu lỗi ghi log thì vẫn cho web chạy tiếp, tránh bị đứng trang
            e.printStackTrace(); 
        }

        // QUAN TRỌNG: Cho phép request đi tiếp. Không có dòng này web sẽ trắng trang!
        chain.doFilter(request, response);
    }

    @Override public void init(FilterConfig fConfig) throws ServletException {}
    @Override public void destroy() {}
}