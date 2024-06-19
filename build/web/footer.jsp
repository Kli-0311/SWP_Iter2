<%-- 
    Document   : footerr
    Created on : May 24, 2024, 2:09:15 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page </title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" 
              integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">

        <style>
            .footer{
                margin: 0 auto;
                display: flex;
                flex-direction: row;
                justify-content: center;
                gap: 200px;
                /*padding-top: 30px;*/
            }

            .left{
                width: 20%;
                text-align: left;
                margin: 10px
            }

            .right{
                width: 20%;
                text-align: left;
                margin: 10px 0 0 50px;
                font-size: 15px;
            }

            h5{
                margin-bottom: 0;
            }
            .links{
                margin-bottom: 10px;
            }
        </style>
    </head>
    <body>
        <footer class="footer">
            <div class="left">
                <h5><Strong>Thông tin liên lạc</Strong></h5>
                <p class="divider">_______________________________________________________</p>
                <p>Địa chỉ: Khu Giáo dục và Đào tạo – Khu Công nghệ cao Hòa Lạc – Km29 Đại lộ Thăng Long, Thạch Thất, TP. HN</p>
                <p>Điện thoại: 0868686868</p>
            </div>
            <div class="right">
                <h5><Strong>Liên kết</Strong></h5>
                <p class="divider">___________________________________________</p>
                <div class="social-icons">
                    <div class="links">
                        <a href="https://facebook.com" target="_blank"><i class="fa-brands fa-facebook"></i> https://www.facebook.com/</a>
                    </div>
                    <div class="links">
                        <a href="https://google.com" target="_blank"><i class="fa-brands fa-google"></i> DX-Lab@gmail.com</a>
                    </div>
                </div>
            </div>
        </footer>
    </body>
</html>
