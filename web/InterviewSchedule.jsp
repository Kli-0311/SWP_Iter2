<%-- 
    Document   : InterviewSchedule
    Created on : Jun 22, 2024, 2:42:53 AM
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
                                    <th>Mentor ID</th>
                                    <th>Project Code</th>
                                    <th>Room</th>
                                    <th>Time</th>
                                    <th>Date</th>
                                    <th>Title</th>
                                    <th>Message</th>
                                </tr>
                            </thead>
                            <tbody class="align-middle">
                                <c:forEach items="${viewInterview}" var="view">
                                    <tr>
                                        <td class="align-middle">${view.mentor_id}</td>
                                        <td class="align-middle">${view.project_code}</td>
                                        <td class="align-middle">${view.room}</td>
                                        <td class="align-middle">${view.time}</td>
                                        <td class="align-middle">${view.date_start}</td>
                                        <td class="align-middle">${view.title}</td>
                                        <td class="align-middle">${view.message}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </c:if>
            </div>
        </c:if>
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

                <!-- Insert a new notification -->
                <div class="row">
                    <a class="btn btn-block btn-primary my-3 py-3 text-center" href="interviewScheduleManage?service=chooseProject">
                        Create a new Interview Schedule
                    </a>
                </div>

                <c:if test="${not empty listProject}">
                    <h3 class="text-center">Choose Project you want create Interview Schedule</h3>
                    <div class="col-lg-12 table-responsive mb-5">
                        <table class="table table-bordered text-center mb-0">
                            <thead class="bg-secondary text-dark">
                                <tr>
                                    <th>Image</th>
                                    <th>Project Name</th>
                                    <th>Mentor ID</th>
                                    <th>Action</th>
                                </tr>
                            </thead>
                            <tbody class="align-middle">
                                <c:forEach items="${listProject}" var="noti">
                                    <tr>
                                        <td class="align-middle"><img style="width: 100px" class="img-fluid" src="${noti.projectImg}"/></td>
                                        <td class="align-middle">${noti.projectName}</td>
                                        <td class="align-middle">${noti.mentorId}</td>
                                        <td class="align-middle">
                                            <a href="interviewScheduleManage?service=requestInsert&projectCode=${noti.projectCode}&mentorId=${noti.mentorId}">Choose</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </c:if>
                <!-- Notification Insert -->
                <c:if test="${InsertDone ne null}">
                    <h3 class="font-weight-semi-bold text-uppercase mb-3 text-center">
                        ${InsertDone}
                    </h3>
                </c:if>
                <c:if test="${not empty allInterviewSchedule}">
                    <div class="col-lg-12 table-responsive mb-5">
                        <table class="table table-bordered text-center mb-0">
                            <thead class="bg-secondary text-dark">
                                <tr>
                                    <th>Interview Schedule ID</th>
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
                                        <td class="align-middle">${noti.interviewschedule_id}</td>
                                        <td class="align-middle">${noti.sender_id}</td>
                                        <td class="align-middle">${noti.mentor_id}</td>
                                        <td class="align-middle">${noti.project_code}</td>
                                        <td class="align-middle">${noti.room}</td>
                                        <td class="align-middle">${noti.time}</td>
                                        <td class="align-middle">${noti.date_start}</td>
                                        <td class="align-middle">${noti.title}</td>
                                        <td class="align-middle">${noti.message}</td>
                                        <td class="align-middle">
                                            <a href="deleteControll?service=deleteInterviewSchedule&interviewschedule_id=${noti.interviewschedule_id}" onclick="return confirmDelete('${noti.interviewschedule_id}')">Delete</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </c:if>
                <script>
                    function confirmDelete(interviewschedule_id) {
                        return confirm("Are you sure you want to delete this Interview Schedule (Interview Schedule ID = " + interviewschedule_id + ") ?");
                    }
                </script>

            </div>
        </c:if>

    </body>
</html>
