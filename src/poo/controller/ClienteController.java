package poo.controller;

import java.util.ArrayList;
import java.util.List;

import poo.dao.ClienteDAO;
import poo.model.Cliente;

public class ClienteController {
	public void create(Cliente cliente) {
		try {
			ClienteDAO dao = new ClienteDAO();
			
			dao.create(cliente);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<Cliente> readAll(){
		List<Cliente> clientes = new ArrayList<Cliente>();
		
		try {
			ClienteDAO dao = new ClienteDAO();
			clientes = dao.readAll();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return clientes;
	}
	
	public void update(Cliente cliente) {
		try {
			ClienteDAO dao = new ClienteDAO();
			
			dao.update(cliente);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void delete(Cliente cliente) {
		try {
			ClienteDAO dao = new ClienteDAO();
			
			dao.delete(cliente);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
