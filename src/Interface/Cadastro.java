package Interface;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.JPasswordField;
import java.awt.Color;
import java.awt.Dimension;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.SwingConstants;

public class Cadastro {

	private JFrame frmCadastro;
	private JTextField tfNome;
	private JTextField tfCpf;
	private JTextField tfEmail;
	private JPasswordField pfSenha;
	private JPasswordField pfConfirmarSenha;

	/**
	 * Launch the application.
	 */
	public static void cadastroMain() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cadastro window = new Cadastro();
					window.frmCadastro.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Cadastro() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCadastro = new JFrame();
		frmCadastro.setResizable(false);
		frmCadastro.setMinimumSize(new Dimension(400, 500));
		frmCadastro.setTitle("Cadastro");
		frmCadastro.setBounds(100, 100, 400, 500);
		frmCadastro.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmCadastro.getContentPane().setLayout(null);
		
		JLabel lblNovaConta = new JLabel("Criar uma nova conta");
		lblNovaConta.setBounds(58, 36, 268, 34);
		frmCadastro.getContentPane().add(lblNovaConta);
		lblNovaConta.setFont(new Font("Tahoma", Font.PLAIN, 28));
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(45, 84, 36, 17);
		frmCadastro.getContentPane().add(lblNome);
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		tfNome = new JTextField();
		tfNome.setBounds(45, 107, 294, 20);
		frmCadastro.getContentPane().add(tfNome);
		tfNome.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(45, 133, 31, 17);
		frmCadastro.getContentPane().add(lblEmail);
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		tfEmail = new JTextField();
		tfEmail.setBounds(45, 156, 294, 20);
		frmCadastro.getContentPane().add(tfEmail);
		tfEmail.setColumns(10);
		
		JLabel lblCpf = new JLabel("Cpf");
		lblCpf.setBounds(45, 182, 21, 17);
		frmCadastro.getContentPane().add(lblCpf);
		lblCpf.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		tfCpf = new JTextField();
		tfCpf.setBounds(45, 205, 294, 20);
		frmCadastro.getContentPane().add(tfCpf);
		tfCpf.setColumns(10);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setBounds(45, 231, 38, 17);
		frmCadastro.getContentPane().add(lblSenha);
		lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		pfSenha = new JPasswordField();
		pfSenha.setBounds(45, 254, 294, 20);
		frmCadastro.getContentPane().add(pfSenha);
		
		JLabel lblConfirmarSenha = new JLabel("Confirmar senha");
		lblConfirmarSenha.setBounds(45, 280, 294, 17);
		lblConfirmarSenha.setFont(new Font("Tahoma", Font.PLAIN, 14));
		frmCadastro.getContentPane().add(lblConfirmarSenha);
		
		pfConfirmarSenha = new JPasswordField();
		pfConfirmarSenha.setBounds(45, 303, 294, 20);
		frmCadastro.getContentPane().add(pfConfirmarSenha);
		
		JCheckBox chckbxAdmin = new JCheckBox("Admin");
		chckbxAdmin.setBounds(45, 329, 70, 23);
		frmCadastro.getContentPane().add(chckbxAdmin);
		
		JPanel panel = new JPanel();
		panel.setBounds(45, 380, 294, 34);
		frmCadastro.getContentPane().add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnCancelar = new JButton("Cancelar");
		panel.add(btnCancelar);
		btnCancelar.setMinimumSize(new Dimension(90, 25));
		btnCancelar.setMaximumSize(new Dimension(90, 25));
		
		JButton btnConfirmar = new JButton("Confirmar");
		panel.add(btnConfirmar);
		btnConfirmar.setMaximumSize(new Dimension(90, 25));
		btnConfirmar.setMinimumSize(new Dimension(90, 25));
		
		
		JLabel lblXInvalido = new JLabel("");
		lblXInvalido.setHorizontalAlignment(SwingConstants.CENTER);
		lblXInvalido.setBounds(45, 424, 294, 22);
		lblXInvalido.setForeground(Color.RED);
		lblXInvalido.setFont(new Font("Tahoma", Font.PLAIN, 18));
		frmCadastro.getContentPane().add(lblXInvalido);
		
		frmCadastro.addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
            	Login.loginMain();
                e.getWindow().dispose();
            }
        });
		
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean invalido = false;
				if (pfSenha.getPassword().length >= 4) {
					if (new String(pfSenha.getPassword()).matches(new String(pfConfirmarSenha.getPassword()))) {
						try {
							new Negocio.UsuarioFachada().inserir(
									tfCpf.getText(), 
									tfNome.getText(), 
									tfEmail.getText(), 
									new String(pfSenha.getPassword()).toLowerCase(), 
									chckbxAdmin.isSelected());;
						} catch (Negocio.UsuarioException exc) {
							lblXInvalido.setText("Dados Invalidos.");
							invalido = true;
						} catch (Persistencia.UsuarioDAOCpfDuplicadoException exc) {
							lblXInvalido.setText("Este CPF ja esta cadastrado.");
							invalido = true;
						} catch (Persistencia.UsuarioDAOEmailDuplicadoException exc) {
							lblXInvalido.setText("Este Email ja esta cadastrado.");
							invalido = true;
						}
					} else {
						lblXInvalido.setText("As senhas sao diferentes.");
						invalido = true;
					}
				} else {
					lblXInvalido.setText("Senha muito pequena.");
					invalido = true;
				}
				
				if (!invalido) {
					frmCadastro.dispose();
					Login.loginMain();
				}
			}
		});
		
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmCadastro.dispose();
				Login.loginMain();
			}
		});
	}
}
