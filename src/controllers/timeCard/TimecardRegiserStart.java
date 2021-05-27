package controllers.timeCard;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Employee;
import models.Timecard;
import utils.DBUtil;

@WebServlet("/timecard/timestart")
public class TimecardRegiserStart extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public TimecardRegiserStart() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        EntityManager em = DBUtil.createEntityManager();

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

        //タイムカード 情報なければ新規作成→ない
        Timecard t = new Timecard();

        t.setEmployee((Employee) request.getSession().getAttribute("login_employee"));
        //出勤開始時刻を記録
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        t.setStarted_at(currentTime);
        //出勤日を登録
        t.setStrtime_date(sDate);
        //データベースを登録
        em.getTransaction().begin();
        em.persist(t);
        em.getTransaction().commit();
        em.close();

        // セッションスコープ上の不要になったデータを削除
        request.getSession().removeAttribute("started_at");

        request.getSession().setAttribute("flush", "出勤登録が完了しました");

        response.sendRedirect(request.getContextPath() + "/index.html");

    }
}
