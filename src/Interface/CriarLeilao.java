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
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.SwingConstants;
import java.awt.Color;

public class CriarLeilao {

	private JFrame frmCriarLeilo;
	private JTextField tfNomeProduto;
	private JTextField tfValor;
	private Negocio.Usuario usuarioAtual;

	/**
	 * Launch the application.
	 */
	public static void criarMain(Negocio.Usuario usuarioAtual) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CriarLeilao window = new CriarLeilao(usuarioAtual);
					window.frmCriarLeilo.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CriarLeilao(Negocio.Usuario usuarioAtual) {
		this.usuarioAtual = usuarioAtual;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCriarLeilo = new JFrame();
		frmCriarLeilo.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmCriarLeilo.setResizable(false);
		frmCriarLeilo.setTitle("Criar Leil\u00E3o");
		frmCriarLeilo.setBounds(100, 100, 350, 300);
		frmCriarLeilo.getContentPane().setLayout(null);
		
		JLabel lblCriarNovoLeilo = new JLabel("Criar novo leil\u00E3o");
		lblCriarNovoLeilo.setBounds(78, 27, 199, 34);
		lblCriarNovoLeilo.setFont(new Font("Tahoma", Font.PLAIN, 28));
		frmCriarLeilo.getContentPane().add(lblCriarNovoLeilo);
		
		JLabel lblNomeDoProduto = new JLabel("Nome do produto");
		lblNomeDoProduto.setBounds(45, 86, 265, 24);
		lblNomeDoProduto.setFont(new Font("Tahoma", Font.PLAIN, 14));
		frmCriarLeilo.getContentPane().add(lblNomeDoProduto);
		
		tfNomeProduto = new JTextField();
		tfNomeProduto.setBounds(45, 110, 265, 20);
		frmCriarLeilo.getContentPane().add(tfNomeProduto);
		tfNomeProduto.setColumns(10);
		
		JLabel lblValorInicial = new JLabel("Valor Inicial");
		lblValorInicial.setBounds(45, 130, 265, 24);
		lblValorInicial.setFont(new Font("Tahoma", Font.PLAIN, 14));
		frmCriarLeilo.getContentPane().add(lblValorInicial);
		
		tfValor = new JTextField();
		tfValor.setBounds(45, 154, 265, 20);
		frmCriarLeilo.getContentPane().add(tfValor);
		tfValor.setColumns(10);
		
		JButton btnCriar = new JButton("Criar");
		btnCriar.setBounds(45, 185, 265, 23);
		frmCriarLeilo.getContentPane().add(btnCriar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(45, 214, 265, 23);
		
		frmCriarLeilo.getContentPane().add(btnCancelar);
		
		JLabel lblInvalido = new JLabel("");
		lblInvalido.setForeground(Color.RED);
		lblInvalido.setHorizontalAlignment(SwingConstants.CENTER);
		lblInvalido.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblInvalido.setBounds(45, 64, 265, 24);
		frmCriarLeilo.getContentPane().add(lblInvalido);
		
		frmCriarLeilo.addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                LeilaoMainInterface.leilaoMain(usuarioAtual);
                e.getWindow().dispose();
            }
        });
		
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmCriarLeilo.dispose();
				LeilaoMainInterface.leilaoMain(usuarioAtual);
			}
		});
		
		btnCriar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean invalido = false;
				try {
					new Negocio.LeilaoFachada().inserir(
							tfNomeProduto.getText(), 
							Double.valueOf(tfValor.getText()), 
							usuarioAtual.getCpf());
				} catch (Negocio.LeilaoException exc) {
					lblInvalido.setText("Dados invalidos.");
					invalido = true;
				} catch (Persistencia.LeilaoDAOIdDuplicadoException exc) {
					invalido = true;
				}
				
				if (!invalido) {
					frmCriarLeilo.dispose();
					LeilaoMainInterface.leilaoMain(usuarioAtual);
				}
			}
		});
	}
}
