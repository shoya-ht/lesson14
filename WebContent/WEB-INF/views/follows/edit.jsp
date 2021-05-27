<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">

        <h2>フォロー編集</h2>
        <table>
            <tbody>
                <tr>
                    <th>従業員コード</th>
                    <th>氏名</th>
                    <th>役職</th>
                    <th>操作</th>
                </tr>
                <!-- 全従業員を順に取り出して表示 -->
                <c:forEach var="emp" items="${yesorno}" varStatus="status">
                    <tr class="row${status.count %2 }">
                        <td><c:out value="${emp.employee.code} " /></td>
                        <td><c:out value="${emp.employee.name}" /></td>

                        <td><c:choose>
                                <c:when test="${emp.employee.admin_flag==1}">課長</c:when>
                                <c:when test="${emp.employee.admin_flag==2}">部長</c:when>
                                <c:when test="${emp.employee.admin_flag==3}">管理者</c:when>
                                <c:otherwise>一般</c:otherwise>
                            </c:choose></td>

                        <c:choose>
                        <c:when test="${emp.follow_flg==1}">
                    <td>
                        <a href="#" onclick="forsubmit('formClear_${emp.employee.id}');">解除する</a>
                        <form method="POST" id="formClear_${emp.employee.id}" action="<c:url value='/follow/clear' />">
                        <input type="hidden" name="id" value="${emp.employee.id}"/>
                        </form>
                    </td>
                    </c:when>
                    <c:otherwise>
                    <td>
                        <a href="#" onclick="forsubmit('formRegister_${emp.employee.id}');">登録する</a>
                        <form method="POST" id="formRegister_${emp.employee.id}" action="<c:url value='/follow/register' />">
                        <input type="hidden" name="id" value="${emp.employee.id}"/>
                        </form>
                        </td>
                        </c:otherwise>
                        </c:choose>

                </c:forEach>

            </tbody>
        </table>
        <script>
            function forsubmit(id) {
                document.getElementById(id).submit();
            }
        </script>
    </c:param>
</c:import>