package com.slide1.Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.slide1.Dao.UserDao;
import com.slide1.Entity.User;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/views/login.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("username");
        String pass = request.getParameter("password");
        
        UserDao dao = new UserDao();
        try {
            User user = dao.findById(id); // Bước 1: Tìm user
            if (user == null) {
                request.setAttribute("message", "Sai tên đăng nhập!");
            } else if (!user.getPassword().equals(pass)) { // Bước 2: So sánh pass
                request.setAttribute("message", "Sai mật khẩu!");
            } else {
                // Bước 3: Lưu vào session nếu đúng
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                request.setAttribute("message", "Đăng nhập thành công!");
            }
        } catch (Exception e) {
            request.setAttribute("message", "Lỗi kết nối CSDL!");
        }
        request.getRequestDispatcher("/views/login.jsp").forward(request, response);
    }
}
