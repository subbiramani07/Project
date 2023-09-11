

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class UserLogin
 */
@WebServlet("/UserLogin")
public class UserLogin extends HttpServlet {
	
public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	String  email= request.getParameter("email");
	String  password= request.getParameter("password");
	
	
	PrintWriter out = response.getWriter();

	DBOperation dboperation = new DBOperation();
	try {
		if(dboperation.userLogin(email, password))  {
			HttpSession session =request.getSession();
			session.setAttribute("email", email);
		response.sendRedirect("home.jsp");
		}
		else {
			response.sendRedirect("error.jsp");
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}

}
