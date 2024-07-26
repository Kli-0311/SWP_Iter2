<%-- 
    Document   : home.jsp
    Created on : May 20, 2024, 12:45:26 AM
    Author     : ADMIN
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Intern Student Management System </title>
        <link rel="shortcut icon" type="image/png" href="img/anh.png">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" 
              integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <style>
            body {
                font-family: 'Arial', sans-serif;
                background-color: #f4f4f9;
                color: #333;
            }

            .container {
                width: 100%;
                min-height: 90vh;
                display: flex;
                align-items: center;
                margin-top: 50px;
                flex-direction: column;
            }

            .content {
                text-align: center;
                margin-bottom: 20px;
            }

            .search-form {
                display: flex;
                justify-content: center;
                align-items: center;
                margin-bottom: 20px;
                width: 100%;
                max-width: 500px;
            }

            .search-input {
                flex: 1;
                padding: 10px 15px;
                border: 1px solid #ccc;
                border-radius: 25px 0 0 25px;
                border-right: none;
                outline: none;
            }

            .search-button {
                padding: 10px 20px;
                border: 1px solid #ccc;
                border-left: none;
                border-radius: 0 25px 25px 0;
                background-color: #87CEEB;
                color: white;
                cursor: pointer;
                outline: none;
            }

            .search-button i {
                font-size: 1.2rem;
            }

            .listProject {
                display: flex;
                flex-wrap: wrap;
                gap: 30px;
                justify-content: center;
                width: 100%;
            }

            .project-card {
                background: #fff;
                border-radius: 10px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
                overflow: hidden;
                transition: transform 0.3s;
                width: 300px;
            }

            .project-card:hover {
                transform: translateY(-10px);
            }

            .project-card-body {
                padding: 15px;
                text-align: center;
            }

            .project-card-body h3 {
                font-size: 1.25rem;
                margin-bottom: 10px;
            }
            nav .pagination {
                display: flex;
                justify-content: flex-start;
                margin-top: 20px;
                width: 100%;
            }

            nav .pagination .page-item .page-link {
                color: #333;
            }

            nav .pagination .page-item.active .page-link {
                background-color: #87CEEB;
                border-color: #87CEEB;
            }
        </style>
    </head>

    <body>
        <jsp:include page="header.jsp"></jsp:include>

            <div class="container">
                <div class="content">
                    <h1 class="text-primary">Projects</h1>
                </div>
            <c:if test="${sessionScope.account.role_id == 6}">
                <h3 class="btn btn-success"><a href="viewProjectPass">Project Pass</a></h3>
            </c:if>
            <c:if test="${done ne null}">
                <h3 class="font-weight-semi-bold  mb-3 text-danger">
                    ${done}
                </h3>
            </c:if>
            <form class="search-form" method="GET" action="SearchProjectList">
                <input class="search-input" value="${search}" type="text" name="txtS" placeholder="Search By Project Name">
                <button class="search-button" type="submit">
                    <i class="fa fa-search"></i>
                </button>
            </form>
            <br>
            <div class="listProject">
                <c:forEach items="${allProjects}" var="p">
                    <div class="project-card">
                        <a href="detailProject?projectCode=${p.projectCode}">
                            <img class="img-fluid" src="${p.projectImage}" alt="${p.projectName}" />
                        </a>
                        <div class="project-card-body">
                            <h3 class="mb-3">${p.projectName}</h3>
                        </div>
                    </div>
                </c:forEach>
            </div>
            <nav aria-label="Page navigation">
                <ul class="pagination">
                    <li class="page-item ${tag == 1 ? 'disabled' : ''}">
                        <a class="page-link" href="viewRecruiment?index=1" aria-label="First">
                            <span aria-hidden="true">&laquo;&laquo;</span>
                        </a>
                    </li>
                    <c:forEach var="i" begin="1" end="${endPage}">
                        <li class="page-item ${tag == i ? 'active' : ''}">
                            <a class="page-link" href="viewRecruiment?index=${i}">${i}</a>
                        </li>
                    </c:forEach>
                    <li class="page-item ${tag == endPage ? 'disabled' : ''}">
                        <a class="page-link" href="viewRecruiment?index=${endPage}" aria-label="Last"><span aria-hidden="true">&raquo;&raquo;</span>
                        </a>
                    </li>
                </ul>
            </nav>
        </div>
        <jsp:include page="footer.jsp"></jsp:include>

    </body>
</html>