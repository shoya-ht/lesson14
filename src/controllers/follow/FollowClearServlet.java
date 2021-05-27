package controllers.follow;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Employee;
import models.Follow;
import utils.DBUtil;

/**
 * Servlet implementation class FollowClearServlet
 */
@WebServlet("/follow/clear")
public class FollowClearServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public FollowClearServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


            EntityManager em=DBUtil.createEntityManager();

//従業員のIDではなくフォローリストのIDに変更する
            Employee e=em.find(Employee.class,Integer.parseInt(request.getParameter("id")));

            Follow follow =em.createNamedQuery("getFollows", Follow.class)
                    .setParameter("emp", e)
                    .getSingleResult();

            em.getTransaction().begin();
            em.remove(follow);
            em.getTransaction().commit();
            em.close();
            request.getSession().setAttribute("flush", "登録解除が完了しました。");

         // セッションスコープ上の不要になったデータを削除
            request.getSession().removeAttribute("id");

            response.sendRedirect(request.getContextPath()+"/follow/index");



        }


}
