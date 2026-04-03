<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Đăng nhập - PolyOE</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body class="bg-light">
    <%-- Chèn Header vào đây nếu Hiếu đã làm file header.jsp --%>
    <%-- <jsp:include page="header.jsp" /> --%>

    <div class="container mt-5">
        <div class="row">
            <div class="col-md-4 offset-md-4 shadow p-4 bg-white rounded">
                <h3 class="text-center text-primary mb-4">ĐĂNG NHẬP</h3>
                
                <%-- Hiển thị thông báo lỗi hoặc thành công từ Servlet --%>
                <c:if test="${!empty message}">
                    <div class="alert alert-info py-2 text-center">
                        ${message}
                    </div>
                </c:if>

                <form action="login" method="post">
                    <div class="mb-3">
                        <label class="form-label">Tên đăng nhập:</label>
                        <input name="username" class="form-control" placeholder="Nhập username..." required>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Mật khẩu:</label>
                        <input name="password" type="password" class="form-control" placeholder="Nhập mật khẩu..." required>
                    </div>
                    <button type="submit" class="btn btn-primary w-100 shadow-sm">Đăng nhập</button>
                </form>
                
                <div class="mt-3 text-center">
                    <small><a href="#" class="text-decoration-none">Quên mật khẩu?</a></small>
                </div>
            </div>
        </div>
    </div>
</body>
</html>