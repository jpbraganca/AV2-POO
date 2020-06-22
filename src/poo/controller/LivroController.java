package poo.controller;

import java.util.ArrayList;
import java.util.List;

import poo.dao.LivroDAO;
import poo.model.Livro;

public class LivroController {
	
	public void create(Livro livro) {
		try {
			LivroDAO dao = new LivroDAO();
			
			dao.create(livro);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<Livro> readAll(){
		List<Livro> livros = new ArrayList<Livro>();
		
		try {
			LivroDAO dao = new LivroDAO();
			
			livros = dao.readAll();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return livros;
	}
	
	public void update(Livro livro) {
		try {
			LivroDAO dao = new LivroDAO();
			
			dao.update(livro);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void delete(Livro livro) {
		try {
			LivroDAO dao = new LivroDAO();
			
			dao.delete(livro);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
