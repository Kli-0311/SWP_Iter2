<%-- 
    Document   : forgotPassword
    Created on : Jun 12, 2024, 3:04:39 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Forgot Password</title>
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Lobster&display=swap" rel="stylesheet">
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f2f2f2;
            }
            .forgot-password-container {
                width: 500px;
                padding: 20px;
                background-color: white;
                margin: 0 auto;
                margin-top: 200px;
                box-shadow: 0px 0px 10px rgba(0,0,0,0.1);
                border-radius: 20px;
            }
            .forgot-password-container h1 {
                text-align: center;
                margin-bottom: 20px;
                font-weight: 500;
            }
            .forgot-password-container input[type="text"] {
                width: 100%;
                padding: 10px;
                margin: 10px 0;
                border: 1px solid #ccc;
                box-sizing: border-box;
                border-radius: 20px;
            }
            .forgot-password-container input[type="submit"] {
                width: 100%;
                padding: 10px;
                background-color: #202942;
                color: white;
                border: none;
                border-radius: 20px;
                cursor: pointer;
            }
            .forgot-password-container input[type="submit"]:hover {
                background-color: rgb(80,80,200);
            }
            .forgot-password-container .links {
                text-align: center;
                margin-top: 20px;
            }
            .forgot-password-container .links a {
                color: #202942;
                text-decoration: none;
            }
            .forgot-password-container .links a:hover {
                color: rgb(80,80,130);
            }

        </style>
    </head>
    <body>
        <div class="forgot-password-container">
            <form action="forgotPasssword" method="post">
                <h1>Forgot Password</h1>
                <input type="text" name="email" placeholder="Email" required style="background-color: white" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$">
                <input type="submit" value="Reset Password">
                <p>${message}</p>

            </form>
            <div class="links">
                <a href="login">Back to Login</a>
            </div>
        </div>
    </body>
</html>


