<%-- 
    Document   : DetailNotification
    Created on : Jun 24, 2024, 12:22:47 AM
    Author     : admin
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Detail Notification</title>
        <style>
            
            .container {
                max-width: 960px;
                margin: 20px auto;
                padding: 20px;
                background-color: #fff;
                border-radius: 8px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            }
            .notification-header {
                margin-bottom: 20px;
                color: #333;
                text-align: center;
                font-size: 2em;
            }
            .card {
                border: none;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
                border-radius: 8px;
                overflow: hidden;
            }
            .card-body {
                padding: 20px;
            }
            .card-title {
                font-size: 1.75em;
                margin-bottom: 10px;
                color: #007bff;
            }
            .card-text {
                margin-bottom: 15px;
                font-size: 1em;
                color: #555;
            }
            .card-text strong {
                font-weight: bold;
                color: #333;
            }
            .publish-date {
                color: #888;
                font-size: 0.9em;
            }
            h5 {
                margin-top: 20px;
                font-size: 1.2em;
                color: #333;
            }
            .footer {
                margin-top: auto;
            }
        </style>
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
        <c:if test="${sessionScope.account.role_id == 5}">
            <div class="container">
                <h2 class="notification-header">
                    Detail Notifications 
                </h2>
                <c:if test="${empty notification}">
                    <p>No notifications available.</p>
                </c:if>
                <c:if test="${not empty notification}">
                    <div class="card">
                        <div class="card-body">
                            <h3 class="card-title">${notification.title}</h3>
                            <p class="card-text publish-date">Publish Date: ${notification.published_date}</p>
                            <p class="card-text">Room: <strong>${notification.room}</strong></p>
                            <p class="card-text">Time: <strong>${notification.time}</strong></p>
                            <p class="card-text">Date: <strong>${notification.date_start}</strong></p>
                            <p class="card-text">Link: <strong>${notification.link}</strong></p>
                            <h5>${notification.message}</h5>
                        </div>
                    </div>
                </c:if>
            </div>
        </c:if>
        <div class="footer">
            <jsp:include page="footer.jsp"></jsp:include>
        </div>
    </body>
</html>
