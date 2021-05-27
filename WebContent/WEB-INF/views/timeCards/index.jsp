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

        <c:choose>
            <c:when test="${timecardstart == null }">
                <form method="POST" action="<c:url value='/timecard/timestart'/>">
                    <button type="submit">出勤</button>
                </form>
            </c:when>

            <c:when test="${timecardfin == null }">
                <form method="POST" action="<c:url value='/timecard/timefin'/>">
                    <button type="submit">退勤</button>
                </form>
            </c:when>
            <c:when test="${timecardfin !=null }">
            <h2>本日の出退勤は登録済みです。</h2>
            </c:when>
        </c:choose>
        <p>
            <a href="<c:url value='/timecard/view'/>">過去のタイムカード一覧</a>
        </p>
        <p>
            <a href="<c:url value='/index.html'/>">トップページに戻る</a>
        </p>
    </c:param>
</c:import>