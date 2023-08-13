package mudassir.registration;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegistrationServlet
 */
@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		String uname = request.getParameter("name");
		String uemail = request.getParameter("email");
		String username = request.getParameter("username");
		String upass = request.getParameter("password");
		PrintWriter out = response.getWriter();
//		RequestDispatcher dispatcher = null;
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/signup","root","Mudassir@80");
		
			PreparedStatement pst = con.prepareStatement("INSERT INTO signup(uname,uemail,username,upwd) values(?,?,?,?)");
			pst.setString(1, uname);
			pst.setString(2, uemail);
			pst.setString(3, username);
			pst.setString(4, upass);
			
			int rowCount = pst.executeUpdate();
//			dispatcher = request.getRequestDispatcher("signup.html");
			if(rowCount > 0) {
				out.print("<h1>Your Account Created Successfully!!</h1><br>");
				request.setAttribute("status", "success");
			}
			else {
				request.setAttribute("status", "failed");
			}
//			dispatcher.forward(request, response);
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
