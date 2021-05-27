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

                    <th>氏名</th>
                    <th>日付</th>
                    <th>操作</th>
                </tr>
                <c:forEach var="report" items="${reports}" varStatus="status">
                    <tr class="row${status.count %2 }">

                        <td class="report_name">${report.employee.name}</td>
                        <td class="report_date"><fmt:formatDate
                                value='${report.report_date}' pattern='yyyy-MM-dd' /></td>
                        <td class="report_action"><a href="<c:url value='/reports/showother/detail?id=${report.id}'/>">詳細を見る</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <div id="pagination">
            <!-- 1行目不明 -->
            (全${reports_count}件 )<br />
            <c:forEach var="i" begin="1" end="${((reports_count-1)/15)+1 }"
                step="1">
                <c:choose>
                    <c:when test="${i==page }">
                        <c:out value="${i}" />&nbsp;
</c:when>
                    <c:otherwise>
                        <a href="<c:url value='/reports/showother?page="${i}'/>"><c:out
                                value="${i}" /></a>&nbsp;
</c:otherwise>
                </c:choose>
            </c:forEach>
        </div>
    </c:param>
</c:import>