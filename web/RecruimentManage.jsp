<%-- 
Document   : mentorManageByHR
Created on : May 24, 2024, 2:17:16 PM
Author     : admin
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            .a{
                display: flex;
                align-content: center;
                justify-content: center;
            }
            .form-control{
                width: 200px;
            }
            .messenger-form {
                display: flex;
                align-items: center;
                justify-content: space-between;
                background-color: #f0f0f0;
                border-radius: 20px;
                padding: 10px 20px;
                margin-bottom: 10px;
                width: 100%;
                max-width: 400px;
            }

            .messenger-form input[type="text"] {
                flex-grow: 1;
                border: none;
                outline: none;
                padding: 10px;
                border-radius: 20px;
                margin-right: 10px;
            }

            .messenger-form button {
                border: none;
                outline: none;
                background-color: #0084ff;
                color: white;
                padding: 10px 20px;
                border-radius: 20px;
                cursor: pointer;
                transition: background-color 0.3s ease;
            }

            .messenger-form button:hover {
                background-color: #0056b3;
            }
        </style>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
            <div class="container">
            <c:if test="${sessionScope.account.role_id == 3}">
                <h1 class="font-weight-semi-bold text-uppercase mb-3 text-center">
                    Projects Manager
                </h1>

                <!-- Insert a new project -->
                <div class="row">
                    <a class="btn btn-block btn-primary my-3 py-3 text-center" 
                       href="manageRecruiment?service=requestInsert"
                       >
                        Create a new Project
                    </a>
                </div>

                <c:if test="${showSearchProject ne null}">
                    <!--Search product by name-->
                    <div
                        class="d-flex align-items-center justify-content-between mb-4"
                        >
                        <form action="manageRecruiment" id="searchByName">
                            <input type="hidden" name="service" value="searchByKeywords"/>
                            <div class="input-group">
                                <input
                                    type="text"
                                    class="form-control"
                                    placeholder="Search by name"
                                    name="keywords"
                                    value="${keywords}"
                                    />
                                <div class="input-group-append">
                                    <button class="search-button btn btn-primary" type="submit">
                                        <i class="fa fa-search"></i>
                                    </button>
                                </div>
                            </div>
                        </form>            
                    </div>
                </c:if>

                <c:if test="${notFoundProduct ne null}">
                    <h4 class="font-weight-semi-bold text-uppercase mb-3 text-center">
                        ${notFoundProduct}
                    </h4>
                </c:if>

                <!--thong bao create thanh cong or khong-->
                <c:if test="${InsertDone ne null}">
                    <h3 class="font-weight-semi-bold mb-3 text-center">
                        ${InsertDone}
                    </h3>
                </c:if>
                <c:if test="${not empty allProjects}">
                    <div class="col-lg-12 table-responsive mb-5">
                        <table class="table table-bordered text-center mb-0">
                            <thead class="bg-secondary text-dark">
                                <tr>
                                    <th>Project Code</th>
                                    <th>Project Name</th>
                                    <th>Mentor ID</th>
                                    <th>Project Image</th>
                                    <th>Start Day</th>
                                    <th>End Day</th>
                                    <th>Description</th>
                                    <th>Position Code</th>
                                    <th>Position Name</th>
                                    <th>Position Count</th>
                                    <th>Delete</th>
                                </tr>
                            </thead>
                            <tbody class="align-middle">
                                <c:forEach items="${allProjects}" var="project">
                                    <tr>
                                        <td class="align-middle">${project.projectCode}</td>
                                        <td class="align-middle">${project.projectName}</td>
                                        <td class="align-middle">${project.mentorId}</td>
                                        <td class="align-middle"><img style="width: 70%" src="${project.projectImage}"/></td>
                                        <td class="align-middle">${project.projectStartDay}</td>
                                        <td class="align-middle">${project.projectEndDay}</td>
                                        <td class="align-middle">${project.description}</td>
                                        <c:choose>
                                            <c:when test="${empty project.positions}">
                                                <td class="align-middle"></td>
                                                <td class="align-middle"></td>
                                                <td class="align-middle"></td>
                                            </c:when>
                                            <c:otherwise>
                                                <td class="align-middle">
                                                    <c:forEach items="${project.positions}" var="position">
                                                        ${position.positionCode}<br>
                                                    </c:forEach>
                                                </td>
                                                <td class="align-middle">
                                                    <c:forEach items="${project.positions}" var="position">
                                                        ${position.positionName}<br>
                                                    </c:forEach>
                                                </td>
                                                <td class="align-middle">
                                                    <c:forEach items="${project.positions}" var="position">
                                                        ${position.positionCount}<br>
                                                    </c:forEach>
                                                </td>
                                            </c:otherwise>
                                        </c:choose>
                                        <td class="align-middle">
                                            <a href="deleteControl?service=deleteProject&project_code=${project.projectCode}" onclick="return confirmDelete('${project.projectCode}')">Delete</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>

                    <script>
                        function confirmDelete(projectCode) {
                            return confirm("Are you sure you want to delete this Project (Project Code = " + projectCode + ") ?");
                        }
                    </script>
                </c:if>
               
            </c:if>
        </div>
        <jsp:include page="footer.jsp"></jsp:include>
        <!-- Bootstrap JavaScript Bundle with Popper -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" 
                integrity="sha384-Vk1pRXs9kaQwgj/+4dcABGpK/Ko1y9lOjp1z7ieQ5LRUjAg3BoR1GIVCNUyHfxiN" 
        crossorigin="anonymous"></script>
       
    </body>
</html>
