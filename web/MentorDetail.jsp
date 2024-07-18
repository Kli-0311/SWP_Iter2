<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Mentor Detail</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            margin: 0;
            padding: 0;
        }
        .container {
            width: 60%;
            margin: 50px auto;
            background-color: #ffffff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            text-align: center; /* Center-align the entire container */
        }
        h1 {
            text-align: center;
            color: #333;
            margin-bottom: 20px;
            font-size: 2em;
        }
        table {
            margin: 0 auto; /* Center-align the table */
            border-collapse: collapse;
            margin-top: 20px;
            background-color: #ffa500;
            border-radius: 8px;
            overflow: hidden;
            width: 80%;
        }
        th, td {
            padding: 15px; /* Adjusted padding for better spacing */
            text-align: left; /* Align text to the left within the cells */
            border-bottom: 1px solid #ddd;
        }
        th {
            background-color: #f2f2f2;
            color: #333;
            width: 150px; /* Adjusted width for better spacing */
            font-weight: bold;
            font-size: 1.1em;
            
            

        }
        td {
            background-color: #ffffff;
            font-size: 1em;
            padding-left: 3cm;
        }
        
        .image-cell img {
            width: 150px;
            height: 150px;
            object-fit: cover;
            border-radius: 8px;
            display: block;
            margin: 0 auto;
           
            
        }
        .profile-info {
            display: flex;
            flex-direction: column;
            align-items: center;
        }
        .profile-info > div {
            margin-bottom: 15px;
        }
    </style>
</head>
<body>
    <jsp:include page="header.jsp"></jsp:include>
    <div class="container">
        <h1>Mentor Detail</h1>
        <c:forEach var="mentor" items="${mentorsdetail}">
            <table>
                <tr>
                    <th>Full Name</th>
                    <td>${mentor.fullName}</td>
                </tr>
                <tr>
                    <th>Gender</th>
                    <td>${mentor.gender}</td>
                </tr>
                <tr>
                    <th>Image</th>
                    <td class="image-cell">
                        <img src="img/${mentor.avatar}" alt="User Image">
                    </td>
                </tr>
                <tr>
                    <th>Email</th>
                    <td>${mentor.username}</td>
                </tr>
            </table>
        </c:forEach>
    </div>
</body>
</html>
