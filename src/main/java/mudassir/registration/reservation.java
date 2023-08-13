package mudassir.registration;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

//import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class reservation
 */
@WebServlet("/reservation")
public class reservation extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		String tn = request.getParameter("train_no");
		String from = request.getParameter("from");
		String to = request.getParameter("to");
		String date = request.getParameter("date");
		PrintWriter out = response.getWriter();
//		RequestDispatcher dispatcher = null;
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/signup","root","Mudassir@80");
		
			PreparedStatement pst = con.prepareStatement("INSERT INTO reservation(train_no,train_from,destination,date_of_journey) values(?,?,?,?)");
			pst.setString(1, tn);
			pst.setString(2, from);
			pst.setString(3, to);
			pst.setString(4, date);
			
			int rowCount = pst.executeUpdate();
//			dispatcher = request.getRequestDispatcher("reservation.html");
			if(rowCount > 0) {
				out.print("<h1>You have Successfully reserved !!</h1><br>");
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
