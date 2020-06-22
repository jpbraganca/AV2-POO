package poo.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConnectionFactory {
	private static final String URL = "jdbc:postgresql://localhost:5432/LocadoraFilme";
	private static final String USER = "postgres";
	private static final String PASSWORD = "";
	
	public static Connection getConnection() {
		try {
			Class.forName("org.postgresql.Driver");
			return DriverManager.getConnection(URL, USER, PASSWORD);
		}
		catch(SQLException | ClassNotFoundException e){
			throw new RuntimeException("Erro na conexao com o banco de dados");
		}
	}
	public static void closeConnection(Connection con) {
		if(con != null) {
			try {
				con.close();
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void closeConnection(Connection con, PreparedStatement stmt) {
		closeConnection(con);
		
		if(stmt != null) {
			try {
				stmt.close();
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
