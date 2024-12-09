<%-- 
    Document   : updateInternSchedule
    Created on : Jul 15, 2024, 9:41:07 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Edit Intern Schedule</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
</head>
<body>
    <%@ include file="header.jsp" %>
    <div class="container">
        <h1>Edit Intern Schedule</h1>
        <form action="updateInternSchedule" method="post">
            <div class="form-group">
                <label for="user_id">User ID:</label>
                <input type="text" id="user_id" name="user_id" class="form-control" value="${param.user_id}" readonly>
            </div>
            <div class="form-group">
                <label for="full_name">Full Name:</label>
                <input type="text" id="full_name" name="full_name" class="form-control" value="${param.full_name}" readonly>
            </div>
            <div class="form-group">
                <label for="startDate">Start Date:</label>
                <input type="date" id="startDate" name="startDate" class="form-control" value="${param.start_date}" required>
            </div>
            <div class="form-group">
                <label for="endDate">End Date:</label>
                <input type="date" id="endDate" name="endDate" class="form-control" value="${param.end_date}" required>
            </div>
            <button type="submit" class="btn btn-primary">Update Schedule</button>
        </form>
            
            <div>${message}</div>
    </div>
</body>
</html>

