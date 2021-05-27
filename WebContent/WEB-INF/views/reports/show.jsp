<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:choose>
            <c:when test="${report !=null }">
                <h2>日報 詳細ページ</h2>

                <table>
                    <tbody>
                        <tr>
                            <th>氏名</th>
                            <td><c:out value="${report.employee.name}" /></td>
                        </tr>
                        <tr>
                            <th>役職</th>
                            <td><c:if test="${report.employee.admin_flag==0}">
                                    <c:out value="一般" />
                                </c:if> <c:if test="${report.employee.admin_flag==1}">
                                    <c:out value="課長" />
                                </c:if> <c:if test="${report.employee.admin_flag==2}">
                                    <c:out value="部長" />
                                </c:if> <c:if test="${report.employee.admin_flag==3}">
                                    <c:out value="管理者" />
                                </c:if>
                        </tr>

                        <tr>
                            <th>日付</th>
                            <td><fmt:formatDate value="${report.report_date}"
                                    pattern="yyyy-MM-dd" /></td>
                        </tr>
                        <tr>
                            <th>取引先</th>
                            <td>
                            <pre><c:out value="${report.company}" /></pre>

                                </td>
                        </tr>
                        <tr>
                            <th>商談内容、状況</th>
                            <td>
                            <pre><c:out value="${report.content}" /></pre>

                              </td>
                        </tr>

                        <tr>
                            <th>次の出勤日の予定</th>
                            <td>
                            <pre><c:out value="${report.plan}" /></pre>

                              </td>
                        </tr>

                        <tr>
                            <th>登録日時</th>
                            <td><fmt:formatDate value="${report.created_at}"
                                    pattern="yyyy-MM-dd HH:mm:ss" /></td>
                        </tr>
                        <tr>
                            <th>更新日時</th>
                            <td><fmt:formatDate value="${report.updated_at}"
                                    pattern="yyyy-MM-dd HH:mm:ss" /></td>
                        </tr>
                    </tbody>
                </table>

                <c:if test="${sessionScope.login_employee.id==report.employee.id}">
                    <p>
                        <a href="<c:url value="/reports/edit?id=${report.id}"/>">この日報を編集する</a>
                    </p>
                </c:if>
            </c:when>
            <c:otherwise>
                <h2>お探しのデータは見つかりませんでした。</h2>
            </c:otherwise>
        </c:choose>
        <p>
            <a href="<c:url value="/reports/index"/>">自分の日報一覧へ</a>
        </p>
    </c:param>
</c:import>
