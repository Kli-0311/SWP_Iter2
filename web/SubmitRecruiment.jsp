<%-- 
    Document   : SubmitRecruiment
    Created on : Jun 26, 2024, 3:49:52 PM
    Author     : admin
--%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
        <c:if test="${sessionScope.account.role_id == 4}">
            <div class="container-fluid mt-8">
                
                <div class="row d-flex justify-content-around">
                    <a class="btn btn-block btn-primary  col-3  my-3 py-3 text-center" 
                       href="submitRecruiment?service=requestInsert"
                       >
                        Create a new Recruitment
                    </a>
                </div>
                <c:if test="${SubmitDone ne null}">
                    <h3 class="font-weight-semi-bold text-uppercase mb-3 text-center">
                        ${SubmitDone}
                    </h3>
                </c:if>
                <c:if test="${not empty listMessageByMentor}">
                    <div class="col-lg-12 table-responsive mb-5">
                        <table class="table table-bordered text-center mb-0">
                            <thead class="bg-secondary text-dark">
                                <tr>
                                    <th>Image</th>
                                    <th>Title</th>
                                    <th>Recruitment</th>
                                    <th>Start Date</th>
                                    <th>End Date</th>
                                    <th>Status</th>
                                    <th>Delete</th>
                                </tr>
                            </thead>
                            <tbody class="align-middle">
                                <c:forEach items="${listMessageByMentor}" var="view">
                                    <tr>
                                        <td class="align-middle"><img style="width: 100px"src="${view.img}" alt="News Image"></td>
                                        <td class="align-middle">${view.title}</td>
                                        <td class="align-middle">${view.message}</td>
                                        <td class="align-middle">${view.date_start}</td>
                                        <td class="align-middle">${view.date_end}</td>
                                        <td class="align-middle text-danger">${view.status}</td>
                                        <td class="align-middle">
                                            <a href="submitRecruiment?service=delete&messId=${view.messageId}"
                                               onclick="return confirmDelete('${view.messageId}')">Delete</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <script>
                        function confirmDelete(messId) {
                            return confirm("Are you sure you want to delete this Recruitment ( Recruitment ID " + messId + ") ?");
                        }
                    </script>
                </c:if>
                <c:if test="${not empty mess}">
                    <h3 style="color: red" class="font-weight-semi-bold  mb-3 ">
                        ${mess.message}
                    </h3>
                </c:if>

                <c:if test="${submit ne null}">
                    <!--<h3 style="color: red">Dự án của bạn đã được hoàn thành</h3>-->
                    <h3 class="font-weight-semi-bold mb-3 ">
                        Submit Recruitment
                    </h3>
                    <form action="submitRecruiment" method="get" id="submitRecruiment" enctype="multipart/form-data">
                        <input type="hidden" name="service" value="sendInsertDetail" />
                        <div class="mb-3">
                            <input type="hidden" class="form-control" id="send_id" name="send_id" value="${sessionScope.account.user_id}" readonly required>
                        </div>
                        <div class="mb-3">
                            <input type="hidden" class="form-control" id="receiverId" name="receiverId" value="HR001" readonly required>
                        </div>
                        <div class="mb-3">
                            <label for="title" class="form-label">Title</label>
                            <input type="text" class="form-control" id="title" name="title" required>
                        </div><div class="mb-3">
                            <label for="img" class="form-label">Image</label>
                            <input type="file" class="form-control" id="img" name="img" required>
                        </div>
                        <div class="mb-3">
                            <label for="date_start" class="form-label">Start Date</label>
                            <input type="date" class="form-control" id="date_start" name="date_start" required>
                        </div>
                        <div class="mb-3">
                            <label for="date_end" class="form-label">End Date</label>
                            <input type="date" class="form-control" id="date_end" name="date_end" required>
                        </div>
                        <div class="mb-3">
                            <label for="message" class="form-label">Recruitment</label>
                            <textarea style="height: 300px" type="textarea" class="form-control" id="message" name="message" placeholder="Write something..." required></textarea>
                        </div>
                        <div class="mb-3">
                            <input type="hidden" class="form-control" id="status" name="status" value="Pending" readonly required>
                        </div>
                        <div class="text-center">
                            <button type="submit" class="btn btn-primary">Submit</button>
                        </div>
                    </form>
                </c:if>
            </div>
        </c:if>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" 
                integrity="sha384-Vk1pRXs9kaQwgj/+4dcABGpK/Ko1y9lOjp1z7ieQ5LRUjAg3BoR1GIVCNUyHfxiN" 
        crossorigin="anonymous"></script>
    </body>
</html>
