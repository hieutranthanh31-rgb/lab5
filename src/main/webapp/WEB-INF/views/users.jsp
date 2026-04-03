<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>User Management</title>
    <style>
        * {
            box-sizing: border-box;
            margin: 0;
            padding: 0;
        }

        body {
            font-family: Arial, sans-serif;
            font-size: 13px;
            background-color: #d0d8e8;
            display: flex;
            justify-content: center;
            padding: 30px;
        }

        .panel {
            background-color: #dce5f0;
            border: 2px solid #8fa8c8;
            border-radius: 6px;
            padding: 16px;
            width: 520px;
        }

        /* ── Form ── */
        .form-grid {
            display: grid;
            grid-template-columns: 1fr 1fr;
            gap: 8px 16px;
        }

        .form-group {
            display: flex;
            flex-direction: column;
            gap: 3px;
        }

        .form-group label {
            font-weight: normal;
            color: #222;
        }

        .form-group input[type="text"],
        .form-group input[type="password"],
        .form-group input[type="email"] {
            border: 2px solid #e07820;
            border-radius: 2px;
            padding: 4px 6px;
            font-size: 13px;
            background: #fff;
            outline: none;
            height: 26px;
        }

        .form-group input[type="text"]:focus,
        .form-group input[type="password"]:focus,
        .form-group input[type="email"]:focus {
            border-color: #c05000;
        }

        /* ── Role row ── */
        .role-row {
            margin-top: 8px;
            display: flex;
            align-items: center;
            gap: 16px;
        }

        .role-row span {
            color: #222;
        }

        .role-row label {
            display: flex;
            align-items: center;
            gap: 5px;
            cursor: pointer;
            color: #222;
        }

        .role-row input[type="radio"] {
            accent-color: #e07820;
            width: 14px;
            height: 14px;
        }

        /* ── Buttons ── */
        .btn-row {
            margin-top: 12px;
            display: flex;
            gap: 6px;
        }

        .btn-row button {
            padding: 4px 16px;
            font-size: 13px;
            border: 1px solid #888;
            border-radius: 3px;
            background: linear-gradient(to bottom, #f0f0f0, #d0d0d0);
            cursor: pointer;
            color: #222;
        }

        .btn-row button:hover {
            background: linear-gradient(to bottom, #e0e0e0, #b8b8b8);
        }

        .btn-row button:active {
            background: linear-gradient(to bottom, #c8c8c8, #a8a8a8);
        }

        /* ── Table ── */
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 16px;
        }

        table thead tr {
            background-color: #3a6ea5;
            color: #fff;
        }

        table thead th {
            padding: 6px 8px;
            text-align: left;
            font-weight: bold;
            font-size: 13px;
        }

        table tbody tr:nth-child(odd) {
            background-color: #dce8f5;
        }

        table tbody tr:nth-child(even) {
            background-color: #c8d8ec;
        }

        table tbody td {
            padding: 5px 8px;
            color: #222;
            height: 22px;
        }

        table tbody td a {
            color: #1a4fa0;
            text-decoration: underline;
            cursor: pointer;
        }

        table tbody td a:hover {
            color: #0a2f60;
        }

        /* error / success message */
        .msg-error {
            color: #c00;
            margin-bottom: 8px;
            font-size: 12px;
        }
        .msg-success {
            color: #060;
            margin-bottom: 8px;
            font-size: 12px;
        }
    </style>
</head>
<body>
<div class="panel">

    <c:if test="${not empty param.error}">
        <p class="msg-error">${param.error}</p>
    </c:if>

    <!-- ===== FORM ===== -->
    <form id="userForm" action="${pageContext.request.contextPath}/users" method="post">
        <input type="hidden" name="action" id="actionField" value="create">

        <div class="form-grid">
            <div class="form-group">
                <label for="id">Id:</label>
                <input type="text" id="id" name="id"
                       value="<c:out value='${editUser.id}'/>"
                       <c:if test="${editUser != null}">readonly</c:if>>
            </div>
            <div class="form-group">
                <label for="password">Password:</label>
                <input type="text" id="password" name="password"
                       value="<c:out value='${editUser.password}'/>">
            </div>
            <div class="form-group">
                <label for="fullName">Fullname:</label>
                <input type="text" id="fullName" name="fullName"
                       value="<c:out value='${editUser.fullName}'/>">
            </div>
            <div class="form-group">
                <label for="email">Email Address:</label>
                <input type="text" id="email" name="email"
                       value="<c:out value='${editUser.email}'/>">
            </div>
        </div>

        <div class="role-row">
            <span>Role:</span>
            <label>
                <input type="radio" name="role" value="admin"
                       <c:if test="${editUser != null && editUser.admin}">checked</c:if>>
                Admin
            </label>
            <label>
                <input type="radio" name="role" value="user"
                       <c:if test="${editUser == null || !editUser.admin}">checked</c:if>>
                User
            </label>
        </div>

        <div class="btn-row">
            <button type="submit" onclick="setAction('create')">Create</button>
            <button type="submit" onclick="setAction('update')">Update</button>
            <button type="submit" onclick="setAction('delete')">Delete</button>
            <button type="button" onclick="resetForm()">Reset</button>
        </div>
    </form>

    <!-- ===== TABLE ===== -->
    <table>
        <thead>
            <tr>
                <th>Id</th>
                <th>Fullname</th>
                <th>Email</th>
                <th>Role</th>
                <th></th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="u" items="${users}">
                <tr>
                    <td><c:out value="${u.id}"/></td>
                    <td><c:out value="${u.fullName}"/></td>
                    <td><c:out value="${u.email}"/></td>
                    <td><c:out value="${u.admin ? 'Admin' : 'User'}"/></td>
                    <td>
                        <a href="${pageContext.request.contextPath}/users?editId=<c:out value='${u.id}'/>">Edit</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>

<script>
    function setAction(action) {
        document.getElementById('actionField').value = action;
    }

    function resetForm() {
        var form = document.getElementById('userForm');
        form.reset();
        document.getElementById('actionField').value = 'create';
        // Remove readonly from id field when resetting
        document.getElementById('id').removeAttribute('readonly');
    }
</script>
</body>
</html>
