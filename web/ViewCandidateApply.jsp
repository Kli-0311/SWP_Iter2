<%-- 
    Document   : ViewCandidateApply
    Created on : Jun 1, 2024, 2:50:05 AM
    Author     : admin
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <!-- Bootstrap CSS -->
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <style>
            .table th, .table td {
                vertical-align: middle;
            }
            .container {
                margin-top: 20px;
            }
        </style>
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
            <div class="container">
            <c:if test="${empty listCandidate}">
                hahahah
            </c:if>
            <c:if test="${not empty listCandidate}">
                <div class="content">
                    <h1 style="color: rgb(80,80,255);">List Candidate Apply ${param.projectCode}</h1>
                </div><br>
                <div class="table-responsive">
                    <table class="table table-bordered table-hover">
                        <thead class="thead-dark">
                            <tr>
                                <th>Project Code</th>
                                <th>Position Code</th>
                                <!--<th>Status</th>-->
                                <th>Roll Number</th>
                                <th>Full Name</th>
                                <th>Date Of Birth</th>
                                <th>Gender</th>
                                <th>Phone Number</th>
                                <th>Specialization</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${listCandidate}" var="p">
                                <tr>
                                    <td>${p.projectCode}</td>
                                    <td>${p.positionCode}</td>
                                    <!--<td>${p.status}</td>-->
                                    <td>${p.userId}</td>
                                    <td>${p.full_name}</td>
                                    <td>${p.dob}</td>
                                    <td>${p.gender}</td>
                                    <td>${p.phone_number}</td>
                                    <td>${p.specialization}</td>
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
