<%-- 
    Document   : UpdateNotification
    Created on : Jun 28, 2024, 1:11:52 PM
    Author     : admin
--%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
        <c:if test="${notificationUpdate ne null}">
            <div class="container mt-8">
                <h1 class="font-weight-semi-bold text-uppercase mb-3 text-center">
                    Update Interview Schedule
                </h1>
                <form action="updateNotification" method="get" id="updateNotification" enctype="multipart/form-data">
                    <input type="hidden" name="service" value="sendUpdateDetail" />
                    <div class="mb-3">
                        <label for="notificationId" class="form-label">Notification ID</label>
                        <input type="text" class="form-control" id="notificationId" name="notificationId" value="${notificationUpdate.notification_id}" readonly required>
                    </div>
                    <div class="mb-3">
                        <label for="send_id" class="form-label">Mentor ID</label>
                        <input type="text" class="form-control" id="send_id" name="send_id" value="${notificationUpdate.sender_id}" readonly required>
                    </div>
                    <div class="mb-3">
                        <label for="project_code" class="form-label">Project Code</label>
                        <input type="text" class="form-control" id="project_code" name="project_code" value="${notificationUpdate.project_code}" readonly required>
                    </div>
                    <div class="mb-3">
                        <label for="position_code" class="form-label">Position Code</label>
                        <select id="position_code" name="position_code" value="${notificationUpdate.position_code}"class="form-control" required>
                            <option value="">Select a Position</option>
                            <c:forEach items="${listPosition}" var="position">
                                <option value="${position.positionCode}">${position.positionCode}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="mb-3">
                        <label for="room" class="form-label">Room</label>
                        <input type="text" class="form-control" id="room" name="room" value="${notificationUpdate.room}">
                    </div>
                    <div class="mb-3">
                        <label for="title" class="form-label">Title</label>
                        <input type="text" class="form-control" id="title" name="title" value="${notificationUpdate.title}" required>
                    </div>
                    <div class="mb-3">
                        <label for="time" class="form-label">Time</label>
                        <input type="text" class="form-control" id="time" name="time" value="${notificationUpdate.time}" placeholder="hh:mm:ss" required>
                    </div>
                    <div class="mb-3">
                        <label for="date" class="form-label">Date</label>
                        <input type="date" class="form-control" id="date" name="date" value="${notificationUpdate.date_start}" required>
                    </div>
                    <div class="mb-3">
                        <label for="published_date" class="form-label">Publish Date</label>
                        <input type="date" class="form-control" id="published_date" name="published_date"value="${notificationUpdate.published_date}" required>
                    </div>
                    <div class="mb-3">
                        <label for="message" class="form-label">Message</label>
                        <textarea type="text" class="form-control" id="message" name="message" value="${notificationUpdate.message}" placeholder="Write something..." ></textarea>
                    </div>
                    <div class="mb-3">
                        <label for="link" class="form-label">Link</label>
                        <input type="text" class="form-control" id="link" name="link" value="${notificationUpdate.link}" placeholder="Link Meet">
                    </div>
                    <div class="text-center">
                        <button type="submit" class="btn btn-primary">Update</button>
                    </div>
                </form>
            </div>
        </c:if>
    </body>
</html>
