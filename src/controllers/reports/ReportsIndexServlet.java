package controllers.reports;
//自分の日報一覧表示画面へ
import java.io.IOException;
import java.sql.Date;
import java.util.Calendar;
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
import models.Report;
import utils.DBUtil;

@WebServlet("/reports/index")
public class ReportsIndexServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;


    public ReportsIndexServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EntityManager em=DBUtil.createEntityManager();

        int page;
        try{
            page=Integer.parseInt(request.getParameter("page"));
        }catch(Exception e){
            page=1;

        }
        Employee login_employee = (Employee) request.getSession().getAttribute("login_employee");
        //日報リスト　SQLを実行し、結果を取得
        List<Report> reports = em.createNamedQuery("getMyAllReports", Report.class)
                .setParameter("employee", login_employee)
                .setFirstResult(15 * (page - 1))
                .setMaxResults(15)
                .getResultList();
        //日報合計カウント
        long reports_count = (long) em.createNamedQuery("getMyReportsCount", Long.class)
                .setParameter("employee", login_employee)
                .getSingleResult();

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
        Date sDate = new Date(d2.getTime());

        try{
        //本日の日付で済みのものがあるか確認
         Report report = em.createNamedQuery("getMyTodayReport", Report.class)
                .setParameter("employee", login_employee)
                .setParameter("workDay", sDate)
                .getSingleResult();

         request.getSession().setAttribute("report", report);
        }catch(NoResultException e){
            request.getSession().setAttribute("report", null);

        }

        request.setAttribute("reports", reports);
        request.setAttribute("reports_count", reports_count);


        em.close();

        request.setAttribute("page", page);
        if(request.getSession().getAttribute("flush") !=null){
            request.setAttribute("flush", request.getSession().getAttribute("flush"));;
            request.getSession().removeAttribute("flush");
        }
        RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/views/reports/index.jsp");
        rd.forward(request, response);
                }

}
