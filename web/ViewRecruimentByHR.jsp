<%-- 
    Document   : ViewRecruimentForMentor
    Created on : Jun 26, 2024, 5:20:18 PM
    Author     : admin
--%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            /* Reset CSS */
            body,h1, h2, h3, p, ul, ol, li, img {
                margin: 0;
                padding: 0;
                box-sizing: border-box;
            }
            body {
                font-family: Arial, sans-serif;
                background-color: #f8f9fa;
                color: #333;
                line-height: 1.6;
                margin: 0;
                padding: 0;
            }
            .container {
                margin: 0 auto;
                justify-content: space-between;
                align-items: flex-start;
                padding: 20px;
            }
            .main-content {
                width: 100%;
            }
            h2 {
                color: #343a40;
                margin-bottom: 20px;
            }
            .news-item {
                margin-bottom: 20px;
                padding: 10px;
                background-color: #ffffff;
                border: 1px solid #dee2e6;
                border-radius: 5px;
                transition: transform 0.2s;
                display: flex;
                align-items: flex-start;
            }
            .news-item:hover {
                transform: scale(1.02);<%-- tạo hiệu ứng --%>
            }
            .news-item img {
                max-width: 150px;
                margin-right: 20px;
                border-radius: 5px;
            }
            .news-item h2 {
                color: #007bff;
                font-size: 1.2rem;
                margin-bottom: 5px;
            }
            .news-item p {
                margin-bottom: 10px;
                color: #666;
            }
            .news-item a {
                text-decoration: none;
                color: inherit;
            }
            .news-item a:hover {
                text-decoration: underline;
            }
            .author {
                color: #666;
                font-size: 0.9rem;
            }
            .notification-header {
                /*font-size: 2em;*/
                margin-bottom: 20px;
                color: #333;
            }

            .row {
                display: flex;
                flex-wrap: wrap;
                margin: 0 -15px;
            }

            .col-md-6 {
                flex: 0 0 50%;
                max-width: 50%;
                padding: 0 15px;
                box-sizing: border-box;
            }

            .product-img {
                text-align: center;
                margin-bottom: 20px;
            }

            .product-img img {
                max-width: 100%;
                border-radius: 8px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            }

            .product-listing .content {
                background-color: #f9f9f9;
                padding: 20px;
                border-radius: 8px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            }

            .name {
                font-size: 2.5em;
                margin-bottom: 10px;
                color: #444;
            }

            .info {
                font-size: 1em;
                color: #555;
                margin: 10px 0;
            }

            .info strong {
                color: #333;
            }
        </style>
    </head>
    <body>
        <jsp:include page="header.jsp" />
        <c:if test="${sessionScope.account.role_id == 3}">
            <div class="container">
                <h2 class="font-weight-semi-bold mb-3 text-center">
                    Recruitment requirements
                </h2>
                <c:if test="${done ne null}">
                    <h5 style="color: red" class="font-weight-semi-bold mb-3">
                        ${done}
                    </h5>
                </c:if>
                <c:if test="${not empty listRecruitment}">
                    <div class="main-content">
                        <c:forEach items="${listRecruitment}" var="view">
                            <div class="news-item ">
                                <img src="${view.img}" alt="News Image">
                                <div>
                                    <a href="mentorManage?service=detail&messageId=${view.messageId}">
                                        <h2>${view.title}</h2>
                                        <p>Published Date: ${view.timestamp}</p>
                                    </a>
                                    <div class="author">
                                        Mentor Id: ${view.senderId}
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </c:if>
                <!--Detail Recruitment requirement-->
                <c:if test="${not empty message}">
                    <div class="row">
                        <div class="col-md-6">
                            <div class="product-img">
                                <a href="${message.img}">
                                    <img src="${message.img}" class="img-fluid" alt="">
                                </a>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="product-listing">
                                <div class="content">
                                    <h3 class="name" style="font-size: 40px">${message.title}</h3>
                                    <p class="info">Mentor ID: ${message.senderId}</p>
                                    <p class="info">Start Date: ${message.date_start}</p>
                                    <p class="info">End Date: ${message.date_end}</p>
                                    <p class="info">JD: ${message.message}</p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <form action="mentorManage" method="get">
                        <input type="hidden" name="service" value="respondMentor" />
                        <input type="hidden" name="message_id" value="${message.messageId}" />
                        <input type="hidden" name="receiverId" value="${message.senderId}" />
                        <input type="hidden" name="status" value="Done"/>
                        <button type="submit" class="btn btn-primary">Done</button>
                    </form>

                </c:if>
            </div>
        </c:if>
    </body>
</html>
