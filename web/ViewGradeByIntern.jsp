<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Mentor Details</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f8f9fa;
        }
        .container {
            width: 95%;
            margin: 40px auto;
            overflow: hidden;
        }
        header {
            background: #333;
            color: #fff;
            padding: 20px 0;
            min-height: 70px;
            border-bottom: #0779e4 3px solid;
        }
        header a {
            color: #fff;
            text-decoration: none;
            text-transform: uppercase;
            font-size: 16px;
        }
        header ul {
            padding: 0;
            list-style: none;
        }
        header li {
            display: inline;
            padding: 0 20px;
        }
        header #branding {
            float: left;
        }
        header #branding h1 {
            margin: 0;
        }
        header nav {
            float: right;
            margin-top: 10px;
        }
        header .highlight, header .current a {
            color: #0779e4;
            font-weight: bold;
        }
        header a:hover {
            color: #cccccc;
            font-weight: bold;
        }
        .content {
            padding: 20px;
            background: #fff;
            margin-top: 20px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            border-radius: 10px;
        }
        table {
            width: 100%;
            margin-top: 20px;
            border-collapse: collapse;
            background-color: #fff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            border-radius: 10px;
            overflow: hidden;
        }
        table, th, td {
            border: 1px solid #ddd;
        }
        th, td {
            padding: 10px;
            text-align: left;
            font-size: 14px;
        }
        th {
            background-color: #4CAF50;
            color: white;
            text-transform: uppercase;
            letter-spacing: 1px;
            font-size: 10px;
        }
        tr:nth-child(even) {
            background-color: #f9f9f9;
        }
        tr:hover {
            background-color: #f1f1f1;
        }
        td {
            text-align: center;
            padding: 10px;
            white-space: nowrap;
            overflow: hidden;
            text-overflow: ellipsis;
        }
        h2 {
            color: #333;
            text-align: center;
            margin-bottom: 20px;
            font-size: 24px;
        }
        .container h1 {
            text-align: center;
            margin-bottom: 30px;
            color: #4CAF50;
        }
        .container p {
            text-align: center;
            font-size: 18px;
            color: #777;
        }
    </style>
</head>
<body>
    <jsp:include page="header.jsp"></jsp:include>
    
    <div class="container">
        <h1>Grade Details</h1>
    <p>View Grade for Interns:${nameintern.internName}</p> 
    
        <div class="table-container">
            <table>
                <tr>
                    <th>Intern ID</th>
                    <th>Mentor Name</th>
                    <th>Type</th>
                    <th>Attitude Score</th>
                    <th>Soft Skills Score</th>
                    <th>Technical Skills Score</th>
                    <th>Total Score</th>
                    <th>Comment</th>
                    <th>Project Name</th>
                    <th>Position Name</th>
                </tr>
                <c:forEach var="evaluation" items="${evaluations}">
                    <tr>
                        <td>${evaluation.internId}</td>
                        <td>${evaluation.mentorName}</td>
                        <td>${evaluation.type}</td>
                        <td>${evaluation.attitudeScore}</td>
                        <td>${evaluation.softSkillsScore}</td>
                        <td>${evaluation.technicalSkillsScore}</td>
                        <td>${evaluation.totalScore}</td>
                        <td>${evaluation.comment}</td>
                        <td>${evaluation.projectName}</td>
                        <td>${evaluation.positionName}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</body>
</html>