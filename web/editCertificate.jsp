<%-- 
    Document   : editCertificate
    Created on : Jun 24, 2024, 9:39:57 PM
    Author     : NXC2003
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Certificate</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <style>
            .container {
                margin-top: 20px;
            }
            .form-container {
                margin-top: 20px;
                border: 1px solid #ccc;
                padding: 20px;
            }
        </style>
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <div class="container">
            <h1 class="text-center">Edit Certificate</h1>
            <div class="form-container">
                <form action="editCertificate" method="post" enctype="multipart/form-data">
                    <input type="hidden" name="user_id" value="${sessionScope.account.user_id}">
                    <input type="hidden" name="cer_id" value="${certi.cerId}">

                    <div class="mb-3">
                        <label for="cer_name" class="form-label">Certificate Name:</label>
                        <input type="text" class="form-control" id="cer_name" name="cer_name" value="${certi.cerName}" required>
                    </div>

                    <div class="mb-3">
                        <label for="issue_date" class="form-label">Issue Date:</label>
                        <input type="date" class="form-control" id="issue_date" name="issue_date" value="<fmt:formatDate value='${certi.issueDate}' pattern='yyyy-MM-dd'/>" required>
                    </div>

                    <div class="mb-3">
                        <label for="project_code" class="form-label">Project Code:</label>
                        <input type="text" class="form-control" id="project_code" name="project_code" value="${certi.projectCode}" required>
                    </div>

                    <div class="mb-3">
                        <label for="cer_img" class="form-label">Image:</label>
                        <input type="text" class="form-control" id="cer_img" name="cer_img" value="${certi.cerImg}">
                        <input type="file" class="form-control" id="cer_img" name="new_cer_img">
                    </div>

                    <div class="mb-3">
                        <label for="cer_link" class="form-label">Link:</label>
                        <input type="text" class="form-control" id="cer_link" name="cer_link" value="${certi.cerLink}">
                    </div>

                    <div class="mb-3">
                        <label for="intern_id" class="form-label">Intern ID:</label>
                        <input type="number" class="form-control" id="intern_id" name="intern_id" value="${certi.internId}" required>
                    </div>

                    <input type="hidden" id="senderId" name="senderId" value="${sessionScope.account.user_id}">

                    <button type="submit" class="btn btn-primary">Save Changes</button>
                </form>
            </div>
        </div>
        <jsp:include page="footer.jsp"/>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-pflPW7sA7WO8MWByxoO2Zx8pJsxwZEqeq2WXDfD7j4N4sqksE+6DQkApU5Fi58O6" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js" integrity="sha384-pzjw8f+ua7Kw1TIqKaWPL5SBRO14AOp5L6bL5w5fqLc1LHK/6VqqKnv1PAdOBVzI" crossorigin="anonymous"></script>
    </body>
</html>