<!-- Name: linhtk -->


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>List Accounts of Candidate</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <style>
        /* Custom styles for this page */
        .container {
            max-width: 900px; /* Limit container width for better readability */
            margin-top: 50px; /* Space from top */
        }

        .page-title {
            text-align: center; /* Center align the title */
            margin-bottom: 30px; /* Space below the title */
        }

        /* Table styles */
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px; /* Space above the table */
        }

        th, td {
            padding: 10px;
            text-align: left;
            border: 1px solid #ddd;
        }

        th {
            background-color: #f2f2f2; /* Header background color */
            text-transform: uppercase; /* Uppercase text for headers */
        }

        /* Styling for the status icons */
        .status-icon {
            font-size: 1.2rem; /* Adjust icon size as needed */
            margin-right: 5px; /* Space between icon and text */
            vertical-align: middle; /* Align icon vertically */
        }

        .active-icon {
            color: green; /* Color for active state */
        }

        .inactive-icon {
            color: red; /* Color for inactive state */
        }
    </style>
</head>
<body>
    <jsp:include page="header.jsp" />

    <div class="container">
        <h1 class="page-title">List Accounts of Candidate</h1>

        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>Roll Number</th>
                    <th>Email</th>
                    <th>Full Name</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="account" items="${accounts}">
                    <tr>
                        <td>${account.user_id}</td>
                        <td>${account.username}</td>
                        <td>${account.full_name}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>


    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
