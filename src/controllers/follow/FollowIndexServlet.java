package controllers.follow;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Employee;
import models.Follow;
import utils.DBUtil;

@WebServlet("/follow/index")
public class FollowIndexServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public FollowIndexServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        EntityManager em = DBUtil.createEntityManager();

        //従業員リスト取得
        Employee login_employee = (Employee) request.getSession().getAttribute("login_employee");
        List<Employee> employees = em.createNamedQuery("getAllEmployees", Employee.class)
                .getResultList();
        request.setAttribute("employees", employees);

        try {
            //フォローリスト取得
            List<Follow> follows = em.createNamedQuery("getMyAllFollows", Follow.class)
                    .setParameter("emp", login_employee)
                    .getResultList();
            request.getSession().setAttribute("follows", follows);
        } catch (NoResultException e) {
            request.getSession().setAttribute("follows", null);
            request.getSession().setAttribute("flush", "従業員がフォローされていません");
        }
        em.close();

        if (request.getSession().getAttribute("flush") != null) {
            request.setAttribute("flush", request.getSession().getAttribute("flush"));
            request.getSession().removeAttribute("flush");
        }
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/follows/index.jsp");
        rd.forward(request, response);
    }
}