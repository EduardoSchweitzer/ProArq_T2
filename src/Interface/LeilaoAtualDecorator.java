package Interface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Negocio.Leilao;

public class LeilaoAtualDecorator extends JFrame{
	
	private static JLabel lblValueLabel;
	private static JLabel lblProductLabel;
	private JScrollPane scrollPane;
	private JButton btnDarLance;
	private JTextField textField;
	private Leilao leilao;
	private Negocio.Usuario usuarioAtual;
	private JPanel panel;
	private JLabel lblMaiorLance;
	private JLabel lblLanceInvalido;
	
	public LeilaoAtualDecorator(Negocio.Leilao leilao, Negocio.Usuario usuarioAtual) {
		super();
		inicializar();
	}
	
	
	public void inicializar() {
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setMinimumSize(new Dimension(300, 300));
		this.setBounds(100, 100, 300, 300);
		this.setTitle(leilao.getNomeProduto());
		this.getContentPane().setLayout(null);
		
		
		lblProductLabel = new JLabel(leilao.getNomeProduto());
		lblProductLabel.setBounds(24, 30, 236, 22);
		lblProductLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblProductLabel.setHorizontalAlignment(SwingConstants.CENTER);
		this.getContentPane().add(lblProductLabel);
		
		panel = new JPanel();
		panel.setBounds(0, 84, 282, 29);
		this.getContentPane().add(panel);
		
		lblMaiorLance = new JLabel("Maior Lance: ");
		lblMaiorLance.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel.add(lblMaiorLance);
		
		lblValueLabel = new JLabel(String.valueOf(leilao.getMaiorLance().getValor()));
		panel.add(lblValueLabel);
		lblValueLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		textField = new JTextField();
		textField.setBounds(39, 136, 197, 20);
		this.getContentPane().add(textField);
		textField.setColumns(10);
		
		btnDarLance = new JButton("Dar lance");
		
		btnDarLance.setBounds(39, 167, 197, 27);
		btnDarLance.setFont(new Font("Tahoma", Font.PLAIN, 15));
		this.getContentPane().add(btnDarLance);
		
		lblLanceInvalido = new JLabel("");
		lblLanceInvalido.setHorizontalAlignment(SwingConstants.CENTER);
		lblLanceInvalido.setBounds(39, 206, 210, 22);
		lblLanceInvalido.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblLanceInvalido.setForeground(Color.RED);
		this.getContentPane().add(lblLanceInvalido);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(282, 260, 2, 1);
		this.getContentPane().add(scrollPane);
		
		this.addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
            	LeilaoMainInterface.leilaoMain(usuarioAtual);
                e.getWindow().dispose();
            }
        });
		
		btnDarLance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblLanceInvalido.setText("");
				try {
					Negocio.Leilao checarBanco = new Negocio.LeilaoFachada().buscarPorId(leilao.getId());
					if (leilao.getUltimaModificacao() < checarBanco.getUltimaModificacao()) {
						leilao = checarBanco;
						lblValueLabel.setText(String.valueOf(leilao.getMaiorLance().getValor()));
						lblLanceInvalido.setText("Houve outro lance.");
					} else {
						leilao.setUltimaModificacao(leilao.getUltimaModificacao()+1);
						new Negocio.LeilaoFachada().addLance(
								Double.valueOf(textField.getText()), 
								usuarioAtual.getCpf(), 
								leilao.getId());
						lblValueLabel.setText(String.valueOf(new Negocio.LeilaoFachada().buscarPorId(leilao.getId()).getMaiorLance().getValor()));
					}
				} catch (Negocio.LeilaoException exc) {
					lblLanceInvalido.setText("Valor invalido");
				} catch (Persistencia.LeilaoDAOIdInexistenteException exc) {
					
				}
			}
		});
	}
}
