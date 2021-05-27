package controllers.timeCard;

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
import models.Timecard;
import utils.DBUtil;


@WebServlet("/timecard/view")
public class TimecardView extends HttpServlet {
    private static final long serialVersionUID = 1L;


    public TimecardView() {
        super();

    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EntityManager em=DBUtil.createEntityManager();
        Employee emp = (Employee) request.getSession().getAttribute("login_employee");

        List<Timecard> alltimecards = em.createNamedQuery("getMyAllTimecards", Timecard.class)
                .setParameter("emp", emp)
                .getResultList();

        request.setAttribute("list", alltimecards);
        em.close();

        RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/views/timecards/show.jsp");
        rd.forward(request, response);




    }

}
