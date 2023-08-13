package mudassir.registration;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String username = request.getParameter("username");
		String upwd = request.getParameter("password");
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/signup","root","Mudassir@80");
			Statement st = con.createStatement();
			String query = "SELECT * FROM signup WHERE username = '"+username+"' AND upwd = '"+upwd+"'";
			ResultSet rs =  st.executeQuery(query);
			
			if(rs.next()) {
				response.sendRedirect("search.html");
				//out.print("<h2>"+username+"</h2><br>");
				//out.print("<h2>Login Successfully!!</h2><br>");
			}else {
				out.print("<h2>"+username+": Pleas Enter the correct userId and password</h2><br>");
				out.print("<h2>Login failed!!</h2><br>");
			}
			rs.close();
			st.close();
			con.close();
		}
		catch(ClassNotFoundException e) {
			out.print("<h1>Login failed because of server error</h1><br>");
			e.printStackTrace();
		}catch(SQLException e){
			out.print("<h1>Login failed because of SQL error</h1><br>");
			e.printStackTrace();
		}
	}
}
