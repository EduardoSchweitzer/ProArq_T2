package Interface;

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
import javax.swing.JTable;
import javax.swing.SwingConstants;

import Negocio.Leilao;
import Persistencia.LeilaoDAOIdInexistenteException;

public class LeilaoAtualProponenteDecorator extends JFrame{
	
	private static JLabel lblValueLabel;
	private static JLabel lblProductLabel;
	private JScrollPane scrollPane;
	private JButton btnEncerrar;
	private Leilao leilao;
	private Negocio.Usuario usuarioAtual;
	private JPanel panel;
	private JLabel lblMaiorLance;
	
	public LeilaoAtualProponenteDecorator(Leilao leilao, Negocio.Usuario usuarioAtual) {
		super();
		this.leilao = leilao;
		this.usuarioAtual = usuarioAtual;
		inicializar();
	}
	
	public void inicializar() {
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setMinimumSize(new Dimension(300, 300));
		this.setBounds(100, 100, 300, 300);
		this.setTitle(leilao.getNomeProduto());
		this.getContentPane().setLayout(null);
		
		
		lblProductLabel = new JLabel(leilao.getNomeProduto());
		lblProductLabel.setBounds(0, 47, 282, 22);
		lblProductLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblProductLabel.setHorizontalAlignment(SwingConstants.CENTER);
		this.getContentPane().add(lblProductLabel);
		
		panel = new JPanel();
		panel.setBounds(0, 101, 282, 29);
		this.getContentPane().add(panel);
		
		lblMaiorLance = new JLabel("Maior Lance: ");
		lblMaiorLance.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel.add(lblMaiorLance);
		
		lblValueLabel = new JLabel(String.valueOf(leilao.getMaiorLance().getValor()));
		panel.add(lblValueLabel);
		lblValueLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		btnEncerrar = new JButton("Encerrar leil\u00E3o");
		if (!leilao.isAtivo()) btnEncerrar.setVisible(false);
		btnEncerrar.setMinimumSize(new Dimension(150, 25));
		btnEncerrar.setMaximumSize(new Dimension(150, 25));
		btnEncerrar.setBounds(51, 153, 180, 27);
		btnEncerrar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		this.getContentPane().add(btnEncerrar);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(282, 260, 2, 1);
		this.getContentPane().add(scrollPane);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				LeilaoMainInterface.leilaoMain(usuarioAtual);
			}
		});
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCancelar.setBounds(51, 191, 180, 27);
		this.getContentPane().add(btnCancelar);
		
		this.addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
            	LeilaoMainInterface.leilaoMain(usuarioAtual);
                e.getWindow().dispose();
            }
        });
		
		btnEncerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					leilao = new Negocio.LeilaoFachada().buscarPorId(leilao.getId());
					leilao.setAtivo(false);
					new Negocio.LeilaoFachada().alterar(leilao);
					dispose();
					LeilaoMainInterface.leilaoMain(usuarioAtual);
				} catch (Negocio.LeilaoException exc) {
					
				} catch (LeilaoDAOIdInexistenteException exc) {
				
				}
			}
		});
	}

}
