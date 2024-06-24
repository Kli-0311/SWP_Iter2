<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>News</title>
    <link href="style.css" rel="stylesheet" type="text/css" >
    <style>
        /* Reset CSS */
        body, h1, h2, h3, p, ul, ol, li, img {
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
            width: 80%;
            margin: 0 auto;
            display: flex;
            justify-content: space-between;
            align-items: flex-start;
            padding: 20px;
        }
        .main-content {
            width: 65%;
        }
        .sidebar {
            width: 30%;
            background-color: #f0f0f0;
            padding: 10px;
            border-radius: 5px;
        }
        h2 {
            color: #343a40;
            margin-bottom: 20px;
        }
        .latest-news-button {
            margin-bottom: 20px;
        }
        .latest-news-button input[type="radio"] {
            margin-right: 10px;
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
        .sidebar h3 {
            margin-bottom: 10px;
            color: #343a40;
        }
        .sidebar .news-item {
            background-color: #ffffff;
            padding: 20px;
            border: 1px solid #dee2e6;
            border-radius: 5px;
            margin-bottom: 10px;
        }
        .sidebar .news-item img {
            max-width: 100px;
            border-radius: 5px;
        }
        .sidebar .news-item h3 {
            font-size: 1rem;
            margin-bottom: 5px;
            color: #007bff;
        }
        .sidebar .news-item p {
            color: #666;
            margin-bottom: 5px;
        }
        .sidebar .news-item a {
            text-decoration: none;
            color: inherit;
        }
        .sidebar .news-item a:hover {
            text-decoration: underline;
        }
        .form-control {
           
           margin-top: 10px;
           margin-bottom:  10px;
        }

        .input-group-append{
            font-size: 20px;
            padding-bottom: 9px;
            padding-top: 8px;
            
        }
         .pagination a {
            margin: 0 5px;
            text-decoration: none;
            padding: 5px 10px;
            border: 1px solid #ccc;
        }
        .pagination a.active {
            font-weight: bold;
            background-color: #eee;
        }
        .back-button {
            display: inline-flex;
            align-items: center;
            justify-content: center;
            width: 40px;
            height: 40px;
            background-color: #f8f8f8;
            border: 1px solid #ccc;
            border-radius: 5px;
            text-decoration: none;
            color: #333;
            font-size: 20px;
            transition: background-color 0.3s ease;
        }

        .back-button:hover {
            background-color: #e0e0e0;
        }

        .back-button::before {
            content: '←';
            margin-right: 5px;
        }
    </style>
</head>
<body>
    <jsp:include page="header.jsp"></jsp:include>

        <div class="container">
            <div class="main-content">
                <h2> News</h2>
            <c:if test="${isSearchResult != null && isSearchResult}">
                <div>
                    <a href="ViewNews" class="back-button"></a>
                </div>
            </c:if>
            <form action="SearchNews" method="post" class="form-inline my-2 my-lg-0" >
                <div class="input-group input-group-sm">
                    <input  value="${searchS}" name="search" type="text" class="form-control"
                           aria-label="Small" aria-describedby="inputGroup-sizing -sm" placeholder="Search News ">
                    <div class="input-group-append">
                        <button type="submit" class="btn btn-secondary btn-number">
                            <i class="fa fa-search"></i>
                        </button>
                    </div>
                
            </div>
                
            </form>
            <c:forEach var="news" items="${ListA}"  >
                <div class="news-item" id="news-item-${news.newsId}">
                    <img src="${news.imageUrl}" alt="News Image">
                    <div>
                        <a href="ViewNews?news_id=${news.newsId}">
                            <h2>${news.title}</h2>
                            <p>Published Date: ${news.publishedDate}</p>
                        </a>
                        <div class="author">
                            Author: ${news.userId}
                        </div>
                    </div>
                </div>
            </c:forEach>
              <!-- Pagination Controls -->
            <nav aria-label="Page navigation">
                <ul class="pagination">
                    <c:forEach var="i" begin="1" end="${endP}">
                        <li class="page-item ${tag == i ? 'active' : ''}">
                            <a class="page-link" href="ViewNews?index=${i}">${i}</a>
                        </li>
                    </c:forEach>
                </ul>
            </nav>
              
            
                             
        </div>

        <div class="sidebar">
            <h3>Latest News</h3>
            <c:if test="${not empty newsLastView}">
                <c:forEach items="${newsLastView}" var="news">
                    <div class="news-item">
                        <img src="${news.imageUrl}" alt="Related News Image">
                        <div>
                            <a href="ViewNews?news_id=${news.newsId}">
                                <h3>${news.title}</h3>
                                <p>Published Date: ${news.publishedDate}</p>
                            </a>
                            <div class="author">
                                Author: ${news.userId}
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </c:if>

        </div>
                           
    </div>
                   

    <jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
