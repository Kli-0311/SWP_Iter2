<%-- 
    Document   : changeInformation
    Created on : May 26, 2024, 5:40:15 PM
    Author     : ADMIN
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Profile</title>
        <style>
            .container1 {
                display: flex;
                flex-direction: column;
                align-items: center;
                justify-content: center;
                padding: 20px;
                border-radius: 10px;
                margin: 10px auto;
            }

            .changein4 {
                width: 60%;
                margin:0 auto;
                margin-top: 50px;
                background-color: #ffffff;
                padding: 20px;
                box-shadow: 0px 0px 10px rgba(0,0,0,0.1);
                border-radius: 10px;
            }

            .changein4 h1 {
                text-align: center;
            }

            .changein4 input[type="text"],
            .changein4 input[type="date"],
            .changein4 input[type="file"],
            .changein4 input[type="password"]
            {
                width: 100%;
                padding: 10px;
                margin-top: 5px;
                margin-bottom: 15px;
                border: 1px solid #ccc;
                border-radius: 5px;
            }

            .changein4-gender{
                display: flex;
                flex-direction: row;
            }

            label {
                display: inline-block;
                margin-right: 10px;
                font-weight: 700;
            }

            .changein4 input[type="radio"] {
                width: auto;
                margin-right: 5px;
            }

            .changein4 button {
                width: 100%;
                padding: 10px 20px;
                background-color: #202942;
                color: white;
                border: none;
                border-radius: 5px;
                cursor: pointer;
                margin-top: 20px;
            }

            .changein4 button:hover {
                background-color: #1b1e34;
            }
        </style>
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>

            <div class="changein4">
                <form action="updateProfile" method="post" enctype="multipart/form-data">
                    <h1>Update Profile</h1>
                    <hr>
                    <label for="full_name">Full Name</label>
                    <input type="text" id="full_name" name="full_name" placeholder="Full Name" value="${sessionScope.account.full_name}">
                <label for="dob">Date of birth</label>
                <input type="date" id="dob" name="dob" placeholder="Date of Birth" value="${sessionScope.account.dob}">
                <div class="changein4-gender">
                    <label for="male">Male</label>
                    <input type="radio" id="male" name="gender" value="Male" ${sessionScope.account.gender == 'Male' ? 'checked' : ''}>
                    <label for="female">Female</label>
                    <input type="radio" id="female" name="gender" value="Female" ${sessionScope.account.gender == 'Female' ? 'checked' : ''}>
                </div>

                <label for="phone_number">Phone Number</label>
                <input type="text" id="phone_number" name="phone_number" placeholder="Phone Number" value="${sessionScope.account.phone_number}">
                <label for="avatar">Avatar</label>
                <img src="img/${sessionScope.account.avatar}" alt="Current Avatar" style="width: 100px; height: 100px;">
                <input type="text" id="avatar" name="avatar" value="${sessionScope.account.avatar}">
                <input type="file" id="avatar" name="avatarNew">

                <label for="specialization">Specialization</label><br>
                <select name="specialization">
                    <option value="SW NodeJS" ${sessionScope.account.specialization == 'SW NodeJS' ? 'selected' : ''}>SW NodeJS</option>
                    <option value="SW .Net" ${sessionScope.account.specialization == 'SW .Net' ? 'selected' : ''}>SW .Net</option>
                    <option value="SW AI" ${sessionScope.account.specialization == 'SW AI' ? 'selected' : ''}>SW AI</option>
                    <option value="NULL" ${sessionScope.account.specialization == 'NULL' ? 'selected' : ''}>NULL</option>
                </select>
                <button type="submit" class="registerbtn">Done</button>
                <p>${messErrorPhone}</p>
                <p>${messErrorDob}</p>
                <p>${messErrorAvatar}</p>
                <strong>${mess}</strong>
            </form>
        </div>
    </body>
</html>
