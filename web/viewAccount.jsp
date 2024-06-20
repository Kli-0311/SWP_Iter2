<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>User Account Management</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <style>
        .sort-icon {
            cursor: pointer;
            margin-left: 5px;
            color: #007bff; /* Màu mặc định */
            transition: color 0.3s ease; /* Hiệu ứng chuyển động */
        }
        .sort-icon:hover {
            color: #0056b3; /* Màu khi di chuột vào */
        }
        .sort-icon.active {
            color: #fff !important; /* Màu khi active */
        }

        .view-list {
            font-size: 18px;
            color: #F3A99C; /* Màu cho chữ View List */
            transition: color 0.3s ease; /* Hiệu ứng chuyển động */
        }

        .view-list:hover {
            color: #FB745C; /* Màu khi di chuột vào, ở đây là trắng */
            text-decoration: none; /* Bỏ gạch chân */
        }
        
        .btn-white {
            background-color: #ffffff; /* Màu nền trắng */
            color: #007bff; /* Màu chữ xanh dương */
            border-color: #007bff; /* Màu viền xanh dương */
        }

        .btn-white:hover {
            background-color: #f8f9fa; /* Màu nền khi di chuột vào */
            color: #0056b3; /* Màu chữ khi di chuột vào */
            border-color: #0056b3; /* Màu viền khi di chuột vào */
        }
    </style>
</head>
<body>
    <jsp:include page="header.jsp"></jsp:include>

    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <h1>List of User Accounts</h1>
                <table class="table table-bordered table-hover">
                    <thead class="thead-dark">
                        <tr>
                            <th>User ID</th>
                            <th>Username</th>
                            <th>Password</th>
                            <th>Active</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${listAccount}" var="account">
                            <tr>
                                <td>${account.user_id}</td>
                                <td>${account.username}</td>
                                <td>${account.password}</td>
                                <td>${account.is_active}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

    <jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
