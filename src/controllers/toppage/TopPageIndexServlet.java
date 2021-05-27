package controllers.toppage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import models.ReportYesNo;
import models.Timecard;
import utils.DBUtil;

@WebServlet("/index.html")
public class TopPageIndexServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public TopPageIndexServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        EntityManager em = DBUtil.createEntityManager();
        //従業員情報検索
        Employee emp = (Employee) request.getSession().getAttribute("login_employee");
        //今日の日時取得
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

        Timecard todayFinCard = null;
        //本日退勤情報があるか検索
        try {
            todayFinCard = em.createNamedQuery("getFinTimecardDay", Timecard.class)
                    .setParameter("emp", emp)
                    .setParameter("workingDay", sDate)
                    .getSingleResult();
            request.setAttribute("todayFinCard", todayFinCard);
        } catch (NoResultException e) {
        }

//退勤情報がなければ過去の日報情報取得
            if (todayFinCard== null) {
                List<Report> report = em.createNamedQuery("getMyLastReport", Report.class)
                        .setParameter("employee", emp)
                        .setParameter("workingDay", sDate)
                        .setFirstResult(0)
                        .setMaxResults(1)
                        .getResultList();
                request.getSession().setAttribute("report", report);
            }else{
                request.getSession().setAttribute("report",null);
            }



        int page;
        try {
            page = Integer.parseInt(request.getParameter("page"));
        } catch (Exception e) {
            page = 1;
        }




        try{
          //日報入手
        Employee login_employee = (Employee) request.getSession().getAttribute("login_employee");
        List<Report> reports = em.createNamedQuery("getMyAllReports", Report.class)
                .setParameter("employee", login_employee)
                .setMaxResults(7)
                .getResultList();
        em.close();
        List<ReportYesNo> list=new ArrayList<ReportYesNo>();
        LocalDate d = LocalDate.now();  //今の日付を取得する
        for(int i = 0; i < 7; i++) {
            LocalDate newDay = d.minusDays(i);   //起点の日付からi日後のインスタンスを生成する
            ReportYesNo ry = new ReportYesNo();
            ry.setReport_date(toDate(newDay));
            ry.setReport_flg(0);
                for(int j=0;j<reports.size();j++){
                    //型変換がおかしい？
                    LocalDate localDate = reports.get(j).getReport_date().toLocalDate();
                    System.out.println("local"+localDate);
                    System.out.println(newDay);
                if(localDate.isEqual(newDay)){

                ry.setReport_flg(1);
                System.out.println("list");
                break;

                }

                }
            list.add(ry);
        }

            request.setAttribute("yesorno", list);
        /*
//まとめるリスト作成


       // List<Date> dates=new ArrayList<Date>();
        for(int i=0;i<7;i++){
         // Date型の日時をCalendar型に変換
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(sDate);

            // 日時を減算する
            calendar.add(Calendar.DATE, -i);

            // Calendar型の日時をDate型に戻す
            java.util.Date rday = calendar.getTime();

            dates.add(java.sql.Date(rday.getTime()));
        }


*/
         request.getSession().setAttribute("report", reports);
        }catch(NoResultException e){
            request.getSession().setAttribute("report", null);

        }

        //日報セッター

        request.setAttribute("page", page);

        if (request.getSession().getAttribute("flush") != null) {
            request.setAttribute("flush", request.getSession().getAttribute("flush"));
            request.getSession().removeAttribute("flush");

        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/topPage/index.jsp");
        rd.forward(request, response);
    }
    public Date toDate(LocalDate dateToConvert) {
        return java.util.Date.from(dateToConvert.atStartOfDay()
          .atZone(ZoneId.systemDefault())
          .toInstant());
    }

}
