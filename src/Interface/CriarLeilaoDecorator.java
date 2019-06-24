package Interface;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class CriarLeilaoDecorator extends JFrame{
	
	private JTextField tfNomeProduto;
	private JTextField tfValor;
	private Negocio.Usuario usuarioAtual;
	
	public CriarLeilaoDecorator(Negocio.Usuario usuarioAtual) {
		super();
		this.usuarioAtual = usuarioAtual;
		inicializar();
	}
	
	public void inicializar() {
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		this.setTitle("Criar Leil\u00E3o");
		this.setBounds(100, 100, 350, 300);
		this.getContentPane().setLayout(null);
		
		JLabel lblCriarNovoLeilo = new JLabel("Criar novo leil\u00E3o");
		lblCriarNovoLeilo.setBounds(78, 27, 199, 34);
		lblCriarNovoLeilo.setFont(new Font("Tahoma", Font.PLAIN, 28));
		this.getContentPane().add(lblCriarNovoLeilo);
		
		JLabel lblNomeDoProduto = new JLabel("Nome do produto");
		lblNomeDoProduto.setBounds(45, 86, 265, 24);
		lblNomeDoProduto.setFont(new Font("Tahoma", Font.PLAIN, 14));
		this.getContentPane().add(lblNomeDoProduto);
		
		tfNomeProduto = new JTextField();
		tfNomeProduto.setBounds(45, 110, 265, 20);
		this.getContentPane().add(tfNomeProduto);
		tfNomeProduto.setColumns(10);
		
		JLabel lblValorInicial = new JLabel("Valor Inicial");
		lblValorInicial.setBounds(45, 130, 265, 24);
		lblValorInicial.setFont(new Font("Tahoma", Font.PLAIN, 14));
		this.getContentPane().add(lblValorInicial);
		
		tfValor = new JTextField();
		tfValor.setBounds(45, 154, 265, 20);
		this.getContentPane().add(tfValor);
		tfValor.setColumns(10);
		
		JButton btnCriar = new JButton("Criar");
		btnCriar.setBounds(45, 185, 265, 23);
		this.getContentPane().add(btnCriar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(45, 214, 265, 23);
		
		this.getContentPane().add(btnCancelar);
		
		JLabel lblInvalido = new JLabel("");
		lblInvalido.setForeground(Color.RED);
		lblInvalido.setHorizontalAlignment(SwingConstants.CENTER);
		lblInvalido.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblInvalido.setBounds(45, 64, 265, 24);
		this.getContentPane().add(lblInvalido);
		
		this.addWindowListener(new WindowAdapter()
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
				dispose();
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
					dispose();
					LeilaoMainInterface.leilaoMain(usuarioAtual);
				}
			}
		});
	}
}
