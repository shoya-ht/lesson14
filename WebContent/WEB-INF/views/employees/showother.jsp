<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:import url="../layout/app.jsp">
    <c:param name="content">
        <c:if test="${flush !=null}">
            <div id="flush_success">
                <c:out value="${flush }"></c:out>
            </div>
        </c:if>
        <h2>全従業員日報一覧</h2>
        <table id="employee_artc">
            <tbody>
                <tr>
                    <th>社員番号</th>
                    <th>氏名</th>
                    <th>日付</th>
                    <th>タイトル</th>
                    <th>操作</th>
                </tr>
                <!-- 1件ごとに表示 1行目不明 -->
                <c:forEach var="employee" items="${employees}" varStatus="status">
                        <td><c:out value="${employee.code}" /></td>
                        <td><c:out value="${employee.name}" /></td>
                        <td><c:out value="${employee.title}" /></td>
                        <td><c:out value="${employee.name}" /></td>
                        <a href="<c:url value='/employees/show?id=${employee.id}'/>">詳細を表示</a>
                </c:forEach>
            </tbody>
        </table>
        <div id="pagination">
        <!-- 1行目不明 -->
            (全${employees_count}件 )<br />
            <c:forEach var="i" begin="1" end="${((employees_count-1)/15)+1 }"
                step="1">
                <c:choose>
                    <c:when test="${i==page }">
                        <c:out value="${i}" />&nbsp;
</c:when>
                    <c:otherwise>
                        <a href="<c:url value='/employees/showother?page="${i}'/>"><c:out
                                value="${i}" /></a>&nbsp;
</c:otherwise>
                </c:choose>
            </c:forEach>
        </div>
    </c:param>
</c:import>