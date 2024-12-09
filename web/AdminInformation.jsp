<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Admin Information</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f9;
        }
        header, footer {
            background-color: #f8a488;
            color: white;
            padding: 10px 20px;
            text-align: center;
        }
        h1 {
            text-align: center;
            margin: 20px 0;
        }
        .form-container {
            display: flex;
            justify-content: center;
            margin-bottom: 20px;
        }
        form {
            display: flex;
        }
        input[type="text"] {
            width: 300px;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        button {
            padding: 10px 20px;
            margin-left: 10px;
            border: 1px solid #f18d6c;
            background-color: #f18d6c;
            color: white;
            border-radius: 4px;
            cursor: pointer;
            transition: background-color 0.3s;
        }
        button:hover {
            background-color: #e5784e;
        }
        .admin-button {
            margin-left: 20px;
        }
        .back-button {
            display: inline-block;
            padding: 10px 20px;
            font-size: 16px;
            color: white;
            background-color: #4dabf5; /* Màu nền của nút */
            text-decoration: none;
            border-radius: 5px; /* Bo tròn góc nút */
            transition: background-color 0.3s;
            margin: 20px auto; /* Căn giữa nút */
            text-align: center; /* Căn giữa văn bản bên trong nút */
            display: block; /* Đảm bảo nút chiếm toàn bộ chiều rộng cần thiết */
            width: fit-content; /* Đảm bảo nút không chiếm quá nhiều chiều rộng */
        }
        .back-button:hover {
            background-color: #90caf9; /* Màu nền khi di chuột qua */
        }
        table {
            width: 90%;
            margin: 0 auto;
            border-collapse: collapse;
            background-color: white;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            margin-bottom: 20px;
        }
        table, th, td {
            border: 1px solid #ddd;
        }
        th, td {
            padding: 15px;
            text-align: left;
        }
        th {
            background-color: #4dabf5;
            color: white;
        }
        td img {
            border-radius: 50%;
        }
        tr {
            border-bottom: 1px solid #ddd;
        }
    </style>
</head>
<body>
    <jsp:include page="header.jsp"></jsp:include>
    <br>
    <h1>Admin Information</h1>
    <br>
    <table>
        <thead>
            <tr>
                <th>User ID</th>
                <th>Full Name</th>
                <th>Avatar</th>
                <th>Date of Birth</th>
                <th>Gender</th>
                <th>Phone</th>
                <th>Active</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="admin" items="${admins}">
                <tr>
                    <td>${admin.userId}</td>
                    <td>${admin.fullName}</td>
                    <td><img src="img/${admin.avatar}" alt="Avatar" style="width:50px;height:50px;"></td>
                    <td>${admin.dob}</td>
                    <td>${admin.gender}</td>
                    <td>${admin.phoneNumber}</td>
                    <td>${admin.isActive}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <a href="UsersInformation" class="back-button">Back</a>
</body>
</html>
