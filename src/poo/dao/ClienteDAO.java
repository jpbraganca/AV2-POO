package poo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import poo.jdbc.ConnectionFactory;
import poo.model.Cliente;

public class ClienteDAO {
	public void create(Cliente cliente) {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		
		String sql = "insert into cliente (nome,endereco,cpf) values (?, ?, ?)";
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, cliente.getNome());
			stmt.setString(2, cliente.getEndereco());
			stmt.setString(3, cliente.getCpf());
			
			stmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectionFactory.closeConnection(con, stmt);
		}	
	}
	
	public List <Cliente> readAll() {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String sql = "select * from cliente";
		List<Cliente> clientes = new ArrayList<Cliente>();
		
		try {
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Cliente cliente = new Cliente();
				
				cliente.setNome(rs.getString("nome"));
				cliente.setEndereco(rs.getString("endereco"));
				cliente.setCpf(rs.getString("cpf"));
				
				clientes.add(cliente);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectionFactory.closeConnection(con,stmt);
		}
		
		return clientes;
	}
	
	public void update(Cliente cliente) {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		
		String sql = "update cliente set nome = ?, endereco = ? where cpf = ?";
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, cliente.getNome());
			stmt.setString(2, cliente.getEndereco());
			stmt.setString(3, cliente.getCpf());
			
			stmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
	}
	
	public void delete(Cliente cliente) {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		
		String sql = "delete from cliente where cpf = ?";
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, cliente.getCpf());
			
			stmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
	}
	
}
