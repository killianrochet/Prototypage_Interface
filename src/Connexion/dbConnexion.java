package Connexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class dbConnexion {
	public static Connection Connect() {
		try {
			String url = "jdbc:mysql://localhost:3306/Sejours";
			
			String user ="root";
			String password ="123456789";
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, user, password);
			
			// display status message
		      if (conn == null) {
		         System.out.println("Connexion à la BDD non réussie");
		      } else
		         System.out.println("Connexion à la BDD réussie.\n");			
			return conn ;
		} catch (ClassNotFoundException | SQLException  e) {
			// TODO: handle exception
			Logger.getLogger(dbConnexion.class.getName()).log(Level.SEVERE,null,e);
		}
		return null;
	}
	
}