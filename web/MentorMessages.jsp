<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <jsp:include page="header.jsp"></jsp:include>
    <title>Mentor Messages</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }
        .container {
            width: 80%;
            margin: auto;
            overflow: hidden;
            background: #fff;
            padding: 20px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
        .messages-table {
            width: 100%;
            border-collapse: collapse;
        }
        .messages-table th, .messages-table td {
            padding: 12px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }
        .messages-table tr:hover {
            background-color: #f1f1f1;
        }
        .messages-table th {
            background-color: #50b3a2;
            color: white;
        }
        .message-title {
            color: #333;
            text-decoration: none;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
        .message-title:hover {
            text-decoration: underline;
        }
        .sender, .subject, .timestamp {
            padding: 10px;
        }
        .sender {
            font-weight: bold;
        }
        .subject {
            flex-grow: 1;
        }
        .timestamp {
            white-space: nowrap;
        }
        .send-button {
            display: inline-block;
            padding: 8px 12px;
            background-color: #007bff;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            margin-top: 20px;
        }
        .send-button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <div class="container">
        <c:if test="${not empty message}">
            <div class="message message-success">${message}</div>
        </c:if>
        <h1>Mentor Messages</h1>
        <table class="messages-table">
            <thead>
                <tr>
                    <th>Sender</th>
                    <th>Subject</th>
                    <th>Timestamp</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="msg" items="${messages}">
                    <tr>
                        <td class="sender"><strong>From:</strong> ${msg.senderId}</td>
                        <td class="subject">
                            <a class="message-title" href="mentorMessages?action=viewMessage&messageId=${msg.messageId}">
                                ${msg.subject}
                            </a>
                        </td>
                        <td class="timestamp">
                            <fmt:formatDate value="${msg.timestamp}" pattern="dd/MM/yyyy"/>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <a href="sendQuestion" class="send-button">Send Questions</a>
    </div>
</body>
</html>
