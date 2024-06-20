<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>News Details</title>
    <!-- Add your CSS stylesheets and JavaScript files here -->
    <style>
        .image-container {
            text-align: center;
        }
        .image-container img {
            display: inline-block;
            max-width: 100%;
        }

        .news-details {
            display: flex;
            flex-direction: column;
            align-items: center;
            position: relative; /* Add relative positioning */
        }

        .title-container {
            margin-top: 20px; /* Adjust margin as needed */
        }

        .publication-date {
            position: absolute;
            top: 10px; /* Adjust top position */
            right: 10px; /* Adjust right position */
            font-size: 14px;
            color: #999;
        }

        .author {
            margin-top: 10px; /* Add margin between publication date and author */
            font-size: 14px;
            color: #666;
        }

        .sidebar {
            padding: 1em;
        }

        .card-container {
            display: flex;
            flex-wrap: wrap;
            gap: 10px;
            justify-content: space-between;
        }

        .card {
            border: 1px solid #ddd;
            border-radius: 8px;
            overflow: hidden;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            display: flex;
            flex-direction: column;
            align-items: center;
            width: 32%; /* Adjust width for smaller cards */
        }

        .card-img-top {
            width: 100%;
            height: auto;
        }

        .card-body {
            padding: 0.5em;
            text-align: center;
        }

        .card-title {
            font-size: 1rem;
            margin-bottom: 0.5em;
        }

        .card-text {
            color: #666;
            margin-bottom: 0.5em;
            font-size: 0.875em;
        }

        .card-link {
            text-decoration: none;
            color: #333;
        }

        .card-link:hover {
            text-decoration: underline;
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
        <h2>News Details</h2>
        <a href="ViewNews" class="back-button"></a>
        <div class="news-details">
            <div class="image-container">
                <img src="${newsDetailsView.imageUrl}" alt="News Image">
            </div>
            <div class="title-container">
                <h3>Title: ${newsDetailsView.title}</h3>
            </div>
            <div id="content-container"></div>
            <div class="publication-date">Published on: ${newsDetailsView.publishedDate}</div>
            <div class="author">Author: ${newsDetailsView.userId}</div>
        </div>
        <div class="sidebar">
            <h3>Latest News</h3>
            <c:if test="${not empty newsLastView}">
                <div class="card-container">
                    <c:forEach items="${newsLastView}" var="news">
                        <div class="card news-item">
                            <img src="${news.imageUrl}" alt="Related News Image" class="card-img-top">
                            <div class="card-body">
                                <a href="ViewNews?news_id=${news.newsId}" class="card-link">
                                    <h5 class="card-title">${news.title}</h5>
                                    <p class="card-text">Published Date: ${news.publishedDate}</p>
                                </a>
                                <div class="author">Author: ${news.userId}</div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </c:if>
        </div>
    </div>
    <jsp:include page="footer.jsp"></jsp:include>

    <script>
        // JavaScript to split content into paragraphs
        document.addEventListener('DOMContentLoaded', function() {
            const content = `${newsDetailsView.content}`; // Fetch the content
            const contentContainer = document.getElementById('content-container');
            const paragraphs = content.split('\n'); // Split content by newline

            paragraphs.forEach(paragraph => {
                if (paragraph.trim()) {
                    const p = document.createElement('p');
                    p.textContent = paragraph;
                    contentContainer.appendChild(p);
                }
            });
        });
    </script>
</body>
</html>
