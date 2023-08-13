package mudassir.registration;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class find
 */
@WebServlet("/find")
public class find extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String from = request.getParameter("from");
		String to = request.getParameter("to");
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/signup","root","Mudassir@80");
			PreparedStatement pst = con.prepareStatement("SELECT * FROM delhi_chennai WHERE Train_from = ? AND Destination = ?");
			
			pst.setString(1,from);
			pst.setString(2,to);
			
			out.print("<table width=75% border=1>");
			out.print("<caption>Train Info :</caption>");
			
			ResultSet rs = pst.executeQuery();
			
			ResultSetMetaData rsmd = rs.getMetaData();
			int totalColumn = rsmd.getColumnCount();
			out.print("<tr>");
			for(int i=1; i<=totalColumn; i++) {
				out.print("<th>"+rsmd.getColumnName(i)+"</th>");
			}
			out.print("</tr>");
			while(rs.next()) {
				out.print("<tr><td>"+rs.getInt(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td><td>"+rs.getString(4)+"</td><td>"+rs.getString(5)+"</td><td>"+rs.getString(6)+"</td><td>"+rs.getTime(7)+"</td><td>"+rs.getInt(8)+
						"</td> <td><a href=\"reservation.html\"><input type=\"submit\" value=\"Book Now\"></a></td> </tr>");
			}
			out.print("</table>");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
