<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <c:if test="${flush !=null}">
            <div id="flush_success">
                <c:out value="${flush }">
                </c:out>
            </div>
        </c:if>
        <h2>日報管理システムへようこそ</h2>
        <nav>
            <ul>
                <c:if test="${sessionScope.login_employee.admin_flag!=0}">
                    <li><a href="<c:url value='/employees/index'/>">従業員管理</a></li>
                </c:if>
                <li><a href="<c:url value='/reports/index'/>">日報管理</a></li>
                <li><a href="<c:url value='/timecards/index'/>">タイムカード</a></li>
            </ul>
        </nav>

    </c:param>
</c:import>