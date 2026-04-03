package com.slide1.Listener;




import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class AppListener implements ServletContextListener, HttpSessionListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // 1. Khi Web bắt đầu chạy: Đọc số lượng từ file hoặc gán mặc định là 0
        // (Để đơn giản, mình gán mặc định là 0, Hiếu có thể nâng cấp đọc từ file sau)
        int count = 0; 
        sce.getServletContext().setAttribute("visitors", count);
        System.out.println("Website đã khởi động. Số lượt truy cập ban đầu: " + count);
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        // 2. Khi có 1 người mới truy cập (Session mới được tạo)
        ServletContext context = se.getSession().getServletContext();
        
        // Lấy số hiện tại ra, tăng lên 1, rồi bỏ ngược lại vào Application Scope
        int count = (int) context.getAttribute("visitors");
        count++;
        context.setAttribute("visitors", count);
        
        System.out.println("Có người mới vào! Tổng lượt truy cập: " + count);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // 3. Khi Web dừng (Shutdown): Lưu số count này vào file hoặc CSDL nếu muốn
        int count = (int) sce.getServletContext().getAttribute("visitors");
        System.out.println("Website đang đóng. Đã lưu số lượt truy cập: " + count);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        // Không cần làm gì ở đây cho bài này
    }
}