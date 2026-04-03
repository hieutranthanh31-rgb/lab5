package com.slide1.Controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.slide1.Dao.VideoDAO;
import com.slide1.Entity.Video;

@WebServlet("/video/search")
public class SearchServlet extends HttpServlet {
    VideoDAO dao = new VideoDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // 1. Lấy từ khóa từ ô input (name="keyword")
        String keyword = request.getParameter("keyword");
        
        List<Video> list;
        if (keyword != null && !keyword.isEmpty()) {
            // 2. Nếu có nhập thì tìm theo từ khóa
            list = dao.findByKeyword(keyword);
        } else {
            // 3. Hiển thị tất cả video khi chưa tìm kiếm
            list = dao.findByKeyword(""); 
        }

        // 4. Gửi danh sách tìm được sang JSP
        request.setAttribute("videos", list);
        
        // QUAN TRỌNG: Sửa lại đường dẫn vì 'views' đã nằm trong 'WEB-INF'
        request.getRequestDispatcher("/WEB-INF/views/search.jsp").forward(request, response);
    }
}