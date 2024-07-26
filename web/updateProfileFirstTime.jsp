<%-- 
    Document   : signup
    Created on : May 25, 2024, 3:58:32 PM
    Author     : ADMIN
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/signup.css">

    </head>
    <body>
        <div class="signup-container">
            <h2>Update Profile</h2>
            <form action="updateFirst" method="post" enctype="multipart/form-data">
                <input type="hidden" id="username" name="userName" placeholder="Username" value="${sessionScope.account.username}" required>

<!--                <input type="text" id="fullName" name="fullName" placeholder="Full Name" value="${param.fullName}" required>-->

                <label for="dob">Date of Birth</label>
                <input type="date" id="dob" name="dob" value="${param.dob}" required>

                <div class="gender">
                    <input type="radio" id="male" name="gender" value="Male" ${param.gender == 'Male' ? 'checked' : ''}  required>
                    <label for="male">Male</label>
                    <input type="radio" id="female" name="gender" value="Female" ${param.gender == 'Female' ? 'checked' : ''} required>
                    <label for="female">Female</label>
                </div>

                <input type="text" id="phoneNumber" name="phoneNumber" placeholder="Phone Number" value="${param.phoneNumber}" required>

                <label for="avatar">Avatar</label>
                <input type="file" id="avatar" name="avatar">
                <c:if test="${not empty messErrorAvatar}">
                    <p class="error">${messErrorAvatar}</p>
                </c:if>
                <select id="specialization" name="specialization" required>
                    <option value="">Choose specialization</option>
                    <option value="SW NodeJS" ${param.specialization == 'SW NodeJS' ? 'selected' : ''}>SW NodeJS</option>
                    <option value="SW .Net" ${param.specialization == 'SW .Net' ? 'selected' : ''}>SW .Net</option>
                    <option value="SW AI" ${param.specialization == 'SW AI' ? 'selected' : ''}>SW AI</option>
                </select>

                <input type="password" id="password" name="password" placeholder="Password" required>

                <input type="password" id="repeatPassword" name="repeatPassword" placeholder="Repeat Password" required>

                <input type="submit" value="Update">

                <p>${messErrorPass} </p>
                <p>${messErrorUsername}</p>
                <p>${messErrorPhone}</p>
                <p>${messErrorDob}</p>
                <p>${messErrorAvatar}</p>   
            </form>
        </div>
    </body>
</html>
