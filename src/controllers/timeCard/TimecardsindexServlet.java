package controllers.timeCard;

//タイムカードの出退勤選択画面へ
import java.io.IOException;
import java.sql.Date;
import java.util.Calendar;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Employee;
import models.Timecard;
import utils.DBUtil;

@WebServlet("/timecards/index")
public class TimecardsindexServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public TimecardsindexServlet() {
        super();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        EntityManager em = DBUtil.createEntityManager();

        Employee emp = (Employee) request.getSession().getAttribute("login_employee");
        Date sNow = new Date(System.currentTimeMillis());
        java.util.Date uDate = sNow;

        //時間切り捨て
        Calendar dateTime = Calendar.getInstance();
        dateTime.setTime(uDate);
        Calendar date = Calendar.getInstance();
        date.clear();
        date.set(dateTime.get(Calendar.YEAR), dateTime.get(Calendar.MONTH), dateTime.get(Calendar.DAY_OF_MONTH));
        java.util.Date d2 = date.getTime();

        Date sDate = new Date(d2.getTime());
        try {
            //タイムカードの検索
            Timecard todayStartCard = em.createNamedQuery("getStartTimecardDay", Timecard.class)
                    .setParameter("emp", emp)
                    .setParameter("workingDay", sDate)
                    .getSingleResult();
            request.setAttribute("timecardstart", todayStartCard);

            Timecard todayFinCard = em.createNamedQuery("getFinTimecardDay", Timecard.class)
                    .setParameter("emp", emp)
                    .setParameter("workingDay", sDate)
                    .getSingleResult();
            request.setAttribute("timecardfin", todayFinCard);

            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/timecards/index.jsp");
            rd.forward(request, response);

        } catch (NoResultException e) {
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/timecards/index.jsp");
            rd.forward(request, response);
            //return;
        }



    }

}

    //当日出勤情報なければ出勤ボタン表示

    //当日出勤情報があれば退勤ボタン表示



//Timecard t=em.find(Timecard.class, Integer.parseInt(request.getParameter("login_employee")));

/*public User getUserByUsernameOrNull(String username) {
try{
    Query q = em.createNamedQuery(User.getUserByUsername);
    q.setParameter("username", username);
    return (User) q.getSingleResult();
} catch(NoResultException e) {
    return null;     //ゼロ件だった場合
}
}*/
/*
        int page;
        try{
            page=Integer.parseInt(request.getParameter("page"));
        }catch(Exception e){
            page=1;

        }
        List<Timecard> timecards =em.createNamedQuery("getAllTimecards",Timecard.class)
                .setFirstResult(15*(page-1))
                .setMaxResults(15)
                .getResultList();

        long timecards_count=(long)em.createNamedQuery("getTimecardsCount",Long.class)
                .getSingleResult();
                */
