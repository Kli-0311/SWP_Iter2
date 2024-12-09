<%-- 
    Document   : IPAddress
    Created on : Jul 16, 2024, 2:28:47 PM
    Author     : ADMIN
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Set WiFi IP Address</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f4f4f4;
                margin: 0;
                height: 100vh;
            }
            .container1 {
                background-color: #fff;
                padding: 40px;
                border-radius: 8px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                text-align: center;
                width: 500px;
                margin: 200px auto;
            }
            h2 {
                margin-bottom: 20px;
                color: #333;
            }
            .form-group {
                align-items: center;
                margin-bottom: 20px;
            }

            input[type="text"] {
                padding: 10px;
                border: 1px solid #ccc;
                border-radius: 4px;
            }
            input[type="submit"] {
                background-color: #4CAF50;
                color: white;
                padding: 10px 20px;
                border: none;
                border-radius: 4px;
                cursor: pointer;
                margin-bottom: 20px;
            }
            input[type="submit"]:hover {
                background-color: #45a049;
            }
            .message {
                color: green;
                margin-bottom: 20px;
            }

            .message2{
                margin-bottom: 20px;
            }
        </style>
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>

            <div class="container1">
                <h2>Set WiFi IP Address</h2>

            <%-- Display message if it exists --%>
            <div class="message">
                ${message}
            </div>

            <%-- Display current IP address --%>
            <div class="message2">Current IP Address: ${latestIpAddress != null ? latestIpAddress : "Not set yet"}</div>

            <form action="setWifiIPAddress" method="post">
                <div class="form-group">
                    <label for="IpAddress">WiFi IP Address:</label>
                    <input type="text" id="IpAddress" name="IpAddress" required>
                </div>
                <br>
                <input type="submit" value="Set IP Address">
            </form>
            <a href="home.jsp">Back to home</a>
        </div>
    </body>
</html>
