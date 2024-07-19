<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Create Account</title>
    <link rel="stylesheet" href="css/createAccount.css">
    <script src="js/scrip.js"></script>
</head>
<body>
    <div class="signup-container">
        <h2>Create an Account</h2>
        <form action="createAccount" method="post">
            <div class="input-container">
                <input type="text" id="user_id" name="user_id" placeholder="User ID" required value="${param.user_id}">
                <span class="validation-icon valid-icon">✔</span>
                <span class="validation-icon invalid-icon">✖</span>
            </div>

            <div class="input-container">
                <input type="text" id="username" name="username" placeholder="Username" required value="${param.username}">
                <span class="validation-icon">✓</span>
            </div>

            <div class="input-container">
                <select name="role_id" required>
                    <option value="1" ${param.role_id == '1' ? 'selected' : ''}>Admin</option>
                    <option value="2" ${param.role_id == '2' ? 'selected' : ''}>Lab Manager</option>
                    <option value="3" ${param.role_id == '3' ? 'selected' : ''}>HR</option>
                    <option value="4" ${param.role_id == '4' ? 'selected' : ''}>Mentor</option>
                </select>
            </div>

            <div class="input-container">
                <input type="text" id="full_name" name="full_name" placeholder="Full Name" required value="${param.full_name}">
                <span class="validation-icon">✔</span>
            </div>

            <div class="input-container">
                <input type="date" id="dob" name="dob" placeholder="Date of Birth" value="${param.dob}">
            </div>

            <div class="input-container gender">
                <input type="radio" id="male" name="gender" value="Male" ${param.gender == 'Male' ? 'checked' : ''} required>
                <label for="male">Male</label>
                <input type="radio" id="female" name="gender" value="Female" ${param.gender == 'Female' ? 'checked' : ''} required>
                <label for="female">Female</label>
            </div>

            <div class="input-container">
                <input type="text" id="phone_number" name="phone_number" placeholder="Phone Number" required value="${param.phone_number}">
                <span class="validation-icon">✔</span>
            </div>

            <div class="input-container">
                <label for="avatar">Avatar</label>
                <input type="file" id="avatar" name="avatar" required>
            </div>

            <div class="input-container">
                <input type="password" id="password" name="password" placeholder="Password" required>
                <span id="password-eye-icon" class="eye-icon" onclick="togglePasswordVisibility('password')">👁</span>
            </div>

            <div class="input-container">
                <input type="password" id="repeatPassword" name="repeatPassword" placeholder="Repeat Password" required>
                <span id="repeatPassword-eye-icon" class="eye-icon" onclick="togglePasswordVisibility('repeatPassword')">👁</span>
                <span id="password-match-icon" class="validation-icon">✔</span> <!-- Biểu tượng xác nhận -->
            </div>

            <input type="submit" value="Create account">

            <c:if test="${not empty errorMessage}">
                <p class="error">${errorMessage}</p>
            </c:if>
            <c:if test="${not empty successMessage}">
                <p class="success">${successMessage}</p>
            </c:if>
            <c:if test="${not empty messErrorUsername}">
                <p class="error">${messErrorUsername}</p>
            </c:if>
            <c:if test="${not empty messErrorDob}">
                <p class="error">${messErrorDob}</p>
            </c:if>
            <c:if test="${not empty messErrorPhone}">
                <p class="error">${messErrorPhone}</p>
            </c:if>
            <c:if test="${not empty messErrorAvatar}">
                <p class="error">${messErrorAvatar}</p>
            </c:if>
            <c:if test="${not empty messErrorPass}">
                <p class="error">${messErrorPass}</p>
            </c:if>

            <!-- Back to Home Link -->
            <div class="links">
                <a href="home.jsp">Back to Home</a>
            </div>
        </form>
    </div>
</body>
</html>
