package RegisterServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.net.jsse.PEMFile;

@WebServlet("/register")
public class REgisterServlet extends HttpServlet {
	private final static String query = "insert into users(name,email,mobile,dob,gender,city) values(?,?,?,?,?,?)";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter pw = resp.getWriter();
		resp.setContentType("text/html");

		pw.println("<link rel = 'stylesheet' href='css/bootstrap.css'></link>");

		// get details
		String name = req.getParameter("UserName");
		String email = req.getParameter("UserEmail");
		String mobile = req.getParameter("UserMobile");
		String dob = req.getParameter("UserDob");
		String gender = req.getParameter("UserGender");
		String city = req.getParameter("UserCity");

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
			int count = ps.executeUpdate();
			pw.println("<div class='card>' style='margin:auto;width:300px;margin-top:100px'>");
			if (count > 0) {
				pw.println("<h2 class = 'bg-danger text-light text-center'>User Registered Successfully</h2>");
			} else {
				pw.println("<h2 class = 'bg-danger text-light text-center'>User Not Registered</h2>");
			}
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
