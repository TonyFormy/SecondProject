package srv;

import java.io.IOException;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ArticoloDao;
import it.antonio.Articolo;

public class ArticoloSrv extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) {

		ArticoloDao aDao = new ArticoloDao();

		String descrizione = request.getParameter("descrizione");
		String quant = request.getParameter("quantita");
		int quantita = Integer.parseInt(quant);

		Articolo a = new Articolo();
		a.setDescrizione(descrizione);
		a.setQuantita(quantita);

		aDao.insert(a);
		select(a,response);
	}
public void select(Articolo a,HttpServletResponse response) {
		
		String url = "jdbc:mysql://localhost:3306/esercizioservlet";
		String user = "tonyformy";
		String password = "tony";
		String driver = "com.mysql.cj.jdbc.Driver";

		Connection conn = null;

		try {
			try {
				Class.forName(driver);
				try {
					conn = DriverManager.getConnection(url, user, password);
					System.out.println("Connesso correttamente al database");
					String query_da_eseguire = "select * from articolo";
					  try {
						  PreparedStatement stmt = conn.prepareStatement(query_da_eseguire);
						  	ResultSet ris =stmt.executeQuery();
						  	Writer w;
							try {
								w = response.getWriter();
								w.write("<html><body><h2>Tabella Articoli : </h2>"
										  +"<hr></br><table cellspacing='0' cellpadding='5' border='1'>"
										  +"<tr>"
										  +"<td><b>Codice</b></td>"
										  +"<td><b>Descrizione</b></td>"
										  +"<td><b>Quantità</b></td>"
										  +"</tr>");
								while(ris.next()) {
									 w.write("<tr>"
											  +"<td>"+ris.getString(1) + "</td>"
											  +"<td>"+ris.getString(2) + "</td>"
											  +"<td>"+ris.getString(3) + "</td>"
											+"</tr>");
								}
										   w.write("</table></br><hr></body></html>");
										   
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						  }
						  catch (SQLException e) {

						   e.printStackTrace();
						  }
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		}

}
}
