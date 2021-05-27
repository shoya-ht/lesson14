package controllers.timeCard;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.DBUtil;

@WebServlet("/timecard/record")
public class TimecardRecordServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;


    public TimecardRecordServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

//直前の出退勤のみ表示
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em=DBUtil.createEntityManager();

      //あとで    Timecard r=em.find(timecard.class, Integer.parseInt(request.getParameter("id")));
        em.close();

      //あとで      request.setAttribute("timecard", t);

        RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/views/timecard/show.jsp");
        rd.forward(request, response);

    }

    }
