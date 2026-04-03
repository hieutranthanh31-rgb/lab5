package com.slide1.Controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.slide1.Dao.VideoDAO;

@WebServlet("/report-share")
public class ReportServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        VideoDAO dao = new VideoDAO();
        // Gọi hàm report đã viết trong VideoDAO
        List<Object[]> reportData = dao.reportSharedVideo();
        
        // Đẩy dữ liệu sang JSP với tên biến là "items"
        request.setAttribute("items", reportData);
        
        // Trỏ đúng đường dẫn file JSP của Hiếu
        request.getRequestDispatcher("/views/report-share.jsp").forward(request, response);
    }
}