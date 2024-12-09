<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Attendance Detail</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                margin: 20px;
            }
            h4 {
                text-align: center;
            }
            table.calendar {
                width: 80%;
                border-collapse: collapse;
                margin-top: 20px;
                margin-left: auto;
                margin-right: auto;
            }
            table.calendar th, table.calendar td {
                border: 1px solid #ddd;
                padding: 10px;
                text-align: center;
                width: 14.28%;
            }
            table.calendar th {
                background-color: #f2f2f2;
                height: 50px;
            }
            table.calendar td {
                height: 100px;
                vertical-align: top;
            }
            table.calendar td .date {
                font-weight: bold;
                color: #000;
            }
            table.calendar td .status {
                color: green;
            }
            table.calendar td.weekend {
                background-color: #e6f7ff;
            }
            .container {
                max-width: 500px;
                margin: 20px auto;
                padding: 20px;
                border: 1px solid #ccc;
                border-radius: 10px;
                background-color: #f9f9f9;
            }
            .message {
                color: green;
                font-weight: bold;
            }
            .error {
                color: red;
                font-weight: bold;
            }
            .button {
                background-color: #4CAF50;
                color: white;
                padding: 10px 20px;
                border: none;
                border-radius: 5px;
                cursor: pointer;
            }
            .pagination {
                margin-top: 20px;
                text-align: center;
                display: flex;
                justify-content: center;
            }
            .pagination a {
                display: inline-block;
                padding: 8px 16px;
                text-decoration: none;
                color: #000;
                background-color: #f2f2f2;
                border: 1px solid #ddd;
                cursor: pointer;
            }
            .pagination a:hover {
                background-color: #ddd;
            }
        </style>
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>

        <h4>Attendance for ${internName}</h4>

        <table class="calendar">
            <thead>
                <tr></tr>
                <tr>
                    <th>Mon</th>
                    <th>Tue</th>
                    <th>Wed</th>
                    <th>Thu</th>
                    <th>Fri</th>
                    <th>Sat</th>
                    <th>Sun</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="weeksGroup" items="${currentWeeks}">
                    <tr>
                        <c:forEach var="date" items="${weeksGroup}">
                            <td class="${date.dayOfWeek == 'SATURDAY' || date.dayOfWeek == 'SUNDAY' ? 'weekend' : ''}">
                                <div class="date">${date}</div>
                                <div class="status">
                                    <c:choose>
                                        <c:when test="${attendanceRecords[date] != null}">
                                            <h5>${attendanceRecords[date]}</h5>
                                        </c:when>
                                        <c:otherwise>
                                            Not Recorded
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </td>
                        </c:forEach>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <div class="pagination">
            <c:if test="${page > 1}">
                <a href="?user_id=${param.user_id}&page=${page - 1}">Previous</a>
            </c:if>
            <c:if test="${page < totalPages}">
                <a href="?user_id=${param.user_id}&page=${page + 1}">Next</a>
            </c:if>
        </div>

        <div class="container">
            <h3>Attendance for ${formattedDate}</h3>
            <form action="attendance" method="post">
                <input type="hidden" name="user_id" value="${sessionScope.account.user_id}" />
                <button type="submit" class="button">Attendance</button>
            </form>
            <br>
            <div class="${not empty requestScope.message ? 'message' : 'error'}">
                ${requestScope.message}
            </div>
        </div>
    </body>
</html>
