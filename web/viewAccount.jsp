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
        /* Custom styles for this page */
        .container {
            max-width: 900px; /* Limit container width for better readability */
        }

        .search-form {
            text-align: right; /* Align search form to the right */
        }

        .search-results {
            margin-top: 20px; /* Add space above the table */
        }

        /* Optional: Style for table hover effect */
        .table-hover tbody tr:hover {
            background-color: #f2f2f2;
            cursor: pointer;
        }
    </style>
</head>
<body>
    <jsp:include page="header.jsp"></jsp:include>

    <div class="container mt-5">
        <div class="row">
            <div class="col-md-12">
                <h1 class="mb-4">List User Accounts</h1>

                <!-- Search Form -->
                <form class="form-inline mb-3 justify-content-end" action="searchAccount" method="get">
                    <div class="input-group">
                        <input class="form-control" type="search" placeholder="Search username" aria-label="Search" name="username">
                        <div class="input-group-append">
                            <button class="btn btn-outline-success" type="submit">Search</button>
                        </div>
                    </div>
                </form>


                <!-- Table to display search results or all users -->
                <div class="search-results">
                    <table class="table table-bordered table-hover">
                        <thead class="thead-dark">
                            <tr>
                                <th>User ID</th>
                                <th>Username</th>
                                <th>Password</th>
                                <th>Active</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${list}" var="account">
                                <tr>
                                    <td>${account.user_id}</td>
                                    <td>${account.username}</td>
                                    <td>${account.password}</td>
                                    <td>
                                        <form action="IsActive" method="post">
                                            <input type="hidden" name="user_id" value="${account.user_id}">
                                            <button type="submit" class="btn btn-${account.is_active == 1 ? 'success' : 'danger'}">
                                                ${account.is_active == 1 ? 'Active' : 'Deactive'}
                                            </button>
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

    <jsp:include page="footer.jsp"></jsp:include>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
