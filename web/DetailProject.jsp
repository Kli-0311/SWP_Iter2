<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Project Details</title>
        <!-- Bootstrap CSS -->
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
         <style>
            .container {
                width: 100%;
                min-height: 90vh;
                display: flex;
                align-items: center;
                margin-top: 50px;
                flex-direction: column;
            }

            .content2 {
                width: 100%;
                min-height: 500px;
                display: flex;
                align-items: flex-start;
                margin: 50px 0;
                display: flex;
                flex-direction: row;
                position: relative;
                background-color: #202942;
                border-radius: 20px;

            }

            .content2_item_li-des {
                display: none;
                width: 722px;
                height: 418px;
                position: absolute;
                right: 150px;
                top: 40px;
                padding: 10px;
                background-color: #f9f9f9;
                border: 1px solid #ddd;
            }

            .content2_item_li-des1  {
                position: absolute;
                right: 150px;
                top: 40px;
                padding: 10px;
                background-color: #f9f9f9;
                border: 1px solid #ddd;
            }

            .content2_item-left{
                margin: 130px 0 0 150px;
            }

            .content2_item-left ul {
                list-style: none;
                padding: 0;
                margin: 0;
            }

            .content2_item-left li {
                margin-bottom: 20px;
                font-size: 20px;
                border: 0.5px solid #ccc;
                background-color: white;
                border-radius: 20px;
            }

            .content2_item-left ul li a {
                text-decoration: none;
                color: #202942;
                display: block;
                padding: 10px;
                color: #202942;
            }

            .content2_item_li:hover .content2_item_li-des {
                display: block;
            }

            .content2_item_li::before{
                /*background-color: red;*/
                position: absolute;
                width: 110px;
                height: 50px;
                content: "";
                display: block;
                left: 320px;
            }

            img{
                width: 700px;
            }

        </style>
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
            <div class="container mt-5">
            <c:choose>
                <c:when test="${not empty detailProject}">
                    <div class="row">
                        <div class="col-md-6">
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
                                    <p class="info">Mentor ID: ${detailProject.mentorId}</p>
                                    <p class="info">${detailProject.description}</p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <c:if test="${empty detailProject.positions}">
                        <form action="CandidateManage" method="post">
                            <input type="hidden" name="userId" value="${sessionScope.account.user_id}">
                            <input type="hidden" name="projectCode" value="${detailProject.projectCode}">
                            <button class="btn btn-primary" type="submit">Apply</button>
                        </form>
                    </c:if>
                    <c:if test="${not empty detailProject.positions}">
                        <h3>Positions:</h3>
                        <div class="row mt-4">
                            <div class="row">
                                <c:forEach items="${detailProject.positions}" var="position">
                                    <c:if test="${sessionScope.account.role_id != 6 }">
                                        <div class="col-md-4 mb-3">
                                            <div class="card">
                                                <div class="card-body">
                                                    <p class="card-text">Position Code: ${position.positionCode}</p>
                                                    <p class="card-text">Position Name: ${position.positionName}</p>
                                                    <p class="card-text">Position Count: ${position.positionCount}</p>
                                                    <form action="CandidateManage" method="post">
                                                        <input type="hidden" name="service" value="viewCandidate">
                                                        <input type="hidden" name="positionCode" value="${position.positionCode}">
                                                        <button class="btn btn-primary" type="submit">View Candidate Apply</button>
                                                    </form>
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
                                    </c:if>
                                    <%-- code day --%>
                                    <c:if test="${sessionScope.account.role_id == 6}">
                                        <div class="col-md-4 mb-3">
                                            <div class="card">
                                                <div class="card-body">
                                                    <p class="card-text">Position Code: ${position.positionCode}</p>
                                                    <p class="card-text">Position Name: ${position.positionName}</p>
                                                    <p class="card-text">Position Count: ${position.positionCount}</p>
                                                    <form action="CandidateManage" method="post" id="applyProject">
                                                        <input type="hidden" name="service" value="applyProject">
                                                        <input type="hidden" name="userId" value="${sessionScope.account.user_id}">
                                                        <input type="hidden" name="projectCode" value="${detailProject.projectCode}">
                                                        <input type="hidden" name="positionCode" value="${position.positionCode}">
                                                        <button class="btn btn-primary" type="submit">Apply</button>
                                                    </form>
                                                </div>
                                            </div>
                                        </div>
                                    </c:if>
                                </c:forEach>
                            </div>
                        </c:if>
                        <div class="btn-and-rating-box mt-3 w-100">
                            <c:if test="${sessionScope.account.role_id != 6}">
                                <div class="d-flex justify-content-around bg-light text-white rounded p-2">
                                    <div>
                                        <form action="CandidateManage" method="post" id="viewCandidateAll">
                                            <input type="hidden" name="service" value="viewCandidateAll">
                                            <input type="hidden" name="projectCode" value="${detailProject.projectCode}">
                                            <button class="btn btn-primary" type="submit">View Candidate Apply All</button>
                                        </form>
                                    </div>
                                </div>
                            </c:if>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <p>No project details available.</p>
                    </c:otherwise>
                </c:choose>
            </div>
            <jsp:include page="footer.jsp"></jsp:include>
            <!-- Bootstrap JS and dependencies -->
            <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
            <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
            <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </body>
</html>