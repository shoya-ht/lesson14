package controllers.follow;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Employee;
import models.Report;
import utils.DBUtil;


@WebServlet("/follow/showindex")
public class FollowShowServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public FollowShowServlet() {
        super();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em=DBUtil.createEntityManager();
        //int id=Integer.parseInt(request.getParameter("id"));

       // request.setAttribute(id, request.getParameter("id"));

        //従業員クラスの従業員情報を取得
        Employee e=em.find(Employee.class,Integer.parseInt(request.getParameter("id")));
        request.setAttribute("e", e);


        //日報リスト　SQLを実行し、結果を取得
        List<Report> reports = em.createNamedQuery("getMyAllReports", Report.class)
                .setParameter("employee", e)
                .getResultList();

        request.setAttribute("reports", reports);
        em.close();

        if (request.getSession().getAttribute("flush") != null) {
            request.setAttribute("flush", request.getSession().getAttribute("flush"));
            request.getSession().removeAttribute("flush");
        }

        RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/views/follows/showindex.jsp");
        rd.forward(request, response);
                }

}
