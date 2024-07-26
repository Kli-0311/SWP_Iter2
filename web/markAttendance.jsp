<%-- 
    Document   : markAttendance
    Created on : Jul 26, 2024, 8:36:34 PM
    Author     : ADMIN
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Intern Attendance</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f2f2f2;
                margin: 0;
                padding: 0;
            }
            .container {
                width: 80%;
                margin: 70px auto;
                background-color: #fff;
                padding: 20px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                border-radius: 5px;
            }
            h2 {
                color: #336699;
                margin-top: 0;
            }
            table {
                width: 100%;
                border-collapse: collapse;
                margin-top: 10px;
            }
            table, th, td {
                border: 1px solid #ccc;
            }
            th, td {
                padding: 10px;
                text-align: left;
            }
            th {
                background-color: #f2f2f2;
            }
            tr:nth-child(even) {
                background-color: #f9f9f9;
            }
            tr:hover {
                background-color: #e0e0e0;
            }
            .submit-btn {
                margin-top: 20px;
                padding: 10px 20px;
                background-color: #336699;
                color: #fff;
                border: none;
                border-radius: 5px;
                cursor: pointer;
            }
            .submit-btn:hover {
                background-color: #285a8b;
            }
        </style>
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
            <div class="container">
                <h2>Intern Attendance</h2>
                <a style="margin-left: 1100px" href="attendanceAndSchedule">Back to List</a>


                <form action="markAttendanceServlet" method="POST">
                    <table>
                        <thead>
                            <tr>
                                <th>Intern ID</th>
                                <th>User ID</th>
                                <th>Full Name</th>
                                <th>Position</th>
                                <th>Attendance Status</th>
                            </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="intern" items="${internList}">
                            <tr>
                                <td>
                                    ${intern.intern_id}
                                    <input type="hidden" name="intern_id" value="${intern.intern_id}">
                                </td>
                                <td>${intern.user_id}</td>
                                <td>${intern.full_name}</td>
                                <td>${positions[intern.position_code]}</td>
                                <td>
                                    <label>
                                        <input type="radio" name="status_${intern.intern_id}" value="Present" required> Present
                                    </label>
                                    <label>
                                        <input type="radio" name="status_${intern.intern_id}" value=Absent"> Absent
                                    </label>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <button type="submit" class="submit-btn">Submit Attendance for ${current_time}</button>
            </form>
            <c:if test="${not empty message}">
                    <h5 style="margin-top: 20px;"><c:out value="${message}" /></h5>
            </c:if> 
        </div>
    </body>
</html>
