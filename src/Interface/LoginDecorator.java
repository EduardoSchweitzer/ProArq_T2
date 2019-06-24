package Interface;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class LoginDecorator extends JFrame{
	
	private JTextField tfEmail;
	private JPasswordField tfSenha;
	
	public LoginDecorator() {
		super();
		inicializar();
	}
	
	public void inicializar() {
		this.setResizable(false);
		this.setMaximumSize(new Dimension(400, 400));
		this.setMinimumSize(new Dimension(400, 400));
		this.setTitle("Login");
		this.setBounds(100, 100, 400, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);
		
		JLabel lblEntrar = new JLabel("Entrar");
		lblEntrar.setBounds(154, 49, 76, 34);
		lblEntrar.setFont(new Font("Tahoma", Font.PLAIN, 28));
		this.getContentPane().add(lblEntrar);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(50, 114, 33, 19);
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 15));
		this.getContentPane().add(lblEmail);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setBounds(50, 167, 40, 19);
		lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 15));
		this.getContentPane().add(lblSenha);
		
		tfEmail = new JTextField();
		tfEmail.setBounds(50, 133, 285, 20);
		this.getContentPane().add(tfEmail);
		tfEmail.setColumns(10);
		
		tfSenha = new JPasswordField();
		tfSenha.setBounds(50, 186, 285, 20);
		this.getContentPane().add(tfSenha);
		tfSenha.setColumns(10);
		
		
		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.setBounds(50, 256, 285, 23);
		
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setBounds(50, 285, 285, 23);
		
		JLabel lblAviso = new JLabel("");
		lblAviso.setHorizontalAlignment(SwingConstants.CENTER);
		lblAviso.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblAviso.setBounds(50, 220, 285, 22);
		lblAviso.setForeground(Color.RED);
		lblAviso.setFont(new Font("Tahoma", Font.PLAIN, 18));
		this.getContentPane().add(lblAviso);
		this.getContentPane().add(btnEntrar);
		this.getContentPane().add(btnCadastrar);
		
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Negocio.Usuario usuarioAtual = null;
				boolean invalido = false;
				try {
					usuarioAtual = new Negocio.UsuarioFachada().buscarPorEmail(tfEmail.getText());
					
				} catch (Negocio.UsuarioException exc) {
					lblAviso.setText("Email invalido");
					invalido = true;
				}
				
				if (usuarioAtual != null) {
					dispose();
					if (usuarioAtual.isAdmin()) {
						LeilaoMainInterfaceAdmin.leilaoMain();
					} else {
						LeilaoMainInterface.leilaoMain(usuarioAtual);
					}
				} else {
					if (!invalido) {
						lblAviso.setText("Usuario nao econtrado");
					}
				}
			}
		});
		
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Cadastro.cadastroMain();
			}
		});
	}

}
