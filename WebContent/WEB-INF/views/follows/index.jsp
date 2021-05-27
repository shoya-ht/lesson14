<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:if test="${flush !=null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>

        <c:choose>
            <c:when test="${fn:length( follows )  == 0}">
                <h2>フォロー登録</h2>
                <table>
                    <tbody>
                        <tr>
                            <th>ID</th>
                            <th>氏名</th>
                            <th>役職</th>
                            <th>操作</th>
                        </tr>
                        <c:forEach var="follow" items="${employees}" varStatus="status">
                            <tr class="row${status.count %2 }">
                                <td><c:out value="${follow.id} " /></td>
                                <td><c:out value="${follow.name }" /></td>

                                <td><c:choose>
                                        <c:when test="${follow.admin_flag==1}">課長</c:when>
                                        <c:when test="${follow.admin_flag==2}">部長</c:when>
                                        <c:when test="${follow.admin_flag==3}">管理者</c:when>
                                        <c:otherwise>一般</c:otherwise>
                                    </c:choose></td>

                                <td><a href="#"
                                    onclick="forsubmit('formRegister_${follow.id}');">登録する</a>
                                    <form method="POST" id="formRegister_${follow.id}"
                                        action="<c:url value='/follow/register' />">
                                        <input type="hidden" name="id" value="${follow.id}" />
                                    </form></td>

                            </tr>
                        </c:forEach>

                    </tbody>
                </table>
            </c:when>
            <c:otherwise>
                <h2>フォロー中の従業員一覧</h2>
                <table id="follows_list">
                    <tbody>
                        <tr>
                            <th>従業員コード</th>
                            <th>フォロー日時</th>
                            <th>操作</th>
                        </tr>
                        <c:forEach var="follow" items="${follows}" varStatus="status">
                            <tr>
                                <td><c:out value="${follow.regemp_id.code} " /></td>

                                <td><fmt:formatDate value='${follow.registered_at}'
                                        pattern='yyyy-MM-dd HH:mm:ss' /></td>
                                        <td>
                                        <a href="<c:url value='/follow/showindex?id=${follow.id}'/>">詳細を表示</a></td>
                                        <!--
                                <td><a href="#"
                                    onclick="forsubmit('formShow_${follow.id}');">詳細を見る</a>
                                    <form method="POST" id="formShow_${follow.id}"
                                        action="<c:url value='/follow/show' />">
                                        <input type="hidden" name="id" value="${follow.id}" />
                                    </form></td>
                                     -->
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>

            </c:otherwise>
        </c:choose>
        <p>
            <a href="<c:url value='/follow/edit'/>">フォロー編集</a>
        </p>
        <script>
            function forsubmit(id) {
                document.getElementById(id).submit();
            }
        </script>
    </c:param>
</c:import>