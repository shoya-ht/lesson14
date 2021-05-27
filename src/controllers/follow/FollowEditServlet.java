package controllers.follow;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.EditFollow;
import models.Employee;
import models.Follow;
import utils.DBUtil;

@WebServlet("/follow/edit")
public class FollowEditServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public FollowEditServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        EntityManager em = DBUtil.createEntityManager();


        Employee login_employee = (Employee) request.getSession().getAttribute("login_employee");
        request.setAttribute("login_employee",login_employee);

      //全従業員リスト取得
        List<Employee> employees = em.createNamedQuery("getAllEmployees", Employee.class)
                .getResultList();
        request.setAttribute("allemployees", employees);

        //表示用モデル作成
        List<Follow> follows = em.createNamedQuery("getMyAllFollows", Follow.class)
                .setParameter("emp", login_employee)
                .getResultList();
        List<EditFollow> list=new ArrayList<EditFollow>();


        for(int i=0;i<employees.size();i++){
            //EditFollowは従業員ごとに作成
            EditFollow ef = new EditFollow();
            ef.setFollow_flg(0);
            Employee value=employees.get(i);
            ef.setEmployee(value);
            for(int j=0;j<follows.size();j++){
                Follow value2=follows.get(j);

                if(value.getId()==value2.getRegemp_id().getId()){
                    ef.setFollow_flg(1);
                    break;
                }
            }
            list.add(ef);
        }
        request.setAttribute("yesorno", list);

        em.close();
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/follows/edit.jsp");
        rd.forward(request, response);
    }
}



        /*
        //フォローしている従業員のフラグ変更
        if(_token !=null&&_token.equals(request.getSession().getId())){
            EntityManager em=DBUtil.createEntityManager();
            Employee e=em.find(Employee.class,(Integer)(request.getSession().getAttribute("employee_id")));
            e.setDelete_flag(1);
            e.setUpdated_at(new Timestamp(System.currentTimeMillis()));
            em.getTransaction().begin();
            em.getTransaction().commit();
            em.close();
            request.getSession().setAttribute("flush", "削除が完了しました。");;
            response.sendRedirect(request.getContextPath()+"/employees/index");
        }

        try {
            //フォローリスト取得
            List<Follow> follows = em.createNamedQuery("getMyAllFollows", Follow.class)
                    .setParameter("emp", login_employee)
                    .getResultList();
            request.getSession().setAttribute("follows", follows);
        } catch (NoResultException e) {
            request.getSession().setAttribute("follows", null);
            request.getSession().setAttribute("flush", "従業員がフォローされていません");
        }
        */




