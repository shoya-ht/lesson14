<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:if test="${flush !=null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>
        <h2>タイムカードシステムへようこそ</h2>
        <form method="POST" action="<c:url value='/timeCard/timestart'/>">
<button type="submit">出勤</button>
</form>
<form method="POST" action="<c:url value='/timeCard/timefin'/>">
<button type="submit">退勤</button>
</form>

        <p>
            <a href="<c:url value='/topPage'/>">トップページに戻る</a>
        </p>

    </c:param>
</c:import>