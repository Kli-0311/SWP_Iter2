<%-- 
    Document   : AttendanceManagement
    Created on : Jul 10, 2024, 11:05:14 AM
    Author     : ADMIN
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f2f2f2;
                margin: 0;
                padding: 0;
            }
            .container {
                width: 80%;
                margin: 20px auto;
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
            .filter-select {
                margin-bottom: 10px;
            }
            .report-link {
                color: #336699;
                text-decoration: none;
            }
            .report-link:hover {
                text-decoration: underline;
            }
        </style>
        <script>
            function filterReports() {
                var selectedProject = document.getElementById("projectFilter").value;
                window.location.href = "attendanceAndSchedule?selectedProject=" + selectedProject;
            }
        </script>
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
            <div class="container">
                <h2>Intern Attendance</h2>

                <div class="filter-select">
                    <label for="projectFilter"> Project :</label>
                    <select id="projectFilter" onchange="filterReports()">
                        <option value="all" ${selectedProject == null || selectedProject.isEmpty() || selectedProject.equals("all") ? 'selected' : ''}>All Projects</option>
                    <c:forEach var="project" items="${projects}">
                        <option value="${project.projectCode}" ${selectedProject != null && selectedProject.equals(project.projectCode) ? 'selected' : ''}>${project.projectName}</option>
                    </c:forEach>
                </select>
            </div>

            <table>
                <thead>
                    <tr>
                        <th>Intern ID</th>
                        <th>User ID</th>
                        <th>Full Name</th>
                        <th>Position Code</th>
                        <th>Present Days</th>
                        <th>Details</th>

                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="intern" items="${internList}">
                        <tr>
                            <td>${intern.intern_id}</td>
                            <td>${intern.user_id}</td>
                            <td>${intern.full_name}</td>
                            <td>${positions[intern.position_code]}</td>
                            <td>${present_days[intern.user_id]}/${intern.calculateWorkingDays()}</td>
                            <td><a href="viewDetailAttendance?user_id=${intern.user_id}">Detail</a></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>
