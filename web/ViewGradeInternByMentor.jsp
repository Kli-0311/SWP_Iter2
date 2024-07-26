<%-- 
    Document   : ViewGradeInternByMentor
    Created on : Jul 16, 2024, 2:38:37 AM
    Author     : admin
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Grade Interns</title>
 <style>
            body {
                font-family: Arial, sans-serif;
                margin: 0;
                padding: 0;
                background-color: #f4f4f9;
                color: #333;
            }

            .container {
                width: 80%;
                margin: 20px auto;
                padding: 20px;
                background: #fff;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                border-radius: 8px;
            }

            h1 {
                text-align: center;
                color: #4CAF50;
            }

            .filter-select {
                margin-bottom: 20px;
                text-align: center;
            }

            .filter-select label {
                font-weight: bold;
                margin-right: 10px;
            }

            .filter-select select {
                padding: 8px;
                border-radius: 5px;
                border: 1px solid #ccc;
            }

            table {
                width: 100%;
                border-collapse: collapse;
                margin-bottom: 20px;
            }

            th, td {
                padding: 12px;
                text-align: left;
                border-bottom: 1px solid #ddd;
            }

            th {
                background-color: #4CAF50;
                color: white;
            }

            tr:nth-child(even) {
                background-color: #f2f2f2;
            }

            tr:hover {
                background-color: #e9e9e9;
            }

            @media (max-width: 768px) {
                .container {
                    width: 95%;
                }

                table, thead, tbody, th, td, tr {
                    display: block;
                }

                th, td {
                    padding: 8px;
                    text-align: right;
                }

                th {
                    text-align: left;
                    background-color: transparent;
                    color: #333;
                }

                td:before {
                    content: attr(data-label);
                    float: left;
                    font-weight: bold;
                }

                tr {
                    margin-bottom: 10px;
                }
            }
        </style>
        <!--        <script>
                    function filterReports() {
                        var selectedProject = document.getElementById("projectFilter").value;
                        window.location.href = "gradeManage?selectedProject=" + selectedProject;
                    }
                </script>-->
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
        <c:if test="${sessionScope.account.role_id == 2}">
            <div class="container-fluid">
                <h1>Grade For Interns</h1>
                <div class="filter-select">
                    <label for="projectFilter">Filter by Project :</label>
                    <select id="projectFilter" onchange="filterReports()">
                        <option value="all" ${selectedProject == null || selectedProject.isEmpty() || selectedProject.equals("all") ? 'selected' : ''}>All Projects</option>
                        <c:forEach var="project" items="${projects}">
                            <option value="${project.projectCode}" ${selectedProject != null && selectedProject.equals(project.projectCode) ? 'selected' : ''}>${project.projectName}</option>
                        </c:forEach>
                    </select>

                    <label for="typeFilter">Filter by Type :</label>
                    <select id="typeFilter" onchange="filterReports()">
                        <option value="all" ${selectedType == null || selectedType.isEmpty() || selectedType.equals("all") ? 'selected' : ''}>All Types</option>
                        <option value="Midterm" ${selectedType != null && selectedType.equals("Midterm") ? 'selected' : ''}>Midterm</option>
                        <option value="Final" ${selectedType != null && selectedType.equals("Final") ? 'selected' : ''}>Final</option>
                    </select>
                </div>
                <div class="btn btn-success mb-3">
                    <a class="text-white" href="<%=request.getContextPath()%>/gradeManage?service=exportExcel&selectedProject=${selectedProject}&selectedType=${selectedType}">Export Excel</a>
                </div>
                <table>
                    <thead>
                        <tr>
                            <th>TT No</th>
                            <th>Roll Number</th>
                            <th>Intern ID</th>
                            <th>Full Name</th>
                            <th>Position Name</th>
                            <th>Project Name</th>
                            <th>Mentor ID</th>
                            <th>Type</th>
                            <th>Comment</th>
                            <th>Attitude Score</th>
                            <th>Soft Skills Score</th>
                            <th>Technical Skills Score</th>
                            <th>Total Score</th>

                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="grade" items="${listGrade}">
                            <tr>
                                <td>${grade.stt}</td>
                                <td>${grade.rollNumber}</td>
                                <td>${grade.internId}</td>
                                <td>${grade.fullName}</td>
                                <td>${grade.positionName}</td>
                                <td>${grade.projectName}</td>
                                <td>${grade.mentorId}</td>
                                <td>${grade.type}</td>
                                <td>${grade.comment}</td>
                                <td>${grade.attitude_score}</td>
                                <td>${grade.soft_skills_score}</td>
                                <td>${grade.technical_skills_score}</td>
                                <td>${grade.total_score}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>

            <script>
                function filterReports() {
                    var project = document.getElementById('projectFilter').value;
                    var type = document.getElementById('typeFilter').value;
                    window.location.href = "<%=request.getContextPath()%>/gradeManage?service=listAll&selectedProject=" + project + "&selectedType=" + type;
                }
            </script>

        </c:if>
        <c:if test="${sessionScope.account.role_id == 4}">
            <div class="container">
                <h1>Grade For Interns</h1>
                <div class="filter-select">
                    <label for="projectFilter">Filter by Type :</label>
                    <select id="projectFilter" onchange="filterReports()">
                        <option value="all" ${selectedProject == null || selectedProject.isEmpty() || selectedProject.equals("all") ? 'selected' : ''}>All Projects</option>
                        <option value="Midterm" ${selectedProject != null && selectedProject.equals("Midterm") ? 'selected' : ''}>Midterm</option>
                        <option value="Final" ${selectedProject != null && selectedProject.equals("Final") ? 'selected' : ''}>Final</option>
                    </select>
                </div>
                <table>
                    <thead>
                        <tr>
                            <th>STT</th>
                            <th>Roll Number</th>
                            <th>Full Name</th>
                            <th>Intern ID</th>
                            <th>Position Code</th>
                            <th>Type</th>
                            <th>Attitude Score</th>
                            <th>Soft Skills Score</th>
                            <th>Technical Skills Score</th>
                            <th>Total Score</th>
                            <th>Comment</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="grade" items="${gradeForIntern}">
                            <tr>
                                <td>${grade.stt}</td>
                                <td>${grade.rollNumber}</td>
                                <td>${grade.fullName}</td>
                                <td>${grade.internId}</td>
                                <td>${grade.positionName}</td>
                                <td>${grade.type}</td>
                                <td>${grade.attitude_score}</td>
                                <td>${grade.soft_skills_score}</td>
                                <td>${grade.technical_skills_score}</td>
                                <td>${grade.total_score}</td>
                                <td>${grade.comment}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <script>
                    function filterReports() {
                        var type = document.getElementById("projectFilter").value;
                        var projectCode = '<%= request.getParameter("projectCode") %>'; // Add projectCode to the URL
                        var url = "projectManageByMentor?service=viewGrade&projectCode=" + projectCode + "&selectedProject=" + type;
                        window.location.href = url;
                    }
                </script>

            </c:if>
        </div>
    </body>
</html>
