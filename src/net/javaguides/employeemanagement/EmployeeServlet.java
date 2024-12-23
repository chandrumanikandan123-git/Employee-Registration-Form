package net.javaguides.employeemanagement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Servlet implementation class EmployeeServlet
 */
@WebServlet("/EmployeeServlet")
public class EmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EmployeeDao employeeDao;
	public void init() {
        employeeDao = new EmployeeDao();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String address = request.getParameter("address");
        String contact = request.getParameter("contact");

        Employee employee= new Employee();
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setUsername(username);
        employee.setPassword(password);
        employee.setAddress(address);
        employee.setContact(contact);

        try {
            employeeDao.registerEmployee(employee);
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("employeedetails.jsp");
        
    }
}
