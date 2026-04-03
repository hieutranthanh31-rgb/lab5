package com.slide1.Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.slide1.Entity.User;
import com.slide1.Services.UserService;

@WebServlet("/users")
public class UserController extends HttpServlet {

    private UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        // Load all users for the table
        List<User> users = userService.getAllUsers();
        request.setAttribute("users", users);

        // If an editId parameter is present, load that user to pre-fill the form
        String editId = request.getParameter("editId");
        if (editId != null && !editId.trim().isEmpty()) {
            User editUser = userService.findUserById(editId);
            if (editUser != null) {
                request.setAttribute("editUser", editUser);
            }
        }

        request.getRequestDispatcher("/WEB-INF/views/users.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        String id = request.getParameter("id");
        String password = request.getParameter("password");
        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        boolean admin = "admin".equals(request.getParameter("role"));

        if ("create".equals(action)) {
            User user = new User();
            user.setId(id);
            user.setPassword(password);
            user.setFullName(fullName);
            user.setEmail(email);
            user.setAdmin(admin);
            userService.createUser(user);

        } else if ("update".equals(action)) {
            User user = userService.findUserById(id);
            if (user != null) {
                user.setPassword(password);
                user.setFullName(fullName);
                user.setEmail(email);
                user.setAdmin(admin);
                userService.updateUser(user);
            }

        } else if ("delete".equals(action)) {
            User user = userService.findUserById(id);
            if (user != null) {
                userService.deleteUser(user);
            }
        }

        response.sendRedirect(request.getContextPath() + "/users");
    }
}
