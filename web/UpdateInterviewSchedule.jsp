<%-- 
    Document   : UpdateInterviewSchedule
    Created on : Jun 28, 2024, 11:56:32 AM
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
        <c:if test="${interviewScheduleUpdate ne null}">
            <div class="container mt-8">
                <h1 class="font-weight-semi-bold text-uppercase mb-3 text-center">
                    Update Interview Schedule
                </h1>
                <form action="updateInterviewSchedule" method="get" id="updateInterviewSchedule" enctype="multipart/form-data">
                    <input type="hidden" name="service" value="sendUpdateDetail" />
                    <div class="mb-3">
                        <label for="interviewScheduleId" class="form-label">Interview Schedule ID</label>
                        <input type="text" class="form-control" id="interviewScheduleId" name="interviewScheduleId" value="${interviewScheduleUpdate.interviewschedule_id}" readonly required>
                    </div>
                    <div class="mb-3">
                        <label for="send_id" class="form-label">Send ID</label>
                        <input type="text" class="form-control" id="send_id" name="send_id" value="${interviewScheduleUpdate.sender_id}" readonly required>
                    </div>
                    <div class="mb-3">
                        <label for="mentor_id" class="form-label">Mentor ID</label>
                        <input type="text" class="form-control" id="mentor_id" name="mentor_id" value="${interviewScheduleUpdate.mentor_id}" readonly required>
                    </div>
                    <div class="mb-3">
                        <label for="project_code" class="form-label">Project Code</label>
                        <input type="text" class="form-control" id="project_code" name="project_code" value="${interviewScheduleUpdate.project_code}" readonly required>
                    </div>
                    <div class="mb-3">
                        <label for="room" class="form-label">Room</label>
                        <input type="text" class="form-control" id="room" name="room"  value="${interviewScheduleUpdate.room}"required>
                    </div>
                    <div class="mb-3">
                        <label for="title" class="form-label">Title</label>
                        <input type="text" class="form-control" id="title" name="title" value="${interviewScheduleUpdate.title}" required>
                    </div>
                    <div class="mb-3">
                        <label for="time" class="form-label">Time</label>
                        <input type="text" class="form-control" id="time" name="time" value="${interviewScheduleUpdate.time}" placeholder="hh:MM:ss" required>
                    </div>
                    <div class="mb-3">
                        <label for="date" class="form-label">Date</label>
                        <input type="date" class="form-control" id="date" name="date" value="${interviewScheduleUpdate.date_start}" required>
                    </div>
                    <div class="mb-3">
                        <label for="message" class="form-label">Message</label>
                        <textarea type="textarea" class="form-control" id="message" name="message" value="${interviewScheduleUpdate.message}"  required></textarea>
                    </div>
                    <div class="text-center">
                        <button type="submit" class="btn btn-primary">Update</button>
                    </div>
                </form>
            </div>
        </c:if>
    </body>
</html>
