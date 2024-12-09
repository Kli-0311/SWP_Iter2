<%-- 
    Document   : ViewNotificationByIntern
    Created on : Jun 20, 2024, 8:59:40 PM
    Author     : admin
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Notifications</title>
        <style>
            body, h1, h2, h3, p, ul, ol, li, img {
                margin: 0;
                padding: 0;
                box-sizing: border-box;
            }
            body {
                font-family: Arial, sans-serif;
                background-color: #f8f9fa;
                color: #333;
                line-height: 1.6;
                margin: 0;
                padding: 0;
            }
            .container {
                width: 80%;
                margin: 0 auto;
                display: flex;
                flex-direction: column;
                justify-content: space-between;
                align-items: flex-start;
                padding: 20px;
            }
            .main-content {
                width: 100%;
            }
            .notification-header {
                color: #343a40;
                margin-bottom: 20px;
            }
            .notification-item {
                margin-bottom: 20px;
                padding: 10px;
                background-color: #ffffff;
                border: 1px solid #dee2e6;
                border-radius: 5px;
                transition: transform 0.2s;
                display: flex;
                flex-direction: column;
                align-items: flex-start;
            }
            .notification-item:hover {
                transform: scale(1.02);
            }
            .notification-item h2 {
                color: #007bff;
                font-size: 1.2rem;
                margin-bottom: 5px;
            }
            .notification-item p {
                margin-bottom: 10px;
                color: #666;
            }
            .notification-item a {
                text-decoration: none;
                color: inherit;
            }
            .notification-item a:hover {
                text-decoration: underline;
            }
        </style>
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
        <c:if test="${sessionScope.account.role_id == 5}">
            <div class="container">
                <h1 class="font-weight-semi-bold text-uppercase mb-3 notification-header">
                    Notifications
                </h1>
                <c:if test="${empty listNotification}">
                    <p>No notifications available.</p>
                </c:if>
                <c:if test="${not empty listNotification}">
                    <div class="main-content">
                        <c:forEach items="${listNotification}" var="view">
                            <div class="notification-item ">
                                <a href="detailNotification?notificationId=${view.notification_id}">
                                    <h2>${view.title}</h2>
                                    <p>Published Date: ${view.published_date}</p>
                                </a>
                            </div>
                        </c:forEach>
                    </div>
                </c:if>
            </div>
        </c:if>
    </body>
</html>
