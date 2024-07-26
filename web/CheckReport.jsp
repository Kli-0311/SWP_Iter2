<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Intern Reports</title>
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
                window.location.href = "ViewReportsMentor?selectedProject=" + selectedProject;
            }
        </script>
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
            <div class="container">
                <h2>Intern Reports</h2>

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
                        <th>Full Name</th>
                        <th>Project Code</th>
                        <th>Position Code</th>
                        <th>Weekly Report</th>
                        <th>Evaluate</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="reportGroup" items="${reportsList}">
                        <tr>
                            <td>${reportGroup.internId}</td>
                            <td>${reportGroup.fullName}</td>
                            <td>${reportGroup.projectCode}</td>
                            <td>${reportGroup.positionCode}</td>
                            <td>
                                <c:forEach var="weeklyReport" items="${reportGroup.weeklyReports}">
                                    Week  ${weeklyReport.week}: <a href="${weeklyReport.reportLink}" class="report-link" target="_blank">${weeklyReport.reportLink}</a><br>
                                </c:forEach>
                            </td>
                            <td>
                                <a class="btn btn-success" href="evaluateManage?service=midterm&internId=${reportGroup.internId}&projectCode=${reportGroup.projectCode}&fullName=${reportGroup.fullName}">Midterm</a>
                                <a class="btn btn-danger" href="evaluateManage?service=final&internId=${reportGroup.internId}&projectCode=${reportGroup.projectCode}&fullName=${reportGroup.fullName}">Final</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>


    </body>
</html>
