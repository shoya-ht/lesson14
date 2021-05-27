package controllers.employees;

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
import utils.DBUtil;


@WebServlet("/emoloyees/show/destroy")
public class EmployeeShowForDestroy extends HttpServlet {
    private static final long serialVersionUID = 1L;


    public EmployeeShowForDestroy() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em=DBUtil.createEntityManager();
        List<Employee> allemployees = em.createNamedQuery("getAllEmployees", Employee.class)
                .getResultList();
        request.setAttribute("employees", allemployees);
        em.close();

        RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/views/employees/showdestroy.jsp");
        rd.forward(request,response);
    }
}
