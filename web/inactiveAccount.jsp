<!-- Name: linhtk -->


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Inactivate Account</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css">
    <style>
        /* Custom styles for this page */
        .container {
            max-width: 600px; /* Limit container width for better readability */
            background: #f8f9fa;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            padding: 20px;
        }

        h1 {
            font-size: 2rem;
            color: #343a40;
        }

        .form-group label {
            font-weight: bold;
        }

        .form-control {
            transition: box-shadow 0.3s ease;
        }

        .form-control:focus {
            box-shadow: 0 0 5px rgba(0, 123, 255, 0.5);
        }

        .btn-primary, .btn-secondary {
            border-radius: 50px;
            padding: 10px 20px;
            transition: background-color 0.3s ease, transform 0.3s ease;
        }

        .btn-primary:hover, .btn-secondary:hover {
            transform: translateY(-2px);
        }

        .btn-primary:active, .btn-secondary:active {
            transform: translateY(2px);
        }
    </style>
</head>
<body>
    <jsp:include page="header.jsp"></jsp:include>

    <div class="container mt-5 animate__animated animate__fadeIn">
        <h1 class="mb-4 text-center">Inactivate Account</h1>
        
        <form action="inactiveAccount" method="post">
            <input type="hidden" name="user_id" value="${param.user_id}">
            <input type="hidden" name="username" value="${param.username}">
            <input type="hidden" name="password" value="${param.password}">
            
            <div class="form-group">
                <label for="username">Email</label>
                <input type="text" class="form-control" id="username" name="username" value="${param.username}" readonly>
            </div>
            
            
            <div class="form-group">
                <label for="is_active">Active Status</label>
                <select class="form-control" id="is_active" name="is_active">
                    <option value="1" ${param.is_active == 1 ? 'selected' : ''}>Active</option>
                    <option value="0" ${param.is_active == 0 ? 'selected' : ''}>Inactive</option>
                </select>
            </div>
            
            <div class="text-center">
                <button type="submit" class="btn btn-primary">Save</button>
                <a href="viewAccount" class="btn btn-secondary">Cancel</a>
            </div>
        </form>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <script>
        $(document).ready(function(){
            // Add focus effect on form controls
            $('.form-control').on('focus', function(){
                $(this).closest('.form-group').addClass('focused');
            }).on('blur', function(){
                $(this).closest('.form-group').removeClass('focused');
            });
        });
    </script>
</body>
</html>
