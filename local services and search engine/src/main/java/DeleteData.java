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
 * Servlet implementation class DeleteData
 */
@WebServlet("/DeleteData")
public class DeleteData extends HttpServlet {
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
			PreparedStatement ps = con.prepareStatement("delete from services where id = ?");
			int id = Integer.parseInt(request.getParameter("delete-id"));
			ps.setInt(1, id);
			ps.executeUpdate();
			out.print("<html><body><h1>Deleted record successfully!</h1></body></html>");
			ps.close();
			con.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		out.close();
	}

}
