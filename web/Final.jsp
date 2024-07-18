<%-- 
    Document   : final
    Created on : Jul 15, 2024, 3:45:01 AM
    Author     : admin
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
             <style>
            .container {
                width: 100%;
                max-width: 800px;
                margin: 0 auto;
                padding: 20px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                background-color: #f9f9f9;
            }
            table {
                width: 100%;
                border-collapse: collapse;
                margin-top: 20px;
            }
            table, th, td {
                border: 1px solid black;
            }
            th, td {
                padding: 8px;
                text-align: left;
            }
            th {
                background-color: #f2f2f2;
            }
            .text-center {
                text-align: center;
            }
            input[type="submit"] {
                display: block;
                width: 100%;
                padding: 10px;
                background-color: #4CAF50;
                color: white;
                border: none;
                cursor: pointer;
            }
            input[type="submit"]:hover {
                background-color: #45a049;
            }
        </style>
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
        <c:if test="${sessionScope.account.role_id == 4}">
            <div class="container">
                <h3 class="text-center">Grade report for ${fullName}</h3>
                <c:if test="${done ne null}">
                    <h3 class="font-weight-semi-bold text-uppercase mb-3 text-center">
                        ${done}
                    </h3>
                </c:if>
                <c:choose>
                    <c:when test="${finalEvaluation == null}">
                        <form action="evaluateManage" method="get">
                            <input type="hidden" name="service" value="InsertFinal"/>
                            <input type="hidden" name="internId" value="${internId}"/>
                            <input type="hidden" name="mentorid" value="${sessionScope.account.user_id}"/>
                            <input type="hidden" name="type" value="Final"/>
                            <input type="hidden" name="projectCode" value="${intern.projectCode}"/>
                            <input type="hidden" name="positionCode" value="${intern.positionCode}"/>
                            <table>
                                <tr>
                                    <th>GRADE CATEGORY</th>
                                    <th>GRADE ITEM</th>
                                    <th>WEIGHT</th>
                                    <th>VALUE</th>
                                </tr>
                                <tr>
                                    <td>Midterm</td>
                                    <td>Major Knowledge and Skills</td>
                                    <td>30%</td>
                                    <td><input class="w-100" type="number" name="attitude_score" id="attitude_score" oninput="calculateTotals()" value="0" min="0" max="10"></td>
                                </tr>
                                <tr>
                                    <td></td>
                                    <td>Soft Skills</td>
                                    <td>30%</td>
                                    <td><input class="w-100" type="number" name="soft_skills_score" id="soft_skills_score" oninput="calculateTotals()" value="0" min="0" max="10"></td>
                                </tr>
                                <tr>
                                    <td></td>
                                    <td>Attitude</td>
                                    <td>30%</td>
                                    <td><input class="w-100" type="number" name="technical_skills_score" id="technical_skills_score" oninput="calculateTotals()" value="0" min="0" max="10"></td>
                                </tr>
                                <tr>
                                    <td>Total</td>
                                    <td>Total</td>
                                    <td>100%</td>
                                    <td><input class="w-100" type="number" name="total" id="total" readonly></td>
                                </tr>
                            </table>
                            <label>Comment</label>
                            <textarea class="w-100" name="comment"></textarea>
                            <input type="submit" value="Save">
                        </form>
                    </c:when>
                    <c:otherwise>
                        <form action="evaluateManage" method="get">
                            <input type="hidden" name="service" value="UpdateFinal"/>
                            <input type="hidden" name="evaluationId" value="${finalEvaluation.evaluation_id}"/>
                            <input type="hidden" name="projectCode" value="${finalEvaluation.projectCode}"/>
                            <input type="hidden" name="internId" value="${finalEvaluation.internId}"/>

                            <table>
                                <tr>
                                    <th>GRADE CATEGORY</th>
                                    <th>GRADE ITEM</th>
                                    <th>WEIGHT</th>
                                    <th>VALUE</th>
                                </tr>
                                <tr>
                                    <td>Midterm</td>
                                    <td>Major Knowledge and Skills</td>
                                    <td>30%</td>
                                    <td><input class="w-100" type="number" name="attitude_score" id="attitude_score" oninput="calculateTotals()" value="${finalEvaluation.attitude_score}" min="0" max="10"></td>
                                </tr>
                                <tr>
                                    <td></td>
                                    <td>Soft Skills</td>
                                    <td>30%</td>
                                    <td><input class="w-100" type="number" name="soft_skills_score" id="soft_skills_score" oninput="calculateTotals()" value="${finalEvaluation.soft_skills_score}" min="0" max="10"></td>
                                </tr>
                                <tr>
                                    <td></td>
                                    <td>Attitude</td>
                                    <td>30%</td>
                                    <td><input class="w-100" type="number" name="technical_skills_score" id="technical_skills_score" oninput="calculateTotals()" value="${finalEvaluation.technical_skills_score}" min="0" max="10"></td>
                                </tr>
                                <tr>
                                    <td>Total</td>
                                    <td>Total</td>
                                    <td>100%</td>
                                    <td><input class="w-100" type="number" name="total" id="total" value="${finalEvaluation.total_score}" readonly></td>
                                </tr>
                            </table>
                            <label>Comment</label>
                            <textarea class="w-100" name="comment">${finalEvaluation.comment}</textarea>
                            <input type="submit" value="Save">
                        </form>
                    </c:otherwise>
                </c:choose>
            </div>
        </c:if>
        <script>
            function calculateTotals() {
                const midterm1 = parseFloat(document.getElementById('attitude_score').value) || 0;
                const midterm2 = parseFloat(document.getElementById('soft_skills_score').value) || 0;
                const midterm3 = parseFloat(document.getElementById('technical_skills_score').value) || 0;
                const midtermTotal = (midterm1 + midterm2 + midterm3);
                document.getElementById('total').value = midtermTotal.toFixed(2);

            }
        </script>
    </body>
</html>
