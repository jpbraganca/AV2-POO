package poo.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Panel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import poo.controller.FuncionarioController;
import poo.dao.FuncionarioDAO;
import poo.model.Funcionario;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginFrame extends JFrame {

	private JPanel contentPane;
	private JTextField idField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();
					frame.setLocationRelativeTo(null);
					frame.setTitle("Login do sistema");
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
	public LoginFrame() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("Login Funcionario");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		contentPane.add(lblNewLabel, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		idField = new JTextField();
		idField.setBounds(158, 45, 124, 19);
		idField.setColumns(10);
		panel.add(idField);
		
		JLabel lblId = new JLabel("ID");
		lblId.setBounds(138, 47, 21, 15);
		panel.add(lblId);
		
		JLabel lblId_1 = new JLabel("Senha");
		lblId_1.setBounds(106, 96, 53, 15);
		panel.add(lblId_1);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(158, 94, 124, 19);
		panel.add(passwordField);
		
		JButton btnEntrar = new JButton("Entrar");
				
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				FuncionarioDAO dao = new FuncionarioDAO();
				
				if(dao.checkLogin(idField.getText(), passwordField.getText())) {
					ViewMenu menu = new ViewMenu();
					menu.setLocationRelativeTo(null);
					menu.setTitle("Menu");
					menu.setVisible(true);
					dispose();
				}else {
					JOptionPane.showMessageDialog(null, "id/senha incorreto(a)");
				}
				
			}
		});
		btnEntrar.setBounds(96, 138, 117, 25);
		panel.add(btnEntrar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(224, 138, 117, 25);
		panel.add(btnCancelar);
	}
}
