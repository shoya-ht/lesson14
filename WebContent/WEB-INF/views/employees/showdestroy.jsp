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
        <h2>全従業員一覧</h2>
        <table id="employee_all">
            <tbody>
                <tr>
                    <th>社員番号</th>
                    <th>氏名</th>
                    <th>役職</th>
                    <th>削除</th>

                </tr>

                <c:forEach var="employee" items="${employees}" varStatus="status">
                    <tr>
                        <td><c:out value="${employee.code}" /></td>
                        <td><c:out value="${employee.name}" /></td>
                        <td><c:out value="${employee.admin_flag}" /></td>
                        <td><a href="#" onclick="confirmDestroy();">削除 </a>

                        <form method="POST" action="${pageContext.request.contextPath}/destroy">
                            <input type="hidden" name="_token" value="${_token}" />
                        </form>
                        <script>
                            function confirmDestroy() {
                                if (confirm("本当に削除してもよろしいですか？")) {
                                    document.forms[1].submit();
                                }
                            }


                        </script>
                        </td>
                    </tr>

                </c:forEach>
            </tbody>
        </table>
        <div id="pagination">
            (全${employees_count}件 )<br />
            <c:forEach var="i" begin="1" end="${((employees_count-1)/15)+1 }"
                step="1">
                <c:choose>
                    <c:when test="${i==page }">
                        <c:out value="${i}" />&nbsp;
</c:when>
                    <c:otherwise>
                        <a href="<c:url value='/employees/showdestroy?page="${i}'/>"><c:out
                                value="${i}" /></a>&nbsp;
</c:otherwise>

                </c:choose>
            </c:forEach>

        </div>
    </c:param>
</c:import>