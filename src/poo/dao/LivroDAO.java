package poo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import poo.jdbc.ConnectionFactory;
import poo.model.Livro;

public class LivroDAO {
	public void create(Livro livro) {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		
		String sql = "insert into livro (nome,id,categoria) values (?, ?, ?)";
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, livro.getNome());
			stmt.setString(2, livro.getId());
			stmt.setString(3, livro.getCategoria());
			
			stmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectionFactory.closeConnection(con, stmt);
		}	
	}
	
	public List <Livro> readAll(){
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String sql = "select * from livro";
		List<Livro> livros = new ArrayList<Livro>();
		
		try {
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Livro livro = new Livro();
				
				livro.setNome(rs.getString("nome"));
				livro.setId(rs.getString("id"));
				livro.setCategoria(rs.getString("categoria"));
				//livro.setCpf_cli(rs.getString("cpf_cli"));
				
				livros.add(livro);
			}
			
		}catch(SQLException e ) {
			e.printStackTrace();
		}finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
		return livros;
	}
	
	public void update(Livro livro) {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		
		String sql = "update livro set nome = ?, categoria = ? where id = ?";
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, livro.getNome());
			stmt.setString(2, livro.getId());
			stmt.setString(3, livro.getCategoria());
			//stmt.setString(4, livro.getCpf_cli());
			
			stmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
	}
	
	public void delete(Livro livro) {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		
		String sql = "delete from livro where id = ?";
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, livro.getId());
			
			stmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
	}
}
