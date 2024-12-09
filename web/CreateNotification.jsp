<%-- 
    Document   : CreateNotification
    Created on : Jun 23, 2024, 4:44:44 PM
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
        <c:if test="${createNotification ne null}">
            <div class="container mt-8">
                <h1 class="font-weight-semi-bold text-uppercase mb-3 text-center">
                    Create a new Notification
                </h1>
                <form action="createNotification" method="get" id="createNotification" enctype="multipart/form-data">
                    <input type="hidden" name="service" value="sendInsertDetail" /> 
                    <div class="mb-3">
                        <label for="send_id" class="form-label">Mentor ID</label>
                        <input type="text" class="form-control" id="send_id" name="send_id" value="${sessionScope.account.user_id}" readonly required>
                    </div>
                    <div class="mb-3">
                        <label for="project_code" class="form-label">Project Code</label>
                        <input type="text" class="form-control" id="project_code" name="project_code" value="${projectCode}" readonly required>
                    </div>
                    <div class="mb-3">
                        <label for="position_code" class="form-label">Position Code</label>
                        <select id="position_code" name="position_code" class="form-control" >
                            <option value="">Select a Position</option>
                            <c:forEach items="${listPosition}" var="position">
                                <option value="${position.positionCode}">${position.positionName}</option>
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
                        <label for="published_date" class="form-label">Publish Date</label>
                        <input type="date" class="form-control" id="published_date" name="published_date" required>
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
    </body>
</html>
