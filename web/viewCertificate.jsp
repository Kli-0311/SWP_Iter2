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
        <h1 class="text-center">Certificate List</h1>
        <table class="table table-bordered table-striped">
            <thead>
                <tr>
                    
                    <th>Name</th>
                    <th>Issue Date</th>                    
                    <th>Project Code</th>
                    <th>Image</th>
                    <th>Link</th>
                    <th>Sender ID</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="certificate" items="${certificates}">
                    <tr>
                        <td>${certificate.cerName}</td>
                        <td><fmt:formatDate value="${certificate.issueDate}" pattern="yyyy-MM-dd"/></td>
                        
                        <td>${certificate.projectCode}</td>
                        <td><img src="${certificate.cerImg}" alt="Certificate Image"/></td>
                        <td><a href="${certificate.cerLink}" target="_blank">View Certificate</a></td>
                        <td>${certificate.senderId}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-pflPW7sA7WO8MWByxoO2Zx8pJsxwZEqeq2WXDfD7j4N4sqksE+6DQkApU5Fi58O6" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js" integrity="sha384-pzjw8f+ua7Kw1TIqKaWPL5SBRO14AOp5L6bL5w5fqLc1LHK/6VqqKnv1PAdOBVzI" crossorigin="anonymous"></script>
    <%--<jsp:include page="footer.jsp"/>--%>
</body>
</html>
