

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

/**
 * Servlet implementation class InsertData
 */
@WebServlet("/InsertData")
public class InsertData extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/local", "root", "root");
			PreparedStatement ps = con.prepareStatement("insert into services values (?, ?, ?)");
			int id = Integer.parseInt(request.getParameter("ID"));
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			ps.setInt(1, id);
			ps.setString(2, name);
			ps.setString(3, email);
			ps.executeUpdate();
			out.println("Inserted record successfully!");
			ps.close();
			con.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		out.close();
	}

}
