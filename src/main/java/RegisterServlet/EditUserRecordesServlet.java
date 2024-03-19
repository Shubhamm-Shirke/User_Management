package RegisterServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/edit")

public class EditUserRecordesServlet extends HttpServlet {
	private final static String query = "update users set  name=?,email=?,mobile=?,dob=?,gender=?,city=? where id=?";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter pw = resp.getWriter();
		resp.setContentType("text/html");

		pw.println("<link rel = 'stylesheet' href='css/bootstrap.css'></link>");

		// get id
		int id = Integer.parseInt(req.getParameter("id"));

		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String mobile = req.getParameter("mobile");
		String dob = req.getParameter("dob");
		String gender = req.getParameter("gender");
		String city = req.getParameter("city");

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/usermgmt", "root", "Shubham@123");
			PreparedStatement ps = con.prepareStatement(query);

			ps.setString(1, name);
			ps.setString(2, email);
			ps.setString(3, mobile);
			ps.setString(4, dob);
			ps.setString(5, gender);
			ps.setString(6, city);
			ps.setInt(7, id);

			int count = ps.executeUpdate();
			pw.println("<div class='card' style='margin:auto;width:300px;margin-top:100px'>");
			if (count > 0) {
				pw.println("<h2 class = 'bg-danger text-light text-center'>User Record Edited Successfully</h2>");
			} else {
				pw.println("<h2 class = 'bg-danger text-light text-center'>Record Not Edited</h2>");
			}
		} catch (Exception e) {
			pw.println("<h2 class ='bg-danger text-light text-center'>" + e.getMessage() + "</h2>");
			e.printStackTrace();
		}
		pw.println("<a href='home.html'><button class = 'btn btn-outline-success'>Home</button></a>");
		pw.println("&nbsp; &nbsp; &nbsp; &nbsp");

		pw.println("<a href='showData'><button class = 'btn btn-outline-success'>Show User</button></a>");

		pw.println("</div>");
		pw.close();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
