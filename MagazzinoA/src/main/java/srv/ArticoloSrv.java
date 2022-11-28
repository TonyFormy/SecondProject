package srv;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ArticoloDao;
import it.antonio.Articolo;

public class ArticoloSrv extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		
		ArticoloDao aDao = new ArticoloDao();
		
		String descrizione= request.getParameter("descrizione");
		String quant= request.getParameter("quantita");
		int quantita=Integer.parseInt(quant);
		
		Articolo a = new Articolo();
		a.setDescrizione(descrizione);
		a.setQuantita(quantita);
		
		aDao.insert(a);
		try {
			response.getWriter().append("Inserimento andato a buon fine");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
