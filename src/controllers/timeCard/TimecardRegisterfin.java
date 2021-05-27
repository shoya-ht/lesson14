package controllers.timeCard;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Employee;
import models.Report;
import models.Timecard;
import utils.DBUtil;

@WebServlet("/timecard/timefin")
public class TimecardRegisterfin extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public TimecardRegisterfin() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        EntityManager em = DBUtil.createEntityManager();
        Employee e = (Employee) request.getSession().getAttribute("login_employee");

//日付取得
        Date sNow = new Date(System.currentTimeMillis());
        java.util.Date uDate = sNow;
        //時間切り捨て
        Calendar dateTime = Calendar.getInstance();
        dateTime.setTime(uDate);
        Calendar date = Calendar.getInstance();
        date.clear();
        date.set(dateTime.get(Calendar.YEAR), dateTime.get(Calendar.MONTH), dateTime.get(Calendar.DAY_OF_MONTH));
        java.util.Date d2 = date.getTime();
        Date fDate = new Date(d2.getTime());

        try{
      //本日の日報の有無を検索
        Report report = em.createNamedQuery("getMyTodayReport", Report.class)
                .setParameter("employee", e)
                .setParameter("workDay",fDate)
                .getSingleResult();
        request.setAttribute("report", report);


        //タイムカード検索
        Timecard timecard = em.createNamedQuery("getStartTimecardDay", Timecard.class)
                .setParameter("emp", e)
                .setParameter("workingDay", fDate)
                .getSingleResult();

        //退勤開始時刻を記録
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        timecard.setFinished_at(currentTime);

        //退勤日を登録
        timecard.setFintime_date(fDate);
        request.getSession().removeAttribute("finished_at");



        // データベースを登録
        em.getTransaction().begin();
        em.persist(timecard);
        em.getTransaction().commit();
        em.close();


            request.getSession().setAttribute("flush", "退勤登録が完了しました");
        }catch(NoResultException err){

    request.getSession().setAttribute("flush", "！！！＜注意＞退勤登録不可　理由：日報未登録のため！！！");
}

        response.sendRedirect(request.getContextPath() + "/index.html");
    }

}
