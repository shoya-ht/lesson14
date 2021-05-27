<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:choose>
            <c:when test="${list !=null }">
                <h2>タイムカード履歴</h2>

                <table>
                    <tbody>
                        <tr>
                            <th>日付</th>
                        <th>出勤日時</th>
                        <th>退勤日時</th>
                        </tr>
                        <c:forEach items="${list}" var="timecards">
                            <tr>
                            <td><fmt:formatDate value="${timecards.strtime_date}"
                                        pattern="yyyy-MM-dd" /></td>
                                <td><fmt:formatDate value="${timecards.started_at}"
                                        pattern="yyyy-MM-dd HH:mm:ss" /></td>
                                <td><fmt:formatDate value="${timecards.finished_at}"
                                        pattern="yyyy-MM-dd HH:mm:ss" /></td>

                            </tr>
                        </c:forEach>
                    </tbody>
                </table>

            </c:when>
            <c:otherwise>
                <h2>お探しのデータは見つかりませんでした。</h2>
            </c:otherwise>
        </c:choose>

        <p>
            <a href="<c:url value="/timecards/index"/>">タイムカードトップに戻る</a>
        </p>
    </c:param>
</c:import>