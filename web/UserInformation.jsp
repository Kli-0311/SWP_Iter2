<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>User Information</title>
    <link rel="shortcut icon" type="image/png" href="img/anh.png">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" 
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
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
        .container {
            display: flex;
            justify-content: center;
            align-items: center;
            margin-bottom: 20px;
        }
        .form-container {
            flex-grow: 1;
            display: flex;
            justify-content: center;
        }
        .sidebar {
            display: flex;
            flex-direction: column;
            margin-left: 20px; /* Tạo khoảng cách giữa thanh tìm kiếm và thanh bên */
        }
        .admin-button {
            display: inline-block;
            padding: 10px 20px;
            font-size: 16px;
            color: white;
            background-color: #4dabf5; /* Màu nền của nút */
            text-decoration: none;
            border-radius: 5px; /* Bo tròn góc nút */
            transition: background-color 0.3s;
            margin-bottom: 10px; /* Tạo khoảng cách giữa các nút */
        }
        .admin-button:hover {
            background-color: #90caf9; /* Màu nền khi di chuột qua */
        }
        .hr-button {
            display: inline-block;
            padding: 10px 20px;
            font-size: 16px;
            color: white;
            background-color: #4caf50; /* Màu nền của nút */
            text-decoration: none;
            border-radius: 5px; /* Bo tròn góc nút */
            transition: background-color 0.3s;
            margin-bottom: 10px; /* Tạo khoảng cách giữa các nút */
        }
        .hr-button:hover {
            background-color: #81c784; /* Màu nền khi di chuột qua */
        }
        form {
            display: flex;
            width: 100%;
            max-width: 500px;
        }
        input[type="text"] {
            flex: 1;
            padding: 10px 15px;
            border: 1px solid #ccc;
            border-radius: 25px 0 0 25px;
            border-right: none;
            outline: none;
        }
        button {
            width: 50px; /* Adjust as necessary */
            border: 1px solid #4caf50;
            background-color: #f18d6c; /* Green background color to match image */
            border-radius: 0 25px 25px 0; /* Rounded corners for the button */
            cursor: pointer;
            transition: background-color 0.3s;
            outline: none;
            display: flex;
            align-items: center;
            justify-content: center;
        }
        button:hover {
            background-color: #388e3c; /* Slightly darker green on hover */
        }
        button i {
            color: white; /* Color of the icon */
            font-size: 1.2em; /* Adjust the size of the icon */
        }
        table {
            width: 90%;
            margin: 20px auto; /* Tạo khoảng cách phía trên và dưới */
            border-collapse: collapse;
            background-color: white;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        table, th, td {
            border: 1px solid #ddd;
        }
        th, td {
            padding: 15px;
            text-align: left;
        }
        th {
            background-color: #f18d6c;
            color: white;
        }
        td img {
            border-radius: 50%;
        }
        tr {
            border-bottom: 1px solid #ddd;
        }
        .project-container {
            display: flex;
            align-items: center;
            justify-content: space-between;
        }
        .apply-button {
            display: inline-block;
            padding: 5px 10px;
            font-size: 12px;
            color: black;
            background-color: #f5f5dc; /* Beige color */
            text-decoration: none;
            border-radius: 3px;
            margin-left: 10px; /* Space between project name and button */
            transition: background-color 0.3s;
        }
        .apply-button:hover {
            background-color: #e5e5c0; /* Darker beige on hover */
        }
    </style>
</head>
<body>
    <jsp:include page="header.jsp"></jsp:include>
    <h1>User Information</h1>
    <div class="container">
        <div class="form-container" style="margin-left: 180px;">
            <form method="Get" action="SearchMentorInformation">
                <input value="${search}" type="text" name="txt" placeholder="Search By Name Mentor">
                <button type="submit"><i class="fas fa-search"></i></button>
            </form>
        </div>
        <div class="sidebar">
            <a href="AdminsInformation" class="admin-button">Admin Information</a>
            <a href="HRInformation" class="hr-button">HR Information</a>
        </div>
    </div>
    
    <table>
        <thead>
            <tr>
                <th>Mentor ID</th>
                <th>Full Name</th>
                <th>Avatar</th>
                <th>Date of Birth</th>
                <th>Gender</th>
                <th>Phone</th>
                <th>Active</th>
                <th>Projects Name</th>
                <th>Project Time</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="mentor" items="${mentors}">
                <tr>
                    <td>${mentor.id}</td>
                    <td>${mentor.fullName}</td>
                    <td><img src="img/${mentor.avatar}" alt="Avatar" style="width:50px;height:50px;"></td>
                    <td>${mentor.dob}</td>
                    <td>${mentor.gender}</td>
                    <td>${mentor.phone}</td>
                    <td>${mentor.active}</td>
                    <td>
                        <c:forEach var="project" items="${fn:split(mentor.nameProjects, ',')}">
                            <div class="project-container">
                                <a href="IntersInformation?projectCode=${fn:trim(project)}">${fn:trim(project)}</a>
                                <a href="CandidatesApplyInformation?projectName=${fn:trim(project)}" class="apply-button">Candidate Apply</a>
                            </div>
                        </c:forEach>
                    </td>
                    <td>
                        <c:forEach var="time" items="${fn:split(mentor.time, ',')}">
                            <div>${time}</div>
                        </c:forEach>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
