<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Certificate List</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <style>
        .container {
            margin-top: 20px;
        }
        .table th, .table td {
            vertical-align: middle;
            text-align: center;
        }
        .table img {
            max-width: 100px;
            height: auto;
        }
        .table a {
            color: #007bff;
        }
        .table a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
<jsp:include page="header.jsp"/>

<div class="container">
    <a href="chooseProjectCode" class="btn btn-success">Add Certificate</a>
    <c:if test="${not empty certificates}">
    <h1 class="text-center">Certificate List</h1>

    <table class="table table-bordered table-striped">
        <thead>
            <tr>
                
                <th>Name</th>
                <th>Issue Date</th>
                <th>Project Code</th>
                <th>Image</th>
                <th>Link</th>
                <th>Intern Id</th>
                
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="certificate" items="${certificates}">
                <tr>
                    
                    <td>${certificate.cerName}</td>
                    <td><fmt:formatDate value="${certificate.issueDate}" pattern="yyyy-MM-dd"/></td>
                    <td>${certificate.projectCode}</td>
                    <td><img src="${certificate.cerImg}" alt="Certificate Image"/></td>
                    <td><a href="${certificate.cerLink}" target="_blank">Link Certificate</a></td>
                    <td>${certificate.internId}</td>
                    
                    <td>
                        <a href="getCertificate?cer_id=${certificate.cerId}" class="btn btn-primary">Edit</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    </c:if>
    

            <c:if test="${not empty listProject}">
                    <h3 class="text-center">Choose Project you want add Certificate</h3>
                    <div class="col-lg-12 table-responsive mb-5">
                        <table class="table table-bordered text-center mb-0">
                            <thead class="bg-secondary text-dark">
                                <tr>
                                    <th>Image</th>
                                    <th>Project Name</th>
                                    <th>Project Code</th>
                                    <th>Action</th>
                                </tr>
                            </thead>
                            <tbody class="align-middle">
                                <c:forEach items="${listProject}" var="project">
                                    <tr>
                                        <td class="align-middle"><img style="width: 100px" class="img-fluid" src="${project.projectImg}"/></td>
                                        <td class="align-middle">${project.projectName}</td>
                                        <td class="align-middle">${project.projectCode}</td>
                                        <td class="align-middle">
                                            <a href="addCertificate?projectCode=${project.projectCode}">Choose</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </c:if>
                    
                    </div>
<%--<jsp:include page="footer.jsp"/>--%>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-pflPW7sA7WO8MWByxoO2Zx8pJsxwZEqeq2WXDfD7j4N4sqksE+6DQkApU5Fi58O6" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js" integrity="sha384-pzjw8f+ua7Kw1TIqKaWPL5SBRO14AOp5L6bL5w5fqLc1LHK/6VqqKnv1PAdOBVzI" crossorigin="anonymous"></script>
</body>
</html>
