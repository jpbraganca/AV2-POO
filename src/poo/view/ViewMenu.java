package poo.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ViewMenu extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewMenu frame = new ViewMenu();
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
	public ViewMenu() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel homeTitlePane = new JPanel();
		homeTitlePane.setBounds(12, 5, 425, 36);
		homeTitlePane.setBorder(null);
		contentPane.add(homeTitlePane);
		
		JLabel lblNewLabel = new JLabel("Sistema de Biblioteca");
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 17));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		homeTitlePane.add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBounds(12, 53, 425, 201);
		contentPane.add(panel);
		
		JButton clienteButton = new JButton("Cliente");
		clienteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewCliente cliente = new ViewCliente();
				cliente.setLocationRelativeTo(null);
				cliente.setTitle("Cliente");
				cliente.setVisible(true);
			}
		});
		
		JButton funcionarioButton = new JButton("Funcionario");
		funcionarioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewFuncionario funcionario =new ViewFuncionario();
				funcionario.setLocationRelativeTo(null);
				funcionario.setTitle("Funcionario");
				funcionario.setVisible(true);
			}
		});
		
		JButton livroButton = new JButton("Livro");
		livroButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewLivro livro = new ViewLivro();
				livro.setLocationRelativeTo(null);
				livro.setTitle("Livro");
				livro.setVisible(true);
			}
		});
		
		JButton exitButton = new JButton("Sair");
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				LoginFrame login = new LoginFrame();
				login.setLocationRelativeTo(null);
				login.setTitle("Login do Sistema");
				login.setVisible(true);
			}
		});
		exitButton.setBackground(new Color(205, 92, 92));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(176)
							.addComponent(exitButton, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(63)
							.addComponent(clienteButton)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(funcionarioButton)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(livroButton)))
					.addContainerGap(69, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(65)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(funcionarioButton)
						.addComponent(clienteButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(livroButton))
					.addGap(56)
					.addComponent(exitButton)
					.addGap(30))
		);
		panel.setLayout(gl_panel);
	}
}
