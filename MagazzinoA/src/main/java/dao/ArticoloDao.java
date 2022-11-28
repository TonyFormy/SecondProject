package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import it.antonio.Articolo;

public class ArticoloDao {

	public void insert(Articolo a) {
		
		String url = "jdbc:mysql://localhost:3306/esercizioservlet";
		String user = "tonyformy";
		String password = "tony";
		String driver = "com.mysql.cj.jdbc.Driver";

		Connection conn = null;

		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
			System.out.println("Connesso correttamente al database");
			String query_da_eseguire = "INSERT INTO articolo(descrizione,quantita)VALUES(?,?)";
			PreparedStatement stmt = conn.prepareStatement(query_da_eseguire);
			System.out.println("Sto preparando la query");
			stmt.setString(1, a.getDescrizione());
			stmt.setInt(2, a.getQuantita());
			stmt.execute();
			System.out.println("Ho terminato l'inserimento a database.");
			stmt.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
		
	}
	
	

