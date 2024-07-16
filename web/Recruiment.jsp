<%-- 
    Document   : home.jsp
    Created on : May 20, 2024, 12:45:26 AM
    Author     : ADMIN
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Intern Student Management System </title>
        <link rel="shortcut icon" type="image/png" href="img/anh.png">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" 
              integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <style>
            .container {
                width: 100%;
                min-height: 90vh;
                display: flex;
                align-items: center;
                margin-top: 50px;
                flex-direction: column;
            }

            .content2 {
                width: 100%;
                min-height: 500px;
                display: flex;
                align-items: flex-start;
                margin: 50px 0;
                display: flex;
                flex-direction: row;
                position: relative;
                background-color: #202942;
                border-radius: 20px;

            }

            .content2_item_li-des {
                display: none;
                width: 722px;
                height: 418px;
                position: absolute;
                right: 150px;
                top: 40px;
                padding: 10px;
                background-color: #f9f9f9;
                border: 1px solid #ddd;
            }

            .content2_item_li-des1  {
                position: absolute;
                right: 150px;
                top: 40px;
                padding: 10px;
                background-color: #f9f9f9;
                border: 1px solid #ddd;
            }

            .content2_item-left{
                margin: 130px 0 0 150px;
            }

            .content2_item-left ul {
                list-style: none;
                padding: 0;
                margin: 0;
            }

            .content2_item-left li {
                margin-bottom: 20px;
                font-size: 20px;
                border: 0.5px solid #ccc;
                background-color: white;
                border-radius: 20px;
            }

            .content2_item-left ul li a {
                text-decoration: none;
                color: #202942;
                display: block;
                padding: 10px;
                color: #202942;
            }

            .content2_item_li:hover .content2_item_li-des {
                display: block;
            }

            .content2_item_li::before{
                /*background-color: red;*/
                position: absolute;
                width: 110px;
                height: 50px;
                content: "";
                display: block;
                left: 320px;
            }

            img{
                width: 400px;
            }

            .listProject{
                display: flex;
                flex-direction: row;
                gap:70px;
            }

        </style>
    </head>

    <body>
        <jsp:include page="header.jsp"></jsp:include>

            <div class="container">
                <div class="content">
                    <h1 style="color: rgb(80,80,255);">List Project</h1>
                </div><br>
                <div class="listProject">
                <c:forEach items="${allProjects}" var="p">
                    <div class="project-card">
                        <a href="detailProject?projectCode=${p.projectCode}">
                            <img class="img-fluid" src="${p.projectImage}" alt="" />
                        </a>
                        <div class="project-card-body">
                            <h3 class="mb-3">${p.projectName}</h3>
                        </div>
                    </div>
                </c:forEach>
            </div>

        </div>
        <jsp:include page="footer.jsp"></jsp:include>

    </body>
</html>