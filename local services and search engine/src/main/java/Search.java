
import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Search")
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public Search() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		try {
			int roll = Integer.parseInt(request.getParameter("id"));
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/local", "root", "root");
			PreparedStatement ps = con.prepareStatement("select * from services where id = ?");
			ps.setInt(1, roll);
			out.print("<table width=50% border=1>");
			out.print("<caption>Result:</caption>");
			ResultSet rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int total = rsmd.getColumnCount();
			out.print("<tr>");
			for(int i = 1; i <= total; i++) {
				out.print("<th>" + rsmd.getColumnName(i) + "</th>");
			}
			while(rs.next()) {
				out.print("<tr>");
				for(int i = 1; i <= total; i++) {
					if(i != 1) {
						out.print("<td>" + rs.getString(i) + "</td>");
					} else {
						out.print("<td>" + rs.getInt(i) + "</td>");
						
					}
					
				}
				
			}
			out.print("</table>");
			ps.close();
			con.close();
		} catch(Exception e) {
			System.out.println(e);
		}
	}

	}


