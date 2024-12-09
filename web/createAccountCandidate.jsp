<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Create Candidate Account</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css">
    <style>
        body {
            background-color: #f5f5f5;
            font-family: 'Arial', sans-serif;
        }
        .container {
            background: #fff;
            border-radius: 10px;
            padding: 30px;
            box-shadow: 0px 0px 20px rgba(0, 0, 0, 0.1);
            margin-top: 50px;
        }
        .page-title {
            text-align: center;
            font-size: 2.5rem;
            color: #007bff;
            margin-bottom: 30px;
            animation: fadeInDown 1s forwards;
        }
        .form-section {
            text-align: center;
            animation: fadeInUp 1s forwards;
        }
        .form-section h2 {
            color: #28a745;
            margin-bottom: 20px;
            font-size: 1.8rem;
        }
        .upload-form {
            display: flex;
            justify-content: center;
            align-items: center;
            flex-direction: column;
            animation: fadeIn 1s forwards;
        }
        .upload-form input[type="file"] {
            margin-bottom: 20px;
            padding: 10px;
            border: 1px solid #ced4da;
            border-radius: 5px;
            outline: none;
            width: 250px;
        }
        .upload-form input[type="submit"] {
            padding: 10px 30px;
            border: none;
            border-radius: 5px;
            background-color: #28a745;
            color: #fff;
            cursor: pointer;
            outline: none;
            transition: background-color 0.3s ease;
        }
        .upload-form input[type="submit"]:hover {
            background-color: #218838;
        }
        .back-link {
            margin-top: 20px;
            text-align: center;
        }
        .back-link a {
            color: #007bff;
            text-decoration: none;
        }
        .back-link a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <jsp:include page="header.jsp" />

    <div class="container">
        <h1 class="page-title animate__animated animate__fadeInDown">Create Account for Candidates</h1>

        <div class="form-section">
            <h2>Upload Excel File</h2>
            <div class="upload-form" >
                <form action="createAccountCandidate" method="post" enctype="multipart/form-data">
                    <input type="file" name="file" accept=".xlsx">
                    <br>
                    <input type="submit" value="Upload">
                </form>
            </div>
        </div>

        <div class="back-link">
            <a href="home.jsp">Back to Home</a>
        </div>
    </div>

    <jsp:include page="footer.jsp" />

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
