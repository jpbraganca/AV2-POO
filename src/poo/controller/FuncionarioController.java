package poo.controller;

import java.util.ArrayList;
import java.util.List;

import poo.dao.FuncionarioDAO;
import poo.model.Funcionario;

public class FuncionarioController {
	public void create(Funcionario funcionario) {
		try {
			FuncionarioDAO dao = new FuncionarioDAO();
			
			dao.create(funcionario);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<Funcionario> readAll() {
		List<Funcionario> funcionarios = new ArrayList<Funcionario>();
		
		try {
			FuncionarioDAO dao = new FuncionarioDAO();
			
			funcionarios = dao.readAll();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return funcionarios;
	}
	
	public void update(Funcionario funcionario) {
		try {
			FuncionarioDAO dao = new FuncionarioDAO();
			
			dao.update(funcionario);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void delete(Funcionario funcionario) {
		try {
			FuncionarioDAO dao = new FuncionarioDAO();
			
			dao.delete(funcionario);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
