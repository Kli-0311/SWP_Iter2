<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <jsp:include page="header.jsp"></jsp:include>
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
            background: #fff;
            padding: 20px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
            margin-top: 20px;
        }
        .message-details {
            margin-bottom: 20px;
        }
        .message {
            padding: 10px;
            border: 1px solid #ddd;
            margin-bottom: 10px;
            max-width: 70%;
        }
        .message.user-message {
            background-color: #e1ffc7;
            margin-left: auto;
        }
        .message.other-message {
            background-color: #fafafa;
            margin-right: auto;
        }
        .reply-form {
            margin-top: 20px;
        }
        .back-button {
            display: inline-block;
            padding: 8px 12px;
            background-color: #50b3a2;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            margin-top: 20px;
        }
        .reply-form textarea {
            width: 100%;
            padding: 10px;
            margin-top: 10px;
        }
        .reply-form button {
            background-color: #007bff;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            margin-top: 10px;
        }
        .reply-form button:hover {
            background-color: #0056b3;
        }
        .timestamp {
            text-align: center;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="message-details">
        <c:if test="${message != null}">
            <h1><strong>Subject:</strong> ${message.subject}</h1>
            <h4><strong>To:</strong> ${message.receiverId}</h4>
        </c:if>
        <c:if test="${message == null}">
            <p>Message not found.</p>
        </c:if>
    </div>

    <!-- Display Replies -->
    <div class="replies">
        <c:forEach var="reply" items="${replies}">
            <p class="timestamp">
                    <fmt:formatDate value="${reply.timestamp}" pattern="HH:mm dd/MM/yyyy"/>
                </p>
            <div class="message ${reply.senderId == sessionScope.account.user_id ?  'user-message' : 'other-message'  }">
                <p>${reply.message}</p>
                
            </div>
        </c:forEach>
    </div>

    <!-- Form for sending a reply -->
    <form class="reply-form" action="replyIntern" method="post">
        <input type="hidden" name="messageId" value="${message.messageId}">
        <input type="hidden" name="senderId" value="${sessionScope.account.user_id}">
        <input type="hidden" name="receiverId" value="${message.receiverId}">
        <input type="hidden" name="subject" value="${message.subject}">
        <textarea name="message" placeholder="Type your reply here..." required></textarea>
        <button type="submit">Reply</button>
    </form>

    <a href="mentorMessages?user_id=${sessionScope.account.user_id}" class="back-button">Back</a>
</div>
</body>
</html>
