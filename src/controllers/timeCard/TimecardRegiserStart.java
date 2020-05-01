package controllers.timeCard;

import java.io.IOException;
import java.sql.Timestamp;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Employee;
import utils.DBUtil;

@WebServlet("/timestart")
public class TimecardRegiserStart extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public TimecardRegiserStart() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();
        Timestamp t = em.find(Timestamp.class, Integer.parseInt(request.getParameter("id")));
        t.setEmployee((Employee) request.getSession().getAttribute("login_employee"));

        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        t.setStarted_at(currentTime);

        // データベースを更新
        em.getTransaction().begin();
        em.getTransaction().commit();
        em.close();

        // セッションスコープ上の不要になったデータを削除
        request.getSession().removeAttribute("started_at");

        request.getSession().setAttribute("flush", "登録が完了しました");

        response.sendRedirect(request.getContextPath() + "/timeCards/index");

    }
}