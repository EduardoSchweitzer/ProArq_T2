package Interface;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JList;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import Negocio.Leilao;
import Negocio.LeilaoFachada;

import java.awt.Font;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;


public class LeilaoMainInterface {

	private JFrame frame;
	private ArrayList<Leilao> leiloes;
	private LeilaoFachada leilaoFachada;

	/**
	 * Launch the application.
	 */
	public static void leilaoMain() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LeilaoMainInterface window = new LeilaoMainInterface();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LeilaoMainInterface() {
		leilaoFachada = new LeilaoFachada();
		leiloes = new ArrayList<Leilao>();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 736, 518);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 42, 325, 437);
		frame.getContentPane().add(scrollPane);

		JList listaLeiloes = atualizarListaLeiloes();
		listaLeiloes.setFont(new Font("Tahoma", Font.PLAIN, 20));
		listaLeiloes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Leilao leilao = leiloes.get(listaLeiloes.getSelectedIndex());
				LeilaoAtual.leilaoAtualMain(leilao);
			}
		});
		scrollPane.setViewportView(listaLeiloes);
		listaLeiloes.setBackground(Color.WHITE);
		atualizarListaLeiloes();
		
		JLabel lblNewLabel = new JLabel("Leil\u00F5es ativos");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setBounds(0, 0, 325, 45);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnCriarLeilo = new JButton("Criar Leil\u00E3o");
		btnCriarLeilo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CriarLeilao.criarMain();
			}
		});
		btnCriarLeilo.setBounds(417, 69, 241, 63);
		frame.getContentPane().add(btnCriarLeilo);
		
		JButton btnMeusLeiles = new JButton("Meus Leil\u00F5es");
		btnMeusLeiles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MeusLeiloes.showMeusLeilos();
			}
		});
		btnMeusLeiles.setBounds(417, 184, 241, 63);
		frame.getContentPane().add(btnMeusLeiles);
		
				
	}
	
	public JList atualizarListaLeiloes() {
		leiloes = leilaoFachada.buscarAtivos();
		String[] nomes = new String[leiloes.size()];
		for (int i = 0; i < leiloes.size(); i++) {
			nomes[i] = leiloes.get(i).getNomeProduto();
		}
		JList<String> listaLeiloes = new JList<>(nomes);
		return listaLeiloes;
	}
}
