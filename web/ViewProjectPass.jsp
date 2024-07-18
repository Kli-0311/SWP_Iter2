<%-- 
    Document   : ViewProjectPass
    Created on : Jul 6, 2024, 3:13:09 AM
    Author     : admin
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                <div class="row">
                <c:if test="${empty list}">
                    <h3>ban chua co du an nao pass</h3>
                </c:if>
                <c:if test="${not empty list}">
                    <h3>Ban Hay chon 1 du an duy nhat de tham gia</h3>
                    <c:forEach items="${list}" var="project">
                        <div class="col-md-6 mb-4">
                            <a href="viewProjectPass?service=detail&projectCode=${project.projectCode}&positionCode=${project.positionCode}">
                                <div class="card">
                                    <img src="${project.projectImg}" class="card-img-top" alt="Project Image">
                                    <div class="card-body">
                                        <h5 class="card-title">${project.projectName}</h5>
                                    </div>
                                </div>
                            </a>
                        </div>
                    </c:forEach>
                </c:if>
                <c:if test="${not empty detailProject}">
                    <div class="row">
                        <div class="col-md-4">
                            <div class="product-img">
                                <img src="${detailProject.projectImage}" class="img-fluid" alt="${detailProject.projectName}">
                            </div>
                        </div>
                        <div class="col-md-5">
                            <div class="product-listing">
                                <div class="content">
                                    <h3 class="name" style="font-size: 40px">${detailProject.projectName}</h3>
                                    <p class="info">Project Code: ${detailProject.projectCode}</p>
                                    <p class="info">Mentor ID: ${detailProject.mentorId}</p>
                                    <p class="info">Detail Project: ${detailProject.description}</p>
                                    <p class="info">Start Date: ${detailProject.projectStartDay}</p>
                                    <p class="info">End Date: ${detailProject.projectEndDay}</p>
                                </div>
                            </div>
                        </div>
                        <form action="viewProjectPass" method="get">
                            <input type="hidden" name="service" value="choose"/>
                            <input type="hidden" name="mentorId" value="${detailProject.mentorId}"/>
                            <input type="hidden" name="projectCode" value="${detailProject.projectCode}"/>
                            <input type="hidden" name="positionCode" value="${positionCode}"/>
                            <input type="hidden" name="role" value="5"/>
                            <input class="btn btn-success w-100" type="submit" value="Choose"/>
                        </form>


                    </div>
                </c:if>
            </div>
        </div>
    </body>
</html>
