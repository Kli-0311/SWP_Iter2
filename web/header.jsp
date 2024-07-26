<%-- 
    Document   : header
    Created on : May 24, 2024, 1:10:16 PM
    Author     : ADMIN
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page </title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" 
              integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <link rel="stylesheet" href="css/headerAndFooterr.css">

        <style>
            * {
                margin: 0;
                padding: 0;
                box-sizing: border-box;
            }

            .header {
                display: flex;
                justify-content: space-between;
                align-items: center;
                min-height: 70px;
                width: 100%;
                background-color: #F05123;
                padding: 0 20px;
            }

            .fa-cube, .fa-user, .fa-right-to-bracket{
                color: white;
                font-size: 25px;
                margin: 0 10px;
            }

            .header-logo {
                display: flex;
                align-items: center;
                color: white;
                font-size: 20px;
            }

            .header-nav {
                display: flex;
                align-items: center;
            }

            .header-nav-list {
                display: flex;
                list-style: none;
                margin-bottom: 0px;
            }

            .header-nav-list-item {
                margin: 0 20px;
            }

            .header-logo a, .header-nav-list-item a, .header-avt-login a {
                text-decoration: none;
                color: white;
                font-size: 20px;
            }

            .fa-bars {
                color: #f27125;
            }

            .header-avt {
                display: flex;
                align-items: center;
                justify-content: right;
                position: relative;
            }

            .header-avt-setting:hover .header-avt-option {
                display: block;
                opacity: 1;
                transform: translateY(0);
                transition: opacity 0.5s ease-in-out, transform 0.5s ease-in-out;
            }

            .header-avt-setting::before{
                /*background-color: red;*/
                position: absolute;
                width: 45px;
                height: 40px;
                content: "";
                display: block;
                top: 30px;
                right: 10px;
            }

            .header-avt-option {
                background-color: white;
                position: absolute;
                right: 10px;
                top: 40px;
                min-width: 100px;
                list-style: none;
                box-shadow: 0 1px 5px gray;
                padding-left: 0;
                border-radius: 3px;
                overflow: hidden;
                margin-top: 15px;
                display: block;
                opacity: 0;
                transform: translateY(-10px);
                transition: opacity 0.5s ease-in-out, transform 0.2s ease-in-out;
            }


            .header-avt-option-item{
                background-color: var(--white-color);
                padding: 8px 8px;
                margin-top: 0px;
            }

            .header-avt-option a{
                text-decoration: none;
                color: black;
            }

            .header-avt-option-item:hover{
                cursor: pointer;
                background-color: rgb(212, 212, 212);
            }

            .menu {
                position: fixed;
                z-index: 9999;
            }

            .nav_links {
                position: fixed;
                top: 0;
                left: 0;
                background-color: white;
                width: 0;
                height: 100vh;
                justify-content: center;
                display: flex;
                flex-direction: column;
                overflow-x: hidden;
                transition: width 0.5s ease;
            }

            .nav_links.active {
                width: 350px;
            }

            .nav_links ul {
                display: flex;
                flex-direction: column;
                align-items: center;
                list-style: none;
                padding-left: 0;
            }

            .nav_links li {
                margin: 10px 0;
                font-size: 20px;
                color: #f27125;
                font-weight: 700;
                text-transform: uppercase;
            }

            .nav_links li a {
                text-decoration: none;
            }

            .nav-links-item-li a{
                color:#f27125
            }

            .fa-bars {
                position: absolute;
                top: 16px;
                left: 30px;
                z-index: 10000;
            }

            .footer {
                position: static;
                width: 100%;
                background-color: #F05123;
                color: white;
                text-align: center;
                padding: 10px;
            }
        </style>
    </head>
    <body>
        <!-- Header -->
        <div class="header">
            <div class="header-logo">
                <a href="home.jsp"><i class="fa-solid fa-cube"></i> DX-LAB</a>
            </div>
            <c:if test="${sessionScope.account == null}">
                <div class="header-nav">
                    <ul class="header-nav-list">
                        <li class="header-nav-list-item"><a href="home.jsp">Home</a></li>
                        <!--<li class="header-nav-list-item"><a href="manageRecruiment?service=viewRecruiment">Recruitment</a></li>-->
                        <li class="header-nav-list-item"><a href="">Notification</a></li>
                        <li class="header-nav-list-item"><a href="">News</a></li>
                    </ul>
                </div>
            </c:if>

            <c:if test="${sessionScope.account != null}">
                <div class="header-nav">
                    <c:if test="${sessionScope.account.role_id == 1}">
                        <ul class="header-nav-list">
                            <li class="header-nav-list-item"><a href="home.jsp">Home</a></li>
                            <li class="header-nav-list-item"><a href="viewRecruiment">Recruitment</a></li>
                            <li class="header-nav-list-item"><a href="interviewScheduleManage">Interview Schedule</a></li>
                            <!--<li class="header-nav-list-item"><a href="">Notifications</a></li>-->
                            <li class="header-nav-list-item"><a href="ViewNews">News</a></li>
                        </ul>
                    </c:if>
                    <c:if test="${sessionScope.account.role_id == 2}">
                        <ul class="header-nav-list">
                            <li class="header-nav-list-item"><a href="home.jsp">Home</a></li>
                            <li class="header-nav-list-item"><a href="viewRecruiment">Recruitment</a></li>
                            <li class="header-nav-list-item"><a href="interviewScheduleManage">Interview Schedule</a></li>
                            <!--<li class="header-nav-list-item"><a href="">Notifications</a></li>-->
                            <li class="header-nav-list-item"><a href="ViewNews">News</a></li>
                        </ul>
                    </c:if>
                    <c:if test="${sessionScope.account.role_id == 3}">
                        <ul class="header-nav-list">
                            <li class="header-nav-list-item"><a href="home.jsp">Home</a></li>
                            <li class="header-nav-list-item"><a href="viewRecruiment">Recruitment</a></li>
                            <li class="header-nav-list-item"><a href="interviewScheduleManage">Interview Schedule</a></li>
                            <!--<li class="header-nav-list-item"><a href="">Notifications</a></li>-->
                            <li class="header-nav-list-item"><a href="ViewNews">News</a></li>
                        </ul>
                    </c:if>
                    <c:if test="${sessionScope.account.role_id == 6}">
                        <ul class="header-nav-list">
                            <li class="header-nav-list-item"><a href="home.jsp">Home</a></li>
                            <li class="header-nav-list-item"><a href="viewRecruiment">Recruitment</a></li>
                            <li class="header-nav-list-item">
                                <a href="viewInterviewSchedule">Interview Schedule</a>
                            </li>
                            <li class="header-nav-list-item"><a href="">Notifications</a></li>
                            <li class="header-nav-list-item"><a href="ViewNews">News</a></li>
                        </ul>
                    </c:if>

                    <c:if test="${sessionScope.account.role_id == 5}">
                        <ul class="header-nav-list">
                            <li class="header-nav-list-item"><a href="home.jsp">Home</a></li>
                            <li class="header-nav-list-item"><a href="viewRecruiment">Recruitment</a></li>
                            <li class="header-nav-list-item">
                                <a href="viewNotificationByIntern?userId=${sessionScope.account.user_id}">Notifications</a></li>
                            <li class="header-nav-list-item"><a href="ViewNews">News</a></li>
                        </ul>
                    </c:if>
                    <c:if test="${sessionScope.account.role_id == 4}">
                        <ul class="header-nav-list">
                            <li class="header-nav-list-item"><a href="home.jsp">Home</a></li>
                            <li class="header-nav-list-item"><a href="viewRecruiment">Recruitment</a></li>
                            <li class="header-nav-list-item">
                                <a href="viewInterviewScheduleByMentor">Interview Schedule</a>
                            </li>
                            <!--                            <li class="header-nav-list-item">
                                                            <a href="viewNotificationByIntern?userId=${sessionScope.account.user_id}">Notifications</a></li>-->
                            <li class="header-nav-list-item"><a href="ViewNews">News</a></li>
                        </ul>
                    </c:if>
                </div>
            </c:if>


            <c:if test="${sessionScope.account == null}">
                <div class="header-avt">
                    <div class="header-avt-login"><a href="login.jsp">Login</a></div>
                    <div class="header-avt-avt"><a href="login.jsp"><i class="fa-solid fa-right-to-bracket"></i></a></div>
                </div>
            </c:if>
            <c:if test="${sessionScope.account != null}">
                <div class="header-avt">
                    <a style="font-size: 20px; color: white">${sessionScope.account.full_name} / </a>
                    <a style="font-size: 20px; color: white">${sessionScope.role_name}</a>
                    <div class="header-avt-setting">
                        <div class="header-avt-avt"><i class="fa-solid fa-user"></i>
                            <ul class="header-avt-option">
                                <a href="viewProfile.jsp"><li class="header-avt-option-item">User Profile</li></a>
                                <a href="updateProfile.jsp"><li class="header-avt-option-item">Update User Profile</li></a>
                                <a href="changePassword.jsp"><li class="header-avt-option-item">Change Password</li></a>
                                <a href="#" onclick="doLogOut()"><li class="header-avt-option-item">Logout</li></a>
                            </ul>
                        </div>
                    </div>
                </div>
            </c:if>

        </div>

        <!-- Menu -->
        <c:if test="${sessionScope.account != null}">
            <div class="menu">
                <i class="fa-solid fa-bars" aria-hidden="true" style="font-size: 30px;cursor: pointer;"></i>
                <div class="nav_links" id="navLinks">
                    <c:if test="${sessionScope.account.role_id == 6}">
                        <ul class="nav-links-item">
                            <li class="nav-links-item-li"><a href="home.jsp">Home Candidate</a></li>
                            <li class="nav-links-item-li"><a href="">Recruitment</a></li>
                            <li class="nav-links-item-li"><a href="">Notification</a></li>
                            <li class="nav-links-item-li"><a href="">News</a></li>
                        </ul>
                    </c:if>
                    <c:if test="${sessionScope.account.role_id == 5}">
                        <ul class="nav-links-item">
                            <li class="nav-links-item-li" ><a href="home.jsp">Home intern</a></li>
                            <li class="nav-links-item-li" ><a href="viewProject?user_id=${sessionScope.account.user_id}">View Project</a></li>
                            <li class="nav-links-item-li" ><a href="viewReport?user_id=${sessionScope.account.user_id}">Submit Report</a></li>
                            <li class="nav-links-item-li" ><a href="viewSchedule.jsp">View Schedule</a></li>
                            <li class="nav-links-item-li" ><a href="Certificate?user_id=${sessionScope.account.user_id}">View Certificate</a></li>
                            <li class="nav-links-item-li" ><a href="ViewMember?user_id=${sessionScope.account.user_id}">View Member</a></li>
                            <li class="nav-links-item-li"><a href="sendQuestion?user_id=${sessionScope.account.user_id}">Ask mentor</a></li>
                            <li class="nav-links-item-li"><a href="askMentor?user_id=${sessionScope.account.user_id}">List Question</a></li>
                            <li class="nav-links-item-li" ><a href="ViewAttendanceList?user_id=${sessionScope.account.user_id}">View Attendance</a></li>
                            <li class="nav-links-item-li" ><a href="ViewGradeByInterns">View Grade</a></li>

                        </ul>
                    </c:if>
                    <c:if test="${sessionScope.account.role_id == 4}">
                        <ul class="nav-links-item">
                            <li class="nav-links-item-li"><a href="home.jsp">Home Mentor</a></li>
                            <li class="nav-links-item-li"><a href="viewUserAccount?service=ViewListCandidate">Candidate Apply</a></li>
                            <li class="nav-links-item-li"><a href="myProject?userId=${sessionScope.account.user_id}">My Project</a></li>
                            <li class="nav-links-item-li" ><a href="ViewCetificatebyMentor?user_id=${sessionScope.account.user_id}">Certificate Manage</a></li>
                            <li class="nav-links-item-li"><a href="ViewReportsMentor">Report Manage </a></li>
                            <li class="nav-links-item-li"><a href="mentorMessages?user_id=${sessionScope.account.user_id}">Mentor Messages</a></li>
                            <li class="nav-links-item-li"><a href="attendanceAndSchedule">Check Attendance</a></li>
                            <li class="nav-links-item-li"><a href="submitRecruiment">Send Recruitment</a></li>
                        </ul>
                    </c:if>
                    <c:if test="${sessionScope.account.role_id == 3}">
                        <ul class="nav-links-item">
                            <li class="nav-links-item-li"><a href="home.jsp">Home HR</a></li>
                            <li class="nav-links-item-li" ><a href="manageRecruiment">Project Manage</a></li>
                            <li class="nav-links-item-li" ><a href="mentorManage">Recruitment Manage</a></li>
                        </ul>
                    </c:if>
                    <c:if test="${sessionScope.account.role_id == 2}">
                        <ul class="nav-links-item">
                            <li class="nav-links-item-li"><a href="home.jsp">Home Lab manage</a></li>
                            <li class="nav-links-item-li"><a href="NewsManage"> News Manage</a></li>
                            <li class="nav-links-item-li"><a href="gradeManage">Grade Manage</a></li>
                            <li class="nav-links-item-li"><a href="UsersInformation">User Information</a></li>
                        </ul>
                    </c:if>
                    <c:if test="${sessionScope.account.role_id == 1}">
                        <ul class="nav-links-item">
                            <li class="nav-links-item-li"><a href="home.jsp">Home Admin</a></li>
                            <li class="nav-links-item-li"><a href="createAccount.jsp">Create Account</a></li>
                            <li class="nav-links-item-li"><a href="createAccountCandidate.jsp">Create Account Candidate</a></li>
                            <li class="nav-links-item-li"><a href="ViewUserInfor.jsp">View User Information</a></li>
                            <li class="nav-links-item-li"><a href="viewAccount">View Account</a></li>
                            <li class="nav-links-item-li"><a href="viewCreateInternSchedule">Create Intern Schedule</a></li>
                        </ul>
                    </c:if>
                </div>
            </div>
        </c:if>
        <script>
            document.addEventListener('DOMContentLoaded', function () {
                const menuClick = document.querySelector('.fa-bars');
                const navLinks = document.getElementById('navLinks');
                menuClick.addEventListener('click', function () {
                    navLinks.classList.toggle('active');
                    if (navLinks.classList.contains('active')) {
                        document.body.style.overflow = 'hidden';
                    } else {
                        document.body.style.overflow = '';
                    }
                });
            });

            function doLogOut() {
                if (confirm("Are you sure you want to log out?")) {
                    window.location.href = "logout";
                }
            }
        </script>
    </body>
</html>

