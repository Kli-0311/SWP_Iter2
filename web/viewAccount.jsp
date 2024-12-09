<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>User Account Management</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <style>
        /* Các style giữ nguyên */
    </style>
</head>
<body>
    <jsp:include page="header.jsp"></jsp:include>
    <div class="container mt-5">
        <div class="row">
            <div class="col-md-12">
                <h1 class="mb-4">List User Accounts</h1>
                <button type="submit" class="btn btn-secondary">Send Mail</button>
                <form class="form-inline mb-3 justify-content-end" action="searchAccount" method="get">
                    <div class="input-group">
                        <input class="form-control" type="search" placeholder="Search username" aria-label="Search" name="username">
                        <div class="input-group-append">
                            <button class="btn btn-outline-success" type="submit">Search</button>
                        </div>
                    </div>
                </form>

                <form action="sendEmail" method="post">
                    <div class="search-results">
                        <table class="table table-bordered table-hover">
                            <thead class="thead-dark">
                                <tr>
                                    <th>Select</th>
                                    <th>User ID</th>
                                    <th>Username</th>
                                    <th>Active</th>
                                    <th>Edit</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${list}" var="account">
                                    <tr>
                                        <td>
                                            <input type="checkbox" name="selectedUsers" value="${account.username}">
                                        </td>
                                        <td>${account.user_id}</td>
                                        <td>${account.username}</td>
                                        <td>
                                            <span class="status-icon ${account.is_active == 1 ? 'active-icon' : 'inactive-icon'}">
                                                ${account.is_active == 1 ? '&#10003;' : '&#10005;'}
                                            </span>
                                        </td>
                                        <td>
                                            <form action="inactiveAccount.jsp" method="post" style="display:inline;">
                                                <input type="hidden" name="user_id" value="${account.user_id}">
                                                <input type="hidden" name="username" value="${account.username}">
                                                <input type="hidden" name="password" value="${account.password}">
                                                <input type="hidden" name="is_active" value="${account.is_active}">
                                                <button type="submit" class="btn btn-primary">Edit</button>
                                            </form>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    
                </form>
                
               
                <c:if test="${not empty successList or not empty failList}">
                    <div class="mt-3">
                        <c:if test="${not empty successList}">
                            <div class="alert alert-success">
                                Email sent successfully to: ${successList}
                            </div>
                        </c:if>
                        <c:if test="${not empty failList}">
                            <div class="alert alert-danger">
                                Failed to send email to: ${failList}
                            </div>
                        </c:if>
                    </div>
                </c:if>
            </div>
        </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>