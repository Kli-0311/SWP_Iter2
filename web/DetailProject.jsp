<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Project Details</title>
        <!-- Bootstrap CSS -->
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <!-- Font Awesome CSS -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
        <style>
            body {
                background-color: #f5f5f5;
                font-family: Arial, sans-serif;
            }
            .container {
                margin-top: 50px;
            }
            .product-img img {
                width: 100%;
                height: auto;
                border-radius: 15px;
            }
            .product-listing {
                background-color: #f0f0f0;
                padding: 20px;
                border-radius: 15px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            }
            .product-listing h3 {
                color: #333;
            }
            .product-listing .info {
                color: #555;
            }
            .product-listing .info a {
                color: #007bff;
            }
            .btn-custom {
                border: none;
                border-radius: 50px;
                padding: 10px 20px;
                font-size: 16px;
                color: #fff;
                display: flex;
                align-items: center;
                justify-content: center;
            }
            .btn-custom .icon {
                margin-right: 10px;
            }
            .card {
                border: none;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                border-radius: 15px;
            }
            .card-body {
                padding: 20px;
            }
            .card-text {
                color: #555;
            }
            .table {
                background-color: #fff;
            }
            .table th, .table td {
                vertical-align: middle;
            }
            .bg-secondary {
                background-color: #007bff !important;
            }
            .text-dark {
                color: #fff !important;
            }
            .btn-and-rating-box {
                margin-top: 20px;
            }
            .btn-blue {
                background-color: #ADD8E6; /* Màu xanh dương nhạt */
                border: none;
                color: #fff;
            }
            .btn-blue:hover {
                background-color: #87CEEB; /* Màu xanh dương nhạt hơn khi hover */
            }
        </style>
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
            <div class="container">
            <c:choose>
                <c:when test="${not empty detailProject}">
                    <div class="row">
                        <div class="col-md-6 mb-4">
                            <div class="product-img">
                                <a href="${detailProject.projectImage}">
                                    <img src="${detailProject.projectImage}" class="img-fluid" alt="${detailProject.projectName}">
                                </a>
                                <span class="tag badge badge-info">new</span>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="product-listing">
                                <div class="content">
                                    <h3 class="name" style="font-size: 40px">${detailProject.projectName}</h3>
                                    <p class="info">Project Code: ${detailProject.projectCode}</p>
                                    <p class="info">
                                        Mentor ID: <a href="MentorsDetail?mentorId=${detailProject.mentorId}">${detailProject.mentorId}</a>
                                    </p>
                                    <p class="info">${detailProject.description}</p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <c:if test="${empty detailProject.positions}">
                        <form action="CandidateManage" method="post" class="mt-3">
                            <input type="hidden" name="userId" value="${sessionScope.account.user_id}">
                            <input type="hidden" name="projectCode" value="${detailProject.projectCode}">
                            <button class="btn btn-custom btn-block btn btn-primary" type="submit">
                                <i class="fas fa-check icon"></i>
                                Apply
                            </button>
                        </form>
                    </c:if>
                    <c:if test="${not empty detailProject.positions}">
                        <h3 class="mt-4">Positions:</h3>
                        <div class="row mt-4">
                            <c:forEach items="${detailProject.positions}" var="position">
                                <div class="col-md-4 mb-3">
                                    <div class="card">
                                        <div class="card-body">
                                            <p class="card-text">Position Code: ${position.positionCode}</p>
                                            <p class="card-text">Position Name: ${position.positionName}</p>
                                            <p class="card-text">Position Count: ${position.positionCount}</p>
                                            <c:if test="${sessionScope.account.role_id != 6}">
                                                <form action="CandidateManage" method="post">
                                                    <input type="hidden" name="service" value="viewCandidate">
                                                    <input type="hidden" name="positionCode" value="${position.positionCode}">
                                                    <button class="btn btn-custom btn-block btn-blue btn btn-primary" type="submit">
                                                        <i class="fas fa-check icon"></i>
                                                        Apply
                                                    </button>
                                                </form>
                                            </c:if>
                                            <c:if test="${sessionScope.account.role_id == 6}">
                                                <form action="CandidateManage" method="post" id="applyProject">
                                                    <input type="hidden" name="service" value="applyProject">
                                                    <input type="hidden" name="userId" value="${sessionScope.account.user_id}">
                                                    <input type="hidden" name="projectCode" value="${detailProject.projectCode}">
                                                    <input type="hidden" name="positionCode" value="${position.positionCode}">
                                                    <button class="btn btn-custom btn-block btn btn-primary" >
                                                        <i class="fas fa-check icon "></i>
                                                        Apply 
                                                    </button>
                                                </form>
                                            </c:if>
                                        </div>
                                    </div>
                                </div>
                                <c:if test="${not empty viewCandidate && position.positionCode == param.positionCode}">
                                    <div class="col-lg-12 table-responsive mb-5">
                                        <table class="table table-bordered text-center mb-0">
                                            <thead class="bg-secondary text-dark">
                                                <tr>
                                                    <th>User ID</th>
                                                    <th>Full Name</th>
                                                    <th>Specialization</th>
                                                </tr>
                                            </thead>
                                            <tbody class="align-middle">
                                                <c:forEach items="${viewCandidate}" var="c">
                                                    <tr>
                                                        <td class="align-middle">${c.userId}</td>
                                                        <td class="align-middle">${c.full_name}</td>
                                                        <td class="align-middle">${c.specialization}</td>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                </c:if>
                            </c:forEach>
                        </div>
                    </c:if>
                    <div class="btn-and-rating-box w-100">
                        <c:if test="${sessionScope.account.role_id != 6}">
                            <div class="d-flex justify-content-around bg-light text-dark rounded p-2">
                                <form action="CandidateManage" method="post" id="viewCandidateAll">
                                    <input type="hidden" name="service" value="viewCandidateAll">
                                    <input type="hidden" name="projectCode" value="${detailProject.projectCode}">
                                    <button class="btn btn-custom btn-blue btn btn-primary" type="submit">
                                        <i class="fas fa-check icon "></i>
                                        View Candidate Apply All
                                    </button>
                                </form>
                            </div>
                        </c:if>
                    </div>
                </c:when>
                <c:otherwise>
                    <p>No project details available.</p>
                </c:otherwise>
            </c:choose>

            <h5 style="color: red">${errorMessage}</h5>

        </div>

        <!-- Bootstrap JS and dependencies -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <!-- Modal Script -->


    </body>
</html>