package RegisterServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/editurl")

public class EditScreenServlet extends HttpServlet {
	private final static String query = "select name,email,mobile,dob,gender,city from users where id=?";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter pw = resp.getWriter();
		resp.setContentType("text/html");

		pw.println("<link rel = 'stylesheet' href='css/bootstrap.css'></link>");

		int id = Integer.parseInt(req.getParameter("id"));

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/usermgmt", "root", "Shubham@123");
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			rs.next();
			pw.println("<div style='margin:auto;width:500px;margin-top:100px;'>");
			pw.println("<form action='edit?id=" + id + "' method='post'>");
			pw.println("<table class='table table-hover table-striped'>");
			pw.println("<tr>");
			pw.println("<td>Name</td>");
			pw.println("<td><input type='text' name='name' value='" + rs.getString(1) + "'></td>");
			pw.println("</tr>");
			pw.println("<tr>");
			pw.println("<td>Email</td>");
			pw.println("<td><input type='email' name='email' value='" + rs.getString(2) + "'></td>");
			pw.println("</tr>");
			pw.println("<tr>");
			pw.println("<td>Mobile</td>");
			pw.println("<td><input type='text' name='mobile' value='" + rs.getString(3) + "'></td>");
			pw.println("</tr>");
			pw.println("<tr>");
			pw.println("<td>DOB</td>");
			pw.println("<td><input type='date' name='dob' value='" + rs.getString(4) + "'></td>");
			pw.println("</tr>");
			pw.println("<tr>");
			pw.println("<td>Gender</td>");
			pw.println("<td><input type='text' name='gender' value='" + rs.getString(5) + "'></td>");
			pw.println("</tr>");
			pw.println("<tr>");
			pw.println("<td>City</td>");
			pw.println("<td><input type='text' name='city' value='" + rs.getString(6) + "'></td>");
			pw.println("</tr>");

			pw.println("<tr>");
			pw.println("<td><button type='submit' class='btn btn-outline-success'>Edit</button></td>");
			pw.println("<td><button type='reset' class='btn btn-outline-danger'>Cancel</button></td>");
			pw.println("</tr>");
			pw.println("</table>");
			pw.println("</form>");

		} catch (Exception e) {
			pw.println("<h2 class ='bg-danger text-light text-center'>" + e.getMessage() + "</h2>");
			e.printStackTrace();
		}
		pw.println("<a href='home.html'><button class = 'btn btn-outline-success'>Home</button></a>");
		pw.println("</div>");
		pw.close();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
