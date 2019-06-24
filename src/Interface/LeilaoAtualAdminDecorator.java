package Interface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import Negocio.Leilao;
import Negocio.UsuarioException;
import Negocio.UsuarioFachada;

public class LeilaoAtualAdminDecorator extends JFrame{
	
	private static JLabel lblValueLabel;
	private static JLabel lblProductLabel;
	private JScrollPane scrollPane;
	private Leilao leilao;
	private JTable table;
	private JPanel panel;
	private JLabel lblMaiorLance;
	private JLabel lblLanceInvalido;
	private JLabel lblProponente;
	private JLabel lblNome;
	
	public LeilaoAtualAdminDecorator(Leilao leilao) {
		super();
		this.leilao = leilao;
		inicializar();
	}
	
	public void inicializar() {
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setMinimumSize(new Dimension(300, 300));
		this.setBounds(100, 100, 300, 300);
		this.setTitle(leilao.getNomeProduto());
		this.getContentPane().setLayout(null);
		
		
		lblProductLabel = new JLabel(leilao.getNomeProduto());
		lblProductLabel.setBounds(24, 54, 239, 22);
		lblProductLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblProductLabel.setHorizontalAlignment(SwingConstants.CENTER);
		this.getContentPane().add(lblProductLabel);
		
		panel = new JPanel();
		panel.setBounds(0, 108, 282, 29);
		this.getContentPane().add(panel);
		
		lblMaiorLance = new JLabel("Maior Lance: ");
		lblMaiorLance.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel.add(lblMaiorLance);
		
		lblValueLabel = new JLabel(String.valueOf(leilao.getMaiorLance().getValor()));
		panel.add(lblValueLabel);
		lblValueLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		lblProponente = new JLabel("Proponente:");
		lblProponente.setBounds(0, 137, 282, 17);
		lblProponente.setHorizontalAlignment(SwingConstants.CENTER);
		lblProponente.setFont(new Font("Tahoma", Font.BOLD, 14));
		this.getContentPane().add(lblProponente);
		
		try {
			lblNome = new JLabel(new UsuarioFachada().buscarPorCpf(leilao.getCpfProponente()).getNome());
		} catch (UsuarioException e1) {
			e1.printStackTrace();
		}
		lblNome.setBounds(0, 160, 282, 19);
		lblNome.setHorizontalAlignment(SwingConstants.CENTER);
		lblNome.setFont(new Font("Tahoma", Font.BOLD, 15));
		this.getContentPane().add(lblNome);
		
		lblLanceInvalido = new JLabel("");
		lblLanceInvalido.setBounds(0, 185, 282, 22);
		lblLanceInvalido.setHorizontalAlignment(SwingConstants.CENTER);
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
            	LeilaoMainInterfaceAdmin.leilaoMain();
                e.getWindow().dispose();
            }
        });
	}
}
