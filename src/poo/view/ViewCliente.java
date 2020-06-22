package poo.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import poo.controller.ClienteController;
import poo.model.Cliente;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.Button;

public class ViewCliente extends JFrame {

	private JPanel mainPane;
	private JTextField cpfField;
	private JTextField nameField;
	private JTextField enderecoField;
	private JTable funcTable;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewCliente frame = new ViewCliente();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ViewCliente() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 396);
		mainPane = new JPanel();
		mainPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(mainPane);
		
		JPanel inputPane = new JPanel();
		
		JPanel tablePane = new JPanel();
		GroupLayout gl_mainPane = new GroupLayout(mainPane);
		gl_mainPane.setHorizontalGroup(
			gl_mainPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_mainPane.createSequentialGroup()
					.addGroup(gl_mainPane.createParallelGroup(Alignment.LEADING)
						.addComponent(inputPane, GroupLayout.PREFERRED_SIZE, 430, GroupLayout.PREFERRED_SIZE)
						.addComponent(tablePane, GroupLayout.PREFERRED_SIZE, 430, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_mainPane.setVerticalGroup(
			gl_mainPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_mainPane.createSequentialGroup()
					.addComponent(inputPane, GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(tablePane, GroupLayout.PREFERRED_SIZE, 223, GroupLayout.PREFERRED_SIZE))
		);
		tablePane.setLayout(null);
		
		funcTable = new JTable();
		funcTable.setColumnSelectionAllowed(true);
		funcTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 430, 223);
		tablePane.add(scrollPane);
		
		DefaultTableModel modelo = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"cpf", "nome", "endereco"
				}
			);
		
		funcTable.setModel(modelo);
		scrollPane.setViewportView(funcTable);
		
		funcTable.setCellSelectionEnabled(true);
		
		show_user();
		
		JButton createButton = new JButton("Cadastrar");
		createButton.setBackground(new Color(144, 238, 144));
		createButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				DefaultTableModel funcModel = (DefaultTableModel) funcTable.getModel();
				Cliente cliente = new Cliente();
				ClienteController controller = new ClienteController();
				
				cliente.setCpf(cpfField.getText());
				cliente.setNome(nameField.getText());
				cliente.setEndereco(enderecoField.getText());
				
				//Insere no Banco
				controller.create(cliente);
				
				Object[] dados = {cpfField.getText(), nameField.getText(), enderecoField.getText()};
				funcModel.addRow(dados);
				
				JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso");
				clearFields();
				
			}
		});
		
		funcTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(funcTable.getSelectedRow() != -1) {
					cpfField.setText(funcTable.getValueAt(funcTable.getSelectedRow(), 0).toString());
					nameField.setText(funcTable.getValueAt(funcTable.getSelectedRow(), 1).toString());
					enderecoField.setText(funcTable.getValueAt(funcTable.getSelectedRow(), 2).toString());
					cpfField.setEditable(false);
					createButton.setEnabled(false);
				}
			}
		});
		
		JButton updateButton = new JButton("Atualizar");
		updateButton.setBackground(new Color(240, 230, 140));
		updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(funcTable.getSelectedRow() != -1) {					
					Cliente cliente = new Cliente();
					ClienteController controller = new ClienteController();
					
					cliente.setCpf(cpfField.getText());
					cliente.setNome(nameField.getText());
					cliente.setEndereco(enderecoField.getText());
					
					controller.update(cliente);
					
					funcTable.setValueAt(cpfField.getText(), funcTable.getSelectedRow(), 0);
					funcTable.setValueAt(nameField.getText(), funcTable.getSelectedRow(), 1);
					funcTable.setValueAt(enderecoField.getText(), funcTable.getSelectedRow(), 2);
					
					JOptionPane.showMessageDialog(null, "Cliente atualizado com sucesso");
					createButton.setEnabled(true);
					cpfField.setEditable(true);
					clearFields();
				}else {
					JOptionPane.showMessageDialog(null, "Selecione um cliente");
				}
			}
		});
		
		JLabel cpfLabel = new JLabel("cpf");
		
		cpfField = new JTextField();
		cpfField.setColumns(10);
		
		JLabel nameLabel = new JLabel("nome");
		
		nameField = new JTextField();
		nameField.setColumns(10);
		
		JLabel enderecoLabel = new JLabel("endereco");
		
		enderecoField = new JTextField();
		enderecoField.setColumns(10);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setBackground(new Color(240, 128, 128));
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(funcTable.getSelectedRow() != -1) {
					Cliente cliente = new Cliente();
					ClienteController controller = new ClienteController();
					DefaultTableModel funcModel = (DefaultTableModel) funcTable.getModel();
					
					cliente.setCpf((String) funcTable.getValueAt(funcTable.getSelectedRow(), 0));
					controller.delete(cliente);
					
					funcModel.removeRow(funcTable.getSelectedRow());
					JOptionPane.showMessageDialog(null, "Cliente excluido com sucesso");
					createButton.setEnabled(true);
					cpfField.setEditable(true);
					clearFields();
				}else {
					JOptionPane.showMessageDialog(null, "Selecione um cliente");
				}
				
			}
		});
		
		JButton btnLimparCampos = new JButton("Limpar Campos");
		btnLimparCampos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearFields();
				createButton.setEnabled(true);
				cpfField.setEditable(true);
			}
		});
		btnLimparCampos.setFont(new Font("Dialog", Font.BOLD, 10));
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVoltar.setFont(new Font("Dialog", Font.BOLD, 9));
		GroupLayout gl_inputPane = new GroupLayout(inputPane);
		gl_inputPane.setHorizontalGroup(
			gl_inputPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_inputPane.createSequentialGroup()
					.addGroup(gl_inputPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_inputPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_inputPane.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(createButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(cpfField)
								.addComponent(cpfLabel, Alignment.LEADING))
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(Alignment.TRAILING, gl_inputPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnVoltar)
							.addGap(36)))
					.addGroup(gl_inputPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_inputPane.createSequentialGroup()
							.addGroup(gl_inputPane.createParallelGroup(Alignment.LEADING)
								.addComponent(nameLabel)
								.addComponent(nameField, GroupLayout.PREFERRED_SIZE, 154, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED))
						.addGroup(gl_inputPane.createSequentialGroup()
							.addGroup(gl_inputPane.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(btnLimparCampos, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
								.addComponent(btnExcluir, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE))
							.addGap(28)))
					.addGroup(gl_inputPane.createParallelGroup(Alignment.LEADING)
						.addComponent(updateButton)
						.addComponent(enderecoLabel, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
						.addComponent(enderecoField))
					.addContainerGap())
		);
		gl_inputPane.setVerticalGroup(
			gl_inputPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_inputPane.createSequentialGroup()
					.addGap(17)
					.addGroup(gl_inputPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(cpfLabel)
						.addComponent(nameLabel)
						.addComponent(enderecoLabel))
					.addGap(1)
					.addGroup(gl_inputPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(cpfField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(nameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(enderecoField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_inputPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_inputPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(updateButton, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
							.addComponent(createButton, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnExcluir, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_inputPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnLimparCampos)
						.addComponent(btnVoltar))
					.addGap(14))
		);
		inputPane.setLayout(gl_inputPane);
		mainPane.setLayout(gl_mainPane);
	}
	
	public void show_user() {
			DefaultTableModel modelo = (DefaultTableModel) funcTable.getModel();
			try {
				ClienteController controller = new ClienteController();
				//List<Funcionario> lista = controller.readAll();
				
				for(Cliente f : controller.readAll()) {
					modelo.addRow(new Object[] {f.getCpf(), f.getNome(), f.getEndereco()});
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
	}
	
	public void clearFields() {
		cpfField.setText(null);
		nameField.setText(null);
		enderecoField.setText(null);
	}
}
