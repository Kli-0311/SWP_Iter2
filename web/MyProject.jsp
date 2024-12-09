<%-- 
    Document   : MyProject
    Created on : Jun 23, 2024, 2:25:09 AM
    Author     : admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>

            <div class="container mt-5">
                <h1>Projects</h1>
                <div class="row">
                <c:if test="${empty list}">fff</c:if>
                    <c:forEach items="${list}" var="project">
                    <div class="col-md-6 mb-4">
                        <a href="projectManageByMentor?projectCode=${project.projectCode}">
                            <div class="card">
                                <img src="${project.projectImg}" class="card-img-top" alt="Project Image">
                                <div class="card-body">
                                    <h5 class="card-title">${project.projectName}</h5>
                                    <p class="card-text">Project Code: ${project.projectCode}</p>
                                    <p class="card-text">Project Details: ${project.projectDetails}</p>
                                    <p class="card-text">Start Date: ${project.projectStartDay}</p>
                                    <p class="card-text">End Date: ${project.projectEndDay}</p>
                                </div>
                            </div>
                        </a>
                    </div>
                </c:forEach>
            </div>
        </div>
    </body>
</html>
