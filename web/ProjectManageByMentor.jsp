<%-- 
    Document   : ProjectManageByMentor
    Created on : Jun 23, 2024, 3:04:53 PM
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
                    <div class="col-md-3">
                        <div class="product-listing">
                            <a class="btn btn-primary" href="notificationControl?projectCode=${detailProject.projectCode}">Notification</a>
                        </div>
                        <div class="product-listing">
                            <a class="btn btn-success" href="projectManageByMentor?service=viewGrade&projectCode=${detailProject.projectCode}">View Grade</a>
                        </div>
                    </div>     
                </div>
                <c:if test="${not empty detailProject.positions}">
                    <h3>Positions:</h3>
                    <div class="row mt-4">
                        <div class="row">
                            <c:forEach items="${detailProject.positions}" var="position">
                                <c:if test="${sessionScope.account.role_id == 4 }">
                                    <div class="col-md-4 mb-3">
                                        <div class="card">
                                            <div class="card-body">
                                                <p class="card-text">Position Code: ${position.positionCode}</p>
                                                <p class="card-text">Position Name: ${position.positionName}</p>
                                                <p class="card-text">Position Count: ${position.positionCount}</p>
                                            </div>
                                        </div>
                                    </div>
                                </c:if>
                            </c:forEach>
                        </div>
                    </c:if>
                </c:if>
                <c:if test="${not empty listCandidate}">
                    <h3>List Candidate </h3>
                    <div class="col-lg-12 table-responsive mb-5">
                        <table class="table table-bordered text-center mb-0">
                            <thead class="bg-secondary text-dark">
                                <tr>
                                    <th>Roll Number</th>
                                    <th>Email</th>
                                    <th>Full Name</th>
                                    <th>Gender</th>
                                    <th>Date Of Birth</th>
                                    <th>Phone Number</th>
                                    <th>Major</th>
                                    <th>Action</th>
                                </tr>
                            </thead>
                            <tbody class="align-middle">
                                <c:forEach items="${listCandidate}" var="view">
                                    <tr>
                                        <td class="align-middle">${view.userId}</td>
                                        <td class="align-middle">${view.username}</td>
                                        <td class="align-middle">${view.full_name}</td>
                                        <td class="align-middle">${view.gender}</td>
                                        <td class="align-middle">${view.dob}</td>
                                        <td class="align-middle">${view.phone_number}</td>
                                        <td class="align-middle">${view.specialization}</td>
                                        <td class="align-middle">
                                            <form action="passFaildManage" method="get">
                                                <input type="hidden" name="service" value="fail"/>
                                                <input type="hidden" name="userId" value="${view.userId}"/>
                                                <input type="hidden" name="email" value="${view.username}"/>
                                                <input type="hidden" name="fullname" value="${view.full_name}"/>
                                                <input type="hidden" name="projectCode" value="${view.projectCode}"/>
                                                <input class="btn btn-danger w-100" type="submit" value="Fail"/>
                                            </form>
                                            <form action="passFaildManage" method="get">
                                                <input type="hidden" name="service" value="pass"/>
                                                <input type="hidden" name="userId" value="${view.userId}"/>
                                                <input type="hidden" name="fullname" value="${view.full_name}"/>
                                                <input type="hidden" name="email" value="${view.username}"/>
                                                <input type="hidden" name="projectCode" value="${view.projectCode}"/>
                                                <input type="hidden" name="status" value="Done"/>

                                                <input class="btn btn-success w-100" type="submit" value="Pass"/>
                                            </form>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <script>
                        function confirmDelete(userId) {
                            return confirm("Are you sure you want to fail this User (User ID = " + userId + ") ?");
                        }
                    </script>
                </c:if>
                <c:if test="${not empty listIntern}">
                    <h3>List Intern </h3>
                    <div class="col-lg-12 table-responsive mb-5">
                        <table class="table table-bordered text-center mb-0">
                            <thead class="bg-secondary text-dark">
                                <tr>
                                    <th>User ID</th>
                                    <th>Email</th>
                                    <th>Full Name</th>
                                    <th>Gender</th>
                                    <th>Date Of Birth</th>
                                    <th>Phone Number</th>
                                    <th>Major</th>
                                </tr>
                            </thead>
                            <tbody class="align-middle">
                                <c:forEach items="${listIntern}" var="view">
                                    <tr>
                                        <td class="align-middle">${view.user_id}</td>
                                        <td class="align-middle">${view.username}</td>
                                        <td class="align-middle">${view.full_name}</td>
                                        <td class="align-middle">${view.gender}</td>
                                        <td class="align-middle">${view.dob}</td>
                                        <td class="align-middle">${view.phone_number}</td>
                                        <td class="align-middle">${view.specialization}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </c:if>
            </div>
            <!-- Bootstrap JS and dependencies -->
            <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
            <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
            <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </body>
</html>
