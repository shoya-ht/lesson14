<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <c:if test="${flush !=null}">
            <div id="flush_success">
                <c:out value="${flush }">
                </c:out>
            </div>

        </c:if>
        <c:if test="${report !=null}">
            <h2>本日の予定</h2>
            <h3 id=planFT>
                <c:out value="${report[0].plan} " />
            </h3>
        </c:if>
        <nav id="topSelect">
            <ul>
                <c:if test="${sessionScope.login_employee.admin_flag==3}">
                    <li><a href="<c:url value='/employees/index'/>">従業員管理</a></li>
                </c:if>
                <li><a href="<c:url value='/reports/index'/>">日報管理</a></li>
                <li><a href="<c:url value='/timecards/index'/>">タイムカード</a></li>
                <li><a href="<c:url value='/follow/index'/>">フォロー</a></li>
            </ul>
        </nav>
        <h3>日報提出状況</h3>
         <table id="report_exist">
            <tbody>
                <tr>

                    <th class="report_date">日付</th>
                    <th class="report_yesno">提出有無</th>
                </tr>
                <c:forEach var="report" items="${yesorno}" varStatus="status">
                    <tr class="row${status.count %2 }">
                        <td class="report_date"><fmt:formatDate
                                value='${report.report_date}' pattern='yyyy-MM-dd' /></td>
                        <td class="report_flg">
                        <c:choose>
                        <c:when test="${report.report_flg==1}">
                        <h2>○</h2>
                        </c:when>
                        <c:otherwise>
                        <h2>×</h2>
                        </c:otherwise>
                        </c:choose>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <h3>注意点</h3>
        <ul>
            <li>管理者のみ従業員管理画面を表示可能</li>
        </ul>
    </c:param>
</c:import>