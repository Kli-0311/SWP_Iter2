<%-- 
    Document   : CreateProject
    Created on : Jun 20, 2024, 11:32:04 PM
    Author     : admin
--%>
<%@taglib  prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
        <c:if test="${insertProject ne null}">
                    <div class="container mt-8">
                        <h1 class="font-weight-semi-bold text-uppercase mb-3 text-center">
                            Create Project
                        </h1>
                        <form action="createRecruiment" method="get" id="insertProject" enctype="multipart/form-data">
                            <input type="hidden" name="service" value="sendInsertDetail" />
                            <div class="mb-3">
                                <label for="projectCode" class="form-label">Project Code</label>
                                <input type="text" class="form-control" id="projectCode" name="project_code" required>
                            </div>
                            <div class="mb-3">
                                <label for="projectName" class="form-label">Project Name</label>
                                <input type="text" class="form-control" id="projectName" name="project_name" required>
                            </div>
                            <div class="mb-3">
                                <label for="mentorId" class="form-label">Mentor ID</label>
                                <select id="mentorId" name="mentor_id" id="mentorId" class="form-control" required>
                                    <c:forEach items="${listMentor}" var="p">
                                        <option value="${p.user_id}">${p.username}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="mb-3">
                                <label for="projectStartDay" class="form-label">Start Day</label>
                                <input type="date" class="form-control" id="projectStartDay" name="projectStartDay" required>
                            </div>
                            <div class="mb-3">
                                <label for="projectEndDay" class="form-label">End Day</label>
                                <input type="date" class="form-control" id="projectEndDay" name="projectEndDay" required>
                            </div>
                            <div class="mb-3">
                                <label for="description" class="form-label">Description</label>
                                <input type="text" class="form-control" id="description" name="description" required>
                            </div>
                            <div class="mb-3">
                                <label for="projectImage" class="form-label">Project Image</label>
                                <input type="file" class="form-control" id="projectImage" name="project_img" required>
                            </div>
                            <div class="mb-3">
                                <label class="form-label">Project Positions</label>
                                <div id="positions-container">
                                </div>
                                <button type="button" class="btn btn-secondary" onclick="addPosition()">Add Position</button>
                            </div>
                            <div class="text-center">
                                <button type="submit" class="btn btn-primary">Add</button>
                            </div>
                        </form>
                    </div>
                </c:if>
            
        </div>
        <jsp:include page="footer.jsp"></jsp:include>
        <!-- Bootstrap JavaScript Bundle with Popper -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" 
                integrity="sha384-Vk1pRXs9kaQwgj/+4dcABGpK/Ko1y9lOjp1z7ieQ5LRUjAg3BoR1GIVCNUyHfxiN" 
        crossorigin="anonymous"></script>
        <script>
                                    function addPosition() {
                                        const container = document.getElementById('positions-container');
                                        const positionDiv = document.createElement('div');
                                        positionDiv.classList.add('mb-3');

                                        positionDiv.innerHTML = `
            <div class="row">
                <div class="col-md-6">
                    <label class="form-label">Position Name</label>
                    <input type="text" class="form-control" name="position_name[]" required>
                </div>
                <div class="col-md-6">
                    <label class="form-label">Number of Positions</label>
                    <input type="number" class="form-control" name="position_count[]" required>
                </div>
            </div>
        `;
                                        container.appendChild(positionDiv);
                                    }
        </script> 
    </body>
</html>
