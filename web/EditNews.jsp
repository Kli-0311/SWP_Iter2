<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit News</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <!-- Custom CSS -->
    <style>
        body {
            background-color: #f8f9fa; /* Màu nền cho trang */
        }
        .modal-dialog {
            max-width: 800px;
            margin: 1.75rem auto;
        }
        .modal-content {
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 20px rgba(0,0,0,0.1); /* Đổ bóng cho modal */
        }
        .form-group {
            margin-bottom: 20px;
        }
        .modal-title {
            color: #007bff; /* Màu chữ cho tiêu đề modal */
        }
    </style>
    
</head>
<body>

<jsp:include page="header.jsp"></jsp:include>

<div class="container">
    <div class="table-wrapper">
        <div class="table-title">
            <div class="row">
                <div class="col-sm-6">
                    <h2>Edit <b>News</b></h2>
                </div>
            </div>
        </div>
    </div>

    <div id="editNewsModal">
        <div class="modal-dialog">
            <div class="modal-content">
                <form action="EditNews" method="Post" id="editForm">
                    <div class="modal-header bg-light">
                        <h4 class="modal-title text-primary">Edit News</h4>
                        <a href="NewsManage" class="close" aria-hidden="true">&times;</a>
                    </div>
                    <div class="modal-body">
                        <div class="form-group">
                            <label for="newsId">News ID</label>
                            <input value="${load.newsId}" name="newsId" type="text" class="form-control" readonly>
                    </div>
                        
                        <div class="form-group">
                            <label for="publishedDate">Published Date</label>
                            <input value="${load.publishedDate}" name="publishedDate" type="date" class="form-control" required>
                        </div>
                        <div class="form-group">
                            <label for="imageUrl">Image URL</label>
                            <input value="${load.imageUrl}" name="imageUrl" type="file" class="form-control" required>
                        </div>
                        <div class="form-group">
                            <label for="title">Title</label>
                            <input value="${load.title}" name="title" type="text" class="form-control" required>
                        </div>
                        <div class="form-group">
                            <label for="content">Content</label>
                            <textarea name="content" class="form-control" rows="5" required>${load.content}</textarea>
                        </div>
                        <div class="form-group">
                            <label for="userId">User ID</label>
                            <input value="${load.userId}" name="userId" type="text" class="form-control"  readonly>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <input type="submit" class="btn btn-success" value="SaveChange">
                        <a href="NewsManage" class="btn btn-info">Close</a>
                    </div>
                </form>
            </div>
        </div>
    </div>

</div>

<jsp:include page="footer.jsp"></jsp:include>

<!-- Bootstrap JS and jQuery -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script>
    document.addEventListener('DOMContentLoaded', function() {
        var form = document.getElementById('editForm');

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
                if (selectedDate.toISOString().split('T')[0] !== today.toISOString().split('T')[0]) // Chuyển selectedDate thành chuỗi ở định dạng ISO (ví dụ: "2024-06-21T00:00:00.000Z"), sau đó tách chuỗi này bằng ký tự 'T' và lấy phần đầu tiên (ngày) (ví dụ: "2024-06-21").
                {
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
                
            }
        });
    });
</script>
</body>
</html>
