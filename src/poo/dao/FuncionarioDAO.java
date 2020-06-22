package poo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import poo.jdbc.ConnectionFactory;
import poo.model.Funcionario;

public class FuncionarioDAO {
	
	public void create(Funcionario funcionario) {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		
		String sql = "insert into funcionario(nome, senha, id) values (?, ?, ?)";
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, funcionario.getNome());
			stmt.setString(2, funcionario.getSenha());
			stmt.setString(3, funcionario.getId());
			
			stmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
	}
	
	public boolean checkLogin(String id, String senha) {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		boolean check = false;
		
		String sql = "select * from funcionario where id = ? and senha = ?";
		
		try {
			stmt = con.prepareStatement(sql);
			
			stmt.setString(1, id);
			stmt.setString(2, senha);
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				check = true;
			}
						
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
		
		return check;
	}
	
	public List<Funcionario> readAll(){
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String sql = "select * from funcionario";
		List<Funcionario> funcionarios = new ArrayList<Funcionario>();
		
		try {
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Funcionario funcionario = new Funcionario();
				
				funcionario.setNome(rs.getString("nome"));
				funcionario.setSenha(rs.getString("senha"));
				funcionario.setId(rs.getString("id"));
				
				funcionarios.add(funcionario);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
		
		return funcionarios;
	}
	
	public void update(Funcionario funcionario) {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		
		String sql = "update funcionario set nome = ?, senha = ? where id = ?";
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, funcionario.getNome());
			stmt.setString(2, funcionario.getSenha());
			stmt.setString(3, funcionario.getId());
			
			stmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
	}
	
	public void delete(Funcionario funcionario) {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		
		String sql = "delete from funcionario where id = ?";
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, funcionario.getId());
			
			stmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
	}
}
