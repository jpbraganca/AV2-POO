package poo.jdbc;

import java.sql.Connection;

public class ConnectionTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection con = ConnectionFactory.getConnection();
		
		System.out.println("Conexao Ok");

	}

}
