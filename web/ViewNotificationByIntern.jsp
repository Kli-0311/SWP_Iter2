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
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
        <c:if test="${sessionScope.account.role_id == 5}">
            <div class="container">
                <h1 class="font-weight-semi-bold text-uppercase mb-3 text-center">
                    Notifications
                </h1>
                <c:if test="${empty listNotification}">No notifications available.</c:if>
                <c:if test="${not empty listNotification}">
                    <div class="col-lg-12 table-responsive mb-5">
                        <table class="table table-bordered text-center mb-0">
                            <thead class="bg-secondary text-dark">
                                <tr>
                                    <th>Mentor ID</th>
                                    <th>Project Code</th>
                                    <th>Position Code</th>
                                    <th>Room</th>
                                    <th>Time</th>
                                    <th>Date</th>
                                    <th>Title</th>
                                    <th>Message</th>
                                    <th>Link</th>
                                </tr>
                            </thead>
                            <tbody class="align-middle">
                                <c:forEach items="${listNotification}" var="view">
                                    <tr>
                                        <td class="align-middle">${view.sender_id}</td>
                                        <td class="align-middle">${view.project_code}</td>
                                        <td class="align-middle">${view.position_code}</td>
                                        <td class="align-middle">${view.room}</td>
                                        <td class="align-middle">${view.time}</td>
                                        <td class="align-middle">${view.date_start}</td>
                                        <td class="align-middle">${view.title}</td>
                                        <td class="align-middle">${view.message}</td>
                                        <td class="align-middle">${view.link}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </c:if>
            </div>
        </c:if>
    </body>
</html>
