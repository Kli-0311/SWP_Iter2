<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Submit Report</title>
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
        .error {
            color: red;
        }
    </style>
</head>
<body>

    <jsp:include page="header.jsp"></jsp:include>

    <div class="container mt-5">
        <h2 class="mb-4">Submit Report</h2>

        

        <form action="submitReport" method="post">
            <input type="hidden" name="user_id" value="${sessionScope.account.user_id}">
            <div class="mb-3">
                <label for="week" class="form-label">Week</label>
                <input type="number" class="form-control" id="week" name="week" min="1" max="10" placeholder="1" required>
            </div>
            <div class="mb-3">
                <label for="report" class="form-label">Report</label>
                <textarea class="form-control" id="report" name="report" rows="3" required></textarea>
            </div>
            <div class="mb-3">
                <label for="report_link" class="form-label">Report Link</label>
                <input type="text" class="form-control" id="report_link" name="report_link" required>
            </div>
            
            <div class="mb-3">
                <label for="project_code" class="form-label">Project Code</label>
                <select class="form-control" id="project_code" name="project_code" required>
                    <c:forEach var="project" items="${project}">
                        <option value="${project.projectCode}">${project.projectCode}</option>
                    </c:forEach>
                </select>
            </div>
            <button type="submit" class="btn btn-primary">Submit Report</button>
        </form>
    </div>
</body>
</html>
