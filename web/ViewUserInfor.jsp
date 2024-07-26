<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Account Management</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
        <style>
            .sort-icon {
                cursor: pointer;
                margin-left: 5px;
                color: #007bff; /* Màu mặc định */
                transition: color 0.3s ease; /* Hiệu ứng chuyển động */
            }
            .sort-icon:hover {
                color: #0056b3; /* Màu khi di chuột vào */
            }
            .sort-icon.active {
                color: #fff !important; /* Màu khi active */
            }

            .view-list {
                font-size: 18px;
                color: #F3A99C; /* Màu cho chữ View List */
                transition: color 0.3s ease; /* Hiệu ứng chuyển động */
            }

            .view-list:hover {
                color: #FB745C; /* Màu khi di chuột vào, ở đây là trắng */
                text-decoration: none; /* Bỏ gạch chân */
            }

            .btn-white {
                background-color: #ffffff; /* Màu nền trắng */
                color: #007bff; /* Màu chữ xanh dương */
                border-color: #007bff; /* Màu viền xanh dương */
            }

            .btn-white:hover {
                background-color: #f8f9fa; /* Màu nền khi di chuột vào */
                color: #0056b3; /* Màu chữ khi di chuột vào */
                border-color: #0056b3; /* Màu viền khi di chuột vào */
            }

            .search-form {
                justify-content: flex-end; /* Đưa form tìm kiếm sang phải */
            }
        </style>
    </head>
    <body>
        <jsp:include page="header.jsp" />

        <div class="container mt-4">
            <div class="row">
                <div class="col-md-12">

                    <!-- Search Form -->
                    <form class="form-inline mb-3 search-form" action="searchUser" method="get">
                        <div class="input-group col-md-6">
                            <input class="form-control" type="search" placeholder="Search " aria-label="Search" name="username">
                            <div class="input-group-append">
                                <button class="btn btn-outline-success" type="submit">Search</button>
                            </div>
                        </div>
                    </form>

                    <!-- List Buttons -->
                    <div class="container">
                        <div class="row">
                            <div class="col-md-3">
                                <a class="btn btn-block btn-white my-3 py-3 text-center view-link" href="viewUserInfor?service=ViewListHR">
                                    <strong class="view-list">View List HR</strong>
                                </a>
                            </div>
                            <div class="col-md-3">
                                <a class="btn btn-block btn-white my-3 py-3 text-center view-link" href="viewUserInfor?service=ViewListMentor">
                                    <strong class="view-list">View List Mentor</strong>
                                </a>
                            </div>
                            <div class="col-md-3">
                                <a class="btn btn-block btn-white my-3 py-3 text-center view-link" href="viewUserInfor?service=ViewListIntern">
                                    <strong class="view-list">View List Intern</strong>
                                </a>
                            </div>
                            <div class="col-md-3">
                                <a class="btn btn-block btn-white my-3 py-3 text-center view-link" href="viewUserInfor?service=ViewListCandidate">
                                    <strong class="view-list">View List Candidate</strong>
                                </a>
                            </div>
                        </div>
                    </div>


                    <!-- List Tables -->
                    <c:if test="${not empty listHR}">
                        <div class="container">
                            <h1 class="table-header">List HR</h1>
                            <div class="table-responsive">
                                <table id="hrTable" class="table table-bordered table-hover">
                                    <thead class="thead-dark">
                                        <tr>
                                            <th>User ID <i class="sort-icon fas fa-sort" onclick="sortTable('hrTable', 0)"></i></th>
                                            <th>Full Name <i class="sort-icon fas fa-sort" onclick="sortTable('hrTable', 1)"></i></th>
                                            <th>Date Of Birth</th>
                                            <th>Gender</th>
                                            <th>Phone Number</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${listHR}" var="p">
                                            <tr>
                                                <td>${p.user_id}</td>
                                                <td>${p.full_name}</td>
                                                <td>${p.dob}</td>
                                                <td>${p.gender}</td>
                                                <td>${p.phone_number}</td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </c:if>

                    <c:if test="${not empty listMentor}">
                        <div class="container">
                            <h1 class="table-header">List Mentor</h1>
                            <div class="table-responsive">
                                <table id="mentorTable" class="table table-bordered table-hover">
                                    <thead class="thead-dark">
                                        <tr>
                                            <th>User ID <i class="sort-icon fas fa-sort" onclick="sortTable('mentorTable', 0)"></i></th>
                                            <th>Full Name <i class="sort-icon fas fa-sort" onclick="sortTable('mentorTable', 1)"></i></th>
                                            <th>Date Of Birth</th>
                                            <th>Gender</th>
                                            <th>Phone Number</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${listMentor}" var="p">
                                            <tr>
                                                <td>${p.user_id}</td>
                                                <td>${p.full_name}</td>
                                                <td>${p.dob}</td>
                                                <td>${p.gender}</td>
                                                <td>${p.phone_number}</td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </c:if>

                    <c:if test="${not empty listIntern}">
                        <div class="container">
                            <h1 class="table-header">List Intern</h1>
                            <div class="table-responsive">
                                <table id="internTable" class="table table-bordered table-hover">
                                    <thead class="thead-dark">
                                        <tr>
                                            <th>User ID <i class="sort-icon fas fa-sort" onclick="sortTable('internTable', 0)"></i></th>
                                            <th>Full Name <i class="sort-icon fas fa-sort" onclick="sortTable('internTable', 1)"></i></th>
                                            <th>Date Of Birth</th>
                                            <th>Gender</th>
                                            <th>Phone Number</th>
                                            <th>Specialization</th>
                                            <th>Certificate</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${listIntern}" var="p">
                                            <tr>
                                                <td>${p.user_id}</td>
                                                <td>${p.full_name}</td>
                                                <td>${p.dob}</td>
                                                <td>${p.gender}</td>
                                                <td>${p.phone_number}</td>
                                                <td>${p.specialization}</td>
                                                <td>${p.certificate}</td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </c:if>

                    <c:if test="${not empty listCandidate}">
                        <div class="container">
                            <h1 class="table-header">List Candidate</h1>
                            <div class="table-responsive">
                                <table id="candidateTable" class="table table-bordered table-hover">
                                    <thead class="thead-dark">
                                        <tr>
                                            <th>User ID <i class="sort-icon fas fa-sort" onclick="sortTable('candidateTable', 0)"></i></th>
                                            <th>Full Name <i class="sort-icon fas fa-sort" onclick="sortTable('candidateTable', 1)"></i></th>
                                            <th>Date Of Birth</th>
                                            <th>Gender</th>
                                            <th>Phone Number</th>
                                            <th>Specialization</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${listCandidate}" var="p">
                                            <tr>
                                                <td>${p.user_id}</td>
                                                <td>${p.full_name}</td>
                                                <td>${p.dob}</td>
                                                <td>${p.gender}</td>
                                                <td>${p.phone_number}</td>
                                                <td>${p.specialization}</td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </c:if>

                </div>
            </div>
        </div>


        <script>
            function sortTable(tableId, columnIndex) {
                var table, rows, switching, i, x, y, shouldSwitch, sortOrder;
                table = document.getElementById(tableId);
                switching = true;
                // Set the sorting order based on the current icon class
                var sortIcon = table.getElementsByTagName("th")[columnIndex].querySelector(".sort-icon");
                if (sortIcon.classList.contains("fa-sort")) {
                    sortOrder = 'asc';
                } else if (sortIcon.classList.contains("fa-sort-up")) {
                    sortOrder = 'desc';
                } else if (sortIcon.classList.contains("fa-sort-down")) {
                    sortOrder = 'asc'; // Special case, when already sorted descending, clicking again will revert to ascending
                }

                while (switching) {
                    switching = false;
                    rows = table.rows;
                    for (i = 1; i < (rows.length - 1); i++) {
                        shouldSwitch = false;
                        x = rows[i].getElementsByTagName("TD")[columnIndex];
                        y = rows[i + 1].getElementsByTagName("TD")[columnIndex];
                        if (sortOrder === 'asc') {
                            if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {
                                shouldSwitch = true;
                                break;
                            }
                        } else if (sortOrder === 'desc') {
                            if (x.innerHTML.toLowerCase() < y.innerHTML.toLowerCase()) {
                                shouldSwitch = true;
                                break;
                            }
                        }
                    }
                    if (shouldSwitch) {
                        rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
                        switching = true;
                    }
                }
                // Toggle sort order icon
                toggleSortIcon(sortIcon);
            }

            function toggleSortIcon(icon) {
                // Reset all icons to default
                var icons = document.querySelectorAll(".sort-icon");
                icons.forEach(function (icon) {
                    icon.classList.remove("fa-sort-up");
                    icon.classList.remove("fa-sort-down");
                    icon.classList.add("fa-sort");
                });

                // Set the clicked icon to the correct state
                if (icon.classList.contains("fa-sort")) {
                    icon.classList.remove("fa-sort");
                    icon.classList.add("fa-sort-up");
                } else if (icon.classList.contains("fa-sort-up")) {
                    icon.classList.remove("fa-sort-up");
                    icon.classList.add("fa-sort-down");
                } else if (icon.classList.contains("fa-sort-down")) {
                    icon.classList.remove("fa-sort-down");
                    icon.classList.add("fa-sort-up");
                }
            }
        </script>

    </body>
</html>
