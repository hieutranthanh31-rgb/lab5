<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Bài 3 - Tìm kiếm Video</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
</head>
<body class="container mt-4">
    <div class="card">
        <div class="card-header bg-primary text-white">
            <h3 class="mb-0">BÀI 3: TÌM KIẾM VIDEO</h3>
        </div>
        <div class="card-body">
            <form action="${pageContext.request.contextPath}/video/search" method="get" class="form-inline mb-4">
                <label class="mr-2 font-weight-bold">Từ khóa</label>
                <input type="text" name="keyword" value="${param.keyword}" 
                       class="form-control mr-2" placeholder="Nhập tiêu đề video...">
                <button type="submit" class="btn btn-success">Tìm kiếm</button>
            </form>

            <table class="table table-bordered table-hover">
                <thead class="thead-light text-center">
                    <tr>
                        <th>Tiêu đề video</th>
                        <th>Số lượt thích</th>
                        <th>Trạng thái</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="v" items="${videos}">
                        <tr>
                            <td>${v.title}</td>
                            <td class="text-center">${v.favorites.size()}</td> 
                            <td class="text-center">
                                <span class="badge ${v.active ? 'badge-success' : 'badge-danger'}">
                                    ${v.active ? 'Đang hoạt động' : 'Ngưng'}
                                </span>
                            </td>
                        </tr>
                    </c:forEach>
                    <c:if test="${empty videos}">
                        <tr>
                            <td colspan="3" class="text-center text-muted">Không tìm thấy video nào khớp với từ khóa.</td>
                        </tr>
                    </c:if>
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>