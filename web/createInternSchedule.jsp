<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Create Intern Schedule</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
        <style>
            .table-header {
                margin: 20px 0;
            }
            .sort-icon {
                cursor: pointer;
            }
        </style>
        <script>
            function toggle(source, name) {
                checkboxes = document.getElementsByName(name);
                for (var i = 0, n = checkboxes.length; i < n; i++) {
                    checkboxes[i].checked = source.checked;
                }
            }

            function prepareData() {
                var selectedCandidates = [];
                var checkboxes = document.getElementsByName('user_id_no_schedule');
                for (var i = 0; i < checkboxes.length; i++) {
                    if (checkboxes[i].checked) {
                        selectedCandidates.push(checkboxes[i].value);
                    }
                }
                document.getElementById('selectedCandidates').value = selectedCandidates.join(',');
                return true;
            }
        </script>
    </head>
    <body>
        <%@ include file="header.jsp" %>
        <div class="container">
            <h3 class="table-header">List of Candidates Without Internship Schedule</h3>
            <form action="createInternSchedule" method="post" onsubmit="return prepareData()">
                <div class="table-responsive">
                    <table id="candidateTableNoSchedule" class="table table-bordered table-hover">
                        <thead class="thead-dark">
                            <tr>
                                <th><input type="checkbox" onClick="toggle(this, 'user_id_no_schedule')" /> Select All</th>
                                <th>User ID</th>
                                <th>Full Name</th>
                                <th>Date Of Birth</th>
                                <th>Gender</th>
                                <th>Phone Number</th>
                                <th>Specialization</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${listCandidateNoSchedule}" var="p">
                                <tr>
                                    <td><input type="checkbox" name="user_id_no_schedule" value="${p.user_id}"></td>
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

                <div class="form-group">
                    <label for="startDate">Start Date:</label>
                    <input type="date" id="startDate" name="startDate" class="form-control" required>
                </div>
                <div class="form-group">
                    <label for="endDate">End Date:</label>
                    <input type="date" id="endDate" name="endDate" class="form-control" required>
                </div>
                <input type="hidden" id="selectedCandidates" name="selectedCandidates">
                <button type="submit" class="btn btn-primary">Create Schedule</button>
            </form>
            <p style="color: red">${error}</p>


            <h3 class="table-header">List of Candidates With Internship Schedule</h3>
            <div class="table-responsive">
                <table id="candidateTableWithSchedule" class="table table-bordered table-hover">
                    <thead class="thead-dark">
                        <tr>
                            <th>User ID</th>
                            <th>Full Name</th>
                            <th>Date Of Birth</th>
                            <th>Gender</th>
                            <th>Phone Number</th>
                            <th>Specialization</th>
                            <th>Start Date</th>
                            <th>End Date</th>
                            <th>Working Days</th>
                            <th>Edit</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${listCandidateWithSchedule}" var="p">
                            <tr>
                                <td>${p.user_id}</td>
                                <td>${p.full_name}</td>
                                <td>${p.dob}</td>
                                <td>${p.gender}</td>
                                <td>${p.phone_number}</td>
                                <td>${p.specialization}</td>
                                <td>${p.start_date}</td>
                                <td>${p.end_date}</td>
                                <td>${p.calculateWorkingDays()}</td> 
                                <td><a href="updateInternSchedule.jsp?user_id=${p.user_id}&full_name=${p.full_name}&start_date=${p.start_date}&end_date=${p.end_date}" class="btn btn-warning">Edit</a></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </body>
</html>
