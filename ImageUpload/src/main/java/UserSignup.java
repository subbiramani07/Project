

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UserSignup
 */
@WebServlet("/UserSignup")
public class UserSignup extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String  firstname= request.getParameter("firstname");
		String  lastname= request.getParameter("lastname");
		String  email= request.getParameter("email");
		String  password= request.getParameter("password");
		
		
		PrintWriter out = response.getWriter();
		
		DBOperation dboperation = new DBOperation();
		try {
			if(dboperation.userSignup(firstname, lastname, email, password))  {
			response.sendRedirect("home.jsp");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
