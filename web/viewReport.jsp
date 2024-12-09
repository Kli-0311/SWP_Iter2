<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>View Reports</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f8f9fa;
        }
        .container {
            margin-top: 50px;
        }
    </style>
</head>
<body>

<jsp:include page="header.jsp"></jsp:include>

<div class="container mt-5">
    <h2>Reports</h2>
    <div class="row">
        <div class="col-md-12">
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>Intern ID</th>
                        <th>Week</th>
                        <th>Report</th>
                        <th>Report Link</th>
                        <th>Project Code</th>
                        <th>Mentor ID</th>
                        <th>Action</th> 
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${report}" var="report">
                        <tr>
                            <td>${report.internId}</td>
                            <td>${report.week}</td>
                            <td>${report.report}</td>
                            <td><a href="${report.reportLink}" target="_blank">Report Link</a></td>
                            <td>${report.projectCode}</td>
                            <td>${report.mentorId}</td>
                            <td>
                                <a href="editReport?reportId=${report.reportId}" class="btn btn-primary btn-sm">Edit</a>
                            </td> 
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    <a href="submitReport" class="btn btn-primary">Submit Report</a>
</div>
</body>
</html>
