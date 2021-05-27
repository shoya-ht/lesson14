package controllers.follow;

import java.io.IOException;
import java.sql.Timestamp;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Employee;
import models.Follow;
import utils.DBUtil;

@WebServlet("/follow/register")
public class FollowRegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public FollowRegisterServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

            EntityManager em = DBUtil.createEntityManager();

            Follow f = new Follow();

            f.setEmployee((Employee) request.getSession().getAttribute("login_employee"));

            Employee e=em.find(Employee.class,Integer.parseInt(request.getParameter("id")));
            f.setRegemp_id(e);


            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            f.setRegistered_at(currentTime);

                em.getTransaction().begin();
                em.persist(f);
                em.getTransaction().commit();
                em.close();
                request.getSession().setAttribute("flush", "登録が完了しました。");

                response.sendRedirect(request.getContextPath() + "/follow/index");

            }


}
