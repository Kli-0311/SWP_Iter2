<%-- 
    Document   : Notification
    Created on : Jun 16, 2024, 12:05:03 AM
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
                    Notifications Manage
                </h1>

                <!-- Create a new notification -->
                <div class="row">
                    <a class="btn btn-block btn-primary my-3 py-3 text-center" 
                       href="notification?service=requestInsert"
                       >
                        Create a new Notifications
                    </a>
                </div>
                <!--view list Notification-->
                <c:if test="${not empty allNotification}">
                    <div class="col-lg-12 table-responsive mb-5">
                        <table class="table table-bordered text-center mb-0">
                            <thead class="bg-secondary text-dark">
                                <tr>
                                    <th>Notifications ID</th>
                                    <th>Mentor ID</th>
                                    <th>Project Code</th>
                                    <th>Position Code</th>
                                    <th>Room</th>
                                    <th>Time</th>
                                    <th>Date</th>
                                    <th>Title</th>
                                    <th>Message</th>
                                    <th>Link</th>
                                    <th>Delete</th>
                                </tr>
                            </thead>
                            <tbody class="align-middle">
                                <c:forEach items="${allNotification}" var="noti">
                                    <tr>
                                        <td class="align-middle">${noti.notification_id}</td>
                                        <td class="align-middle">${noti.sender_id}</td>
                                        <td class="align-middle">${noti.project_code}</td>
                                        <td class="align-middle">${noti.position_code}</td>
                                        <td class="align-middle">${noti.room}</td>
                                        <td class="align-middle">${noti.time}</td>
                                        <td class="align-middle">${noti.date_start}</td>
                                        <td class="align-middle">${noti.title}</td>
                                        <td class="align-middle">${noti.message}</td>
                                        <td class="align-middle">
                                            <a href="${noti.link}" >${noti.link}</a>
                                        </td>
                                        <td class="align-middle">
                                            <a href="deleteControl?service=deleteNotification&notificationId=${noti.notification_id}" onclick="return confirmDelete('${noti.notification_id}')">Delete</a>
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


                <!--//thông bái insert thành công-->
                <c:if test="${InsertDone ne null}">
                    <h3 class="font-weight-semi-bold  mb-3 text-center">
                        ${InsertDone}
                    </h3>
                </c:if>
                <!--//Form create Notification-->
                <c:if test="${createNotification ne null}">
                    <div class="container mt-8">
                        <h1 class="font-weight-semi-bold text-uppercase mb-3 text-center">
                            Create a new Notification
                        </h1>
                        <form action="notification" method="get" id="createNotification" enctype="multipart/form-data">
                            <input type="hidden" name="service" value="sendInsertDetail" />
                            <div class="mb-3">
                                <label for="send_id" class="form-label">Mentor ID</label>
                                <input type="text" class="form-control" id="send_id" name="send_id" value="${sessionScope.account.user_id}" readonly required>
                            </div>
                            <div class="mb-3">
                                <label for="project_code" class="form-label">Project Code</label>
                                <select id="project_code" name="project_code" class="form-control" required>
                                    <option value="">Select a Project</option>
                                    <c:forEach items="${listProject}" var="project">
                                        <option value="${project.projectCode}">${project.projectCode}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="mb-3">
                                <label for="position_code" class="form-label">Position Code</label>
                                <select id="position_code" name="position_code" class="form-control" >
                                    <option value="">Select a Position</option>
                                    <c:forEach items="${listPosition}" var="position">
                                        <option value="${position.positionCode}">${position.positionCode}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="mb-3">
                                <label for="room" class="form-label">Room</label>
                                <input type="text" class="form-control" id="room" name="room" >
                            </div>
                            <div class="mb-3">
                                <label for="title" class="form-label">Title</label>
                                <input type="text" class="form-control" id="title" name="title" required>
                            </div>
                            <div class="mb-3">
                                <label for="time" class="form-label">Time</label>
                                <input type="text" class="form-control" id="time" name="time" placeholder="hh:mm:ss" required>
                            </div>
                            <div class="mb-3">
                                <label for="date" class="form-label">Date</label>
                                <input type="date" class="form-control" id="date" name="date" required>
                            </div>
                            <div class="mb-3">
                                <label for="message" class="form-label">Message</label>
                                <textarea type="text" class="form-control" id="message" name="message" placeholder="Write something..." ></textarea>
                            </div>
                            <div class="mb-3">
                                <label for="link" class="form-label">Link</label>
                                <input type="text" class="form-control" id="link" name="link" placeholder="Link Meet">
                            </div>
                            <div class="text-center">
                                <button type="submit" class="btn btn-primary">Add</button>
                            </div>
                        </form>
                    </div>
                </c:if>

            </div>
        </c:if>
    </body>
</html>
