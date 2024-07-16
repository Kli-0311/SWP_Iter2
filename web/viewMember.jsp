<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>View project members</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link rel="stylesheet" href="css/headerAndFooter.css">
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<div class="container" style="margin-top: 80px;">
    <h1>View project members</h1>
    <c:if test="${not empty projectsWithMembers}">
        <c:forEach items="${projectsWithMembers}" var="project">
            <h2>${project.projectCode}</h2>
            <c:if test="${not empty project.members}">
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>User ID</th>
                            <th>Username</th>
                            <th>Full Name</th>
                            <th>Position Name</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${project.members}" var="member">
                            <tr>
                                <td>${member.user_id}</td>
                                <td>${member.username}</td>
                                <td>${member.full_name}</td>
                                <td>${member.position_name}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:if>
            <c:if test="${empty project.members}">
                <p>No members found for this project.</p>
            </c:if>
        </c:forEach>
    </c:if>

    <c:if test="${empty projectsWithMembers}">
        <p>No projects found for this user.</p>
    </c:if>
</div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
