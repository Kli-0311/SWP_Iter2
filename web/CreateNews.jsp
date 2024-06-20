<%-- 
    Document   : CreateNew
    Created on : Jun 12, 2024, 10:26:10 PM
    Author     : ADM
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    
    
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>News Manager</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/material-icons/4.0.0/iconfont/material-icons.min.css" rel="stylesheet">
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <!-- Bootstrap Datepicker CSS -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/css/bootstrap-datepicker.min.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f9fa;
        }
        .container {
            width: 80%;
            margin: 0 auto;
            padding: 20px;
        }
        h1 {
            color: #343a40;
            text-align: center;
            margin-bottom: 30px;
        }
        th {
            background-color: #007bff;
            color: #fff;
        }
        tr:nth-child(even) {
            background-color: #f8f9fa;
        }
        .edit, .delete {
            display: inline-block;
            padding: 6px 12px;
            margin-right: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
            text-decoration: none;
            color: #333;
            background-color: #fff;
            font-size: 14px;
        }

        .edit:hover, .delete:hover {
            background-color: #e6e6e6;
            border-color: #adadad;
            color: #333;
        }

        .material-icons {
            vertical-align: middle;
            font-size: 18px;
            margin-right: 5px;
        }

        .tooltip-inner {
            background-color: #343a40;
            color: #fff;
            border-radius: 4px;
            padding: 5px;
        }

        .tooltip.top .tooltip-arrow {
            border-top-color: #343a40;
        }
         .action-buttons a {
            margin-right: 10px;
        }
        .action-column {
            width: 150px; /* Đặt kích thước cho cột Actions */
        }

    </style>
</head>
<body>
    <jsp:include page="header.jsp"></jsp:include>
        <div class="container">
            <h1>News Manager</h1>
            <button type="button" class="btn btn-success mb-3" data-toggle="modal" data-target="#createNewModal">Create News</button>
            <table class="table table-bordered table-striped">
                <thead class="thead-dark">
                    <tr>
                        <th scope="col">News ID</th>
                        <th scope="col">Publication Date</th>
                        <th scope="col">Image</th>
                        <th scope="col">Title</th>
                        <th scope="col">Content</th>
                        <th scope="col">Author</th>
                        <th scope="col" class="action-column">Actions</th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach var="news" items="${newsList}">
                    <tr>
                        <td>${news.newsId}</td>
                        <td>${news.publishedDate}</td>
                        <td><img src="${news.imageUrl}" alt="News Image" class="img-fluid" style="width:100px;height:100px;"></td>
                        <td>${news.title}</td>
                        <td>${news.content}</td>
                        <td>${news.userId}</td>
                        <td class="action-buttons" >
                            <a href="LoadNews?newsId=${news.newsId}" class="edit" >
                                <i class="material-icons" data-toggle="tooltip" title="Edit">&#9998;</i>
                            </a>
                            <a href="DeleteNews?newsId=${news.newsId}" class="DeleteNews" ><i class="material-icons" data-toggle="tooltip" title="Delete">&#x1F5D1;</i></a>
                        </td>
                    </tr>
                </c:forEach>
                <!-- Add more rows for additional news items -->
            </tbody>
        </table>
    </div>

    <!-- Create New Modal -->
    <div class="modal fade" id="createNewModal" tabindex="-1" role="dialog" aria-labelledby="createNewModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <form id="newsForm" action="AddNews" method="Post">
                    <div class="modal-header">
                        <h5 class="modal-title" id="createNewModalLabel">Create New News Item</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="form-group">
                            <label for="publicationDate">Publication Date</label>
                            <input type="date" class="form-control"  name="publishedDate" required>
                        </div>


                        <div class="form-group">
                            <label for="image">Image</label>
                            <input type="file" class="form-control-file"  name="imageUrl" accept="image/*" required>
                        </div>
                        <div class="form-group">
                            <label for="title">Title</label>
                            <input type="text" class="form-control" name="title" required >
                        </div>
                        <div class="form-group">
                            <label for="content">Content</label>
                            <textarea class="form-control"  name="content" rows="3"></textarea>
                        </div>


                        <div class="form-group">
                            <label for="author">Author</label>
                            <input type="text" class="form-control"  name="userId" placeholder="User ID" required
                                   value="${sessionScope.account.getUser_id()}" readonly>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                        <button type="submit" class="btn btn-primary">Save</button>
                    </div>
                    <!-- Modal for success message -->
                    <div class="modal fade" id="successModal" tabindex="-1" role="dialog" aria-labelledby="successModalLabel" aria-hidden="true">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="successModalLabel">Success</h5>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <div class="modal-body">
                                    News created successfully!
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>


    <jsp:include page="footer.jsp"></jsp:include> 
    <!-- Bootstrap JS and jQuery


    

    <!-- Bootstrap JS and jQuery -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

    <script>
    document.addEventListener('DOMContentLoaded', function() {
        var form = document.getElementById('newsForm');

        form.addEventListener('submit', function(event) {
            var publishedDate = form.elements['publishedDate'].value;
            var title = form.elements['title'].value;
            var content = form.elements['content'].value;
            
            var errorMessages = [];
            
            // Kiểm tra nếu không có publishedDate
            if (!publishedDate) {
                errorMessages.push("Published Date is required.");
            } else {
                // Tạo đối tượng Date từ chuỗi publishedDate
                var selectedDate = new Date(publishedDate);
                var today = new Date(); // Ngày hôm nay

                // So sánh ngày được chọn và ngày hôm nay
                if (selectedDate.toISOString().split('T')[0] !== today.toISOString().split('T')[0]) {
                    errorMessages.push("Published Date must be today.");
                }
            }

            if (title.length < 25 || title.length > 100) {
                errorMessages.push("Title must be between 25 and 100 characters.");
            }

            if (content.length < 25 || content.length > 10000) {
                errorMessages.push("Content must be between 25 and 10000 characters.");
            }

            if (errorMessages.length > 0) {
                event.preventDefault(); // Prevent form submission
                alert(errorMessages.join("\n")); // Hiển thị thông báo lỗi
            } else {
                // Nếu không có lỗi, hiển thị modal success
                $('#successModal').modal('show'); // Hiển thị modal

                // Thiết lập thời gian giữ modal trước khi tự động đóng lại (1 phút)
                setTimeout(function() {
                    $('#successModal').modal('hide'); 
                }, 240000); 
            }
        });
    });
</script>



</body>
</html>