<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Project Details</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    
</head>

<body>

    <jsp:include page="header.jsp"></jsp:include>

        <div class="container mt-5">
            <h1>Projects</h1>
            <div class="row">
            <c:forEach items="${projectsList}" var="project">
                <div class="col-md-6 mb-4">
                    <div class="card">
                        <img src="${project.projectImg}" class="card-img-top" alt="Project Image">
                        <div class="card-body">
                            <h5 class="card-title">${project.projectName}</h5>
                            <p class="card-text">Project Code: ${project.projectCode}</p>
                            <p class="card-text">Mentor: ${project.mentorId}</p>
                            <p class="card-text">Project Details: ${project.projectDetails}</p>
                            <p class="card-text">Start Date: ${project.projectStartDay}</p>
                            <p class="card-text">End Date: ${project.projectEndDay}</p>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>


    <div class="container mt-5">
        <h2>Notes</h2>
        <div class="row">
            <div class="col-md-12">
                <div class="container mb-5">
                    <table class="table">
                        <thead>
                            <tr>
                                <th scope="col">Note Id</th>
                                <th scope="col">Intern Id</th>
                                <th scope="col">Note Content</th>
                                <th scope="col">Created At</th>
                                <th scope="col">Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${notesList}" var="noteItem">
                                <tr>
                                    <td>${noteItem.noteId}</td>
                                    <td>${noteItem.internId}</td>
                                    <td>${noteItem.noteContent}</td>
                                    <td><fmt:formatDate value="${noteItem.createdAt}" pattern="yyyy-MM-dd" /></td>
                                    <td>
                                        <form action="note" method="post">
                                            <input type="hidden" name="delete" value="delete">
                                            <input type="hidden" name="user_id" value="${user_id}" />
                                            <input type="hidden" name="noteContent" value="${noteItem.noteContent}" />
                                            <input type="hidden" name="createdAt" value="${noteItem.createdAt}" />
                                            <button type="submit" class="btn btn-danger">Delete</button>
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>

    <div class="container mt-5">
        <h2>Add Note</h2>
        <div class="row">
            <div class="col-md-12">
                <div class="container mb-5">
                    <form action="note" method="post">
                        <input type="hidden" name="user_id" value="${user_id}" />
                        <div class="mb-3">
                            <input type="text" name="noteContent" class="form-control" placeholder="Enter note content">
                        </div>
                        <div class="mb-3">
                            <input type="date" name="createdAt" class="form-control">
                        </div>
                        <button type="submit" class="btn btn-primary">Add Note</button>
                    </form>
                </div>
            </div>
        </div>
    </div>


</body>
</html>
