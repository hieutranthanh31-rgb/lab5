<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> <%-- Thêm dòng này --%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Báo cáo tổng hợp - PolyOE</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <style>
        .table-hover tbody tr:hover { background-color: #f8f9fa; }
    </style>
</head>
<body class="container mt-4">
    <div class="card shadow">
        <div class="card-header bg-primary text-white">
            <h4 class="mb-0">TỔNG HỢP LƯỢT CHIA SẺ THEO VIDEO</h4>
        </div>
        <div class="card-body">
            <table class="table table-bordered table-hover mt-2">
                <thead class="table-dark text-center">
                    <tr>
                        <th>Tiêu đề Video</th>
                        <th>Số lượt chia sẻ</th>
                        <th>Ngày chia sẻ đầu tiên</th>
                        <th>Ngày chia sẻ cuối cùng</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="item" items="${reportList}">
                        <tr>
                            <td class="fw-bold">${item[0]}</td>
                            <td class="text-center">
                                <span class="badge bg-info text-dark">${item[1]}</span>
                            </td>
                            <td class="text-center">
                                <fmt:formatDate value="${item[2]}" pattern="dd/MM/yyyy"/>
                            </td>
                            <td class="text-center">
                                <fmt:formatDate value="${item[3]}" pattern="dd/MM/yyyy"/>
                            </td>
                        </tr>
                    </c:forEach>
                    
                    <c:if test="${empty reportList}">
                        <tr>
                            <td colspan="4" class="text-center text-muted">Không có dữ liệu chia sẻ nào.</td>
                        </tr>
                    </c:if>
                </tbody>
            </table>
        </div>
        <div class="card-footer text-end">
            <a href="index.jsp" class="btn btn-secondary btn-sm">Quay lại trang chủ</a>
        </div>
    </div>
</body>
</html>