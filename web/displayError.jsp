<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Error Display</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            line-height: 1.6;
            background-color: #f0f0f0;
            padding: 20px;
        }
        .error-table {
            width: 80%;
            margin: 20px auto;
            border-collapse: collapse;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            background-color: #ffffff;
        }
        .error-table th, .error-table td {
            border: 1px solid #dddddd;
            padding: 10px;
            text-align: left;
        }
        .error-table th {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>
    <h2>Error Messages</h2>
    <table class="error-table">
        <thead>
            <tr>
                <th>Error Type</th>
                <th>Error Details</th>
            </tr>
        </thead>
        <tbody>
            <%
                String errorMessages = (String) request.getAttribute("errorMessages");
                String[] errors = errorMessages.split("<br>");
                for (String error : errors) {
                    String[] errorDetails = error.split(": ");
                    String errorType = errorDetails[0];
                    String errorDetail = errorDetails.length > 1 ? errorDetails[1] : "";
            %>
            <tr>
                <td><%= errorType %></td>
                <td><%= errorDetail %></td>
            </tr>
            <% } %>
        </tbody>
    </table>
</body>
</html>
