<%-- 
    Document   : Notification
    Created on : Jun 12, 2024, 1:58:43 AM
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
            <!--View Interview Schedule by Intern-->
        <c:if test="${sessionScope.account.role_id == 6}">
            <div class="container">
                <h1 class="font-weight-semi-bold text-uppercase mb-3 text-center">
                    Interview Schedule
                </h1>
                <c:if test="${not empty viewInterview}">
                    <div class="col-lg-12 table-responsive mb-5">
                        <table class="table table-bordered text-center mb-0">
                            <thead class="bg-secondary text-dark">
                                <tr>
                                    <th>Send ID</th>
                                    <th>Project Code</th>
                                    <th>Room</th>
                                    <th>Time</th>
                                    <th>Date</th>
                                    <th>Title</th>
                                    <th>Message</th>
                                </tr>
                            </thead>
                            <tbody class="align-middle">
                                <tr>
                                    <td class="align-middle">${viewInterview.sender_id}</td>
                                    <td class="align-middle">${viewInterview.project_code}</td>
                                    <td class="align-middle">${viewInterview.room}</td>
                                    <td class="align-middle">${viewInterview.time}</td>
                                    <td class="align-middle">${viewInterview.date_start}</td>
                                    <td class="align-middle">${viewInterview.title}</td>
                                    <td class="align-middle">${viewInterview.message}</td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </c:if>
            </div>
        </c:if>
        <!--View list Interview Schedule by Mentor-->
        <c:if test="${sessionScope.account.role_id == 4}">
            <div class="container">
                <h1 class="font-weight-semi-bold text-uppercase mb-3 text-center">
                    Interview Schedule
                </h1>
                <c:if test="${not empty listInterviewSchedule}">
                    <div class="col-lg-12 table-responsive mb-5">
                        <table class="table table-bordered text-center mb-0">
                            <thead class="bg-secondary text-dark">
                                <tr>
                                    <th>Mentor ID</th>
                                    <th>Project Code</th>
                                    <th>Room</th>
                                    <th>Time</th>
                                    <th>Date</th>
                                    <th>Title</th>
                                </tr>
                            </thead>
                            <tbody class="align-middle">
                                <c:forEach items="${listInterviewSchedule}" var="view">
                                    <tr>
                                        <td class="align-middle">${view.mentor_id}</td>
                                        <td class="align-middle">${view.project_code}</td>
                                        <td class="align-middle">${view.room}</td>
                                        <td class="align-middle">${view.time}</td>
                                        <td class="align-middle">${view.date_start}</td>
                                        <td class="align-middle">${view.title}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </c:if>
            </div>
        </c:if>

        <c:if test="${sessionScope.account.role_id == 3}">
            <div class="container">
                <h1 class="font-weight-semi-bold text-uppercase mb-3 text-center">
                    Interview Schedule Manage
                </h1>

                <!-- Insert a new Interview Schedule -->
                <div class="row">
                    <a class="btn btn-block btn-primary my-3 py-3 text-center" 
                       href="interviewScheduleManage?service=requestInsert"
                       >
                        Create a new Interview Schedule
                    </a>
                </div>
                <!--View list Interview Schedule by HR-->
                <c:if test="${not empty allInterviewSchedule}">
                    <div class="col-lg-12 table-responsive mb-5">
                        <table class="table table-bordered text-center mb-0">
                            <thead class="bg-secondary text-dark">
                                <tr>
                                    <th>Notifications ID</th>
                                    <th>Send ID</th>
                                    <th>Mentor ID</th>
                                    <th>Project Code</th>
                                    <th>Room</th>
                                    <th>Time</th>
                                    <th>Date</th>
                                    <th>Title</th>
                                    <th>Message</th>
                                    <th>Delete</th>
                                </tr>
                            </thead>
                            <tbody class="align-middle">
                                <c:forEach items="${allInterviewSchedule}" var="noti">
                                    <tr>
                                        <td class="align-middle">${noti.notification_id}</td>
                                        <td class="align-middle">${noti.sender_id}</td>
                                        <td class="align-middle">${noti.mentor_id}</td>
                                        <td class="align-middle">${noti.project_code}</td>
                                        <td class="align-middle">${noti.room}</td>
                                        <td class="align-middle">${noti.time}</td>
                                        <td class="align-middle">${noti.date_start}</td>
                                        <td class="align-middle">${noti.title}</td>
                                        <td class="align-middle">${noti.message}</td>
                                        <td class="align-middle">
                                            <a href="deleteNotification?service=deleteNotification&notificationId=${noti.notification_id}" onclick="return confirmDelete('${noti.notification_id}')">Delete</a>
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
                    <h3 class="font-weight-semi-bold mb-3 text-center">
                        ${InsertDone}
                    </h3>
                </c:if>
                <!--//Form insert Notification-->
                <c:if test="${createInterviewSchedule ne null}">
                    <div class="container mt-8">
                        <h1 class="font-weight-semi-bold text-uppercase mb-3 text-center">
                            Create Interview Schedule
                        </h1>
                        <form action="interviewScheduleManage" method="get" id="createInterviewSchedule" enctype="multipart/form-data">
                            <input type="hidden" name="service" value="sendInsertDetail" />
                            <div class="mb-3">
                                <label for="send_id" class="form-label">Send ID</label>
                                <input type="text" class="form-control" id="send_id" name="send_id" value="${sessionScope.account.user_id}" readonly required>
                            </div>
                            <div class="mb-3">
                                <label for="mentor_id" class="form-label">Mentor ID</label>
                                <select id="mentor_id" name="mentor_id" class="form-control" required>
                                    <c:forEach items="${listMentor}" var="p">
                                        <option value="${p.user_id}">${p.user_id}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="mb-3">
                                <label for="project_code" class="form-label">Project Code</label>
                                <select id="project_code" name="project_code" class="form-control" required>
                                    <c:forEach items="${listProject}" var="p">
                                        <option value="${p.projectCode}">${p.projectCode}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="mb-3">
                                <label for="room" class="form-label">Room</label>
                                <input type="text" class="form-control" id="room" name="room" required>
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
                                <textarea type="text" class="form-control" id="message" name="message" placeholder="Write something..." required></textarea>
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
