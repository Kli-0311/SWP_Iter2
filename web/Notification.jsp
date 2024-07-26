<%-- 
    Document   : Notification
    Created on : Jun 23, 2024, 4:38:33 PM
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
        <c:if test="${sessionScope.account.role_id == 4}">
            <div class="container">
                <h1 class="font-weight-semi-bold text-uppercase mb-3 text-center">
                    Announcements Manage
                </h1>

                <!-- Create a new notification -->
                <div class="row">
                    <a class="btn btn-block btn-primary my-3 py-3 text-center" 
                       href="notificationControl?service=requestInsert&projectCode=${projectCode}">
                        Create a new Announcement
                    </a>
                </div>
                <!--//thông bái insert thành công-->
                <c:if test="${InsertDone ne null}">
                    <h4 class="font-weight-semi-bold  mb-3 text-danger">
                        ${InsertDone}
                    </h4>
                </c:if>
                <!--view list Notification-->
                <c:if test="${not empty allNotification}">
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
                                    <th>Publish Date</th>
                                    <th>Title</th>
                                    <th>Message</th>
                                    <th>Link</th>
                                    <th>Delete</th>
                                </tr>
                            </thead>
                            <tbody class="align-middle">
                                <c:forEach items="${allNotification}" var="noti">
                                    <tr>
                                        <td class="align-middle">${noti.sender_id}</td>
                                        <td class="align-middle">${noti.project_code}</td>
                                        <td class="align-middle">${noti.position_code}</td>
                                        <td class="align-middle">${noti.room}</td>
                                        <td class="align-middle">${noti.time}</td>
                                        <td class="align-middle">${noti.date_start}</td>
                                        <td class="align-middle">${noti.published_date}</td>
                                        <td class="align-middle">${noti.title}</td>
                                        <td class="align-middle">${noti.message}</td>
                                        <td class="align-middle">
                                            <a href="${noti.link}" >${noti.link}</a>
                                        </td>
                                        <td class="align-middle">
                                            <a href="updateNotification?service=requestUpdate&notificationId=${noti.notification_id}&projectCode=${noti.project_code}">Update</a>
                                            <a href="notificationControl?service=deleteNotification&notificationId=${noti.notification_id}&projectCode=${noti.project_code}" onclick="return confirmDelete('${noti.notification_id}')">Delete</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <script>
                        function confirmDelete(notificationId) {
                            return confirm("Are you sure you want to delete this Notification (Notification ID = " + notificationId + ") ?");
                        }
                    </script>
                </c:if>


            </c:if>
    </body>
</html>
