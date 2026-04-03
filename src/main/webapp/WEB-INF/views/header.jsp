<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark shadow-sm mb-4">
    <div class="container">
        <a class="navbar-brand fw-bold" href="index.jsp">
            <span class="text-primary">polyoe</span>
        </a>

        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav me-auto">
                <li class="nav-item">
                    <a class="nav-link active" href="index.jsp">Trang chủ</a>
                </li>
                <li class="nav-item text-nowrap">
                    <a class="nav-link" href="report-share">Báo cáo chia sẻ</a>
                </li>
            </ul>

            <div class="me-4 text-light border-end pe-3 d-none d-md-block">
                <small>Lượt truy cập: <span class="badge bg-danger">${applicationScope.visitors}</span></small>
            </div>

            <div class="d-flex align-items-center text-white">
                <c:choose>
                    <%-- Nếu session đã có user (Đã đăng nhập) --%>
                    <c:when test="${!empty sessionScope.user}">
                        <span class="me-3">
                            Chào, <strong class="text-warning">${sessionScope.user.fullname}</strong>
                        </span>
                        <a href="logout" class="btn btn-outline-light btn-sm">Đăng xuất</a>
                    </c:when>

                    <%-- Nếu chưa đăng nhập --%>
                    <c:otherwise>
                        <a href="login" class="btn btn-primary btn-sm px-4">Đăng nhập</a>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
</nav>