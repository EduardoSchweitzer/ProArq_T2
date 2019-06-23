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
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;


public class LeilaoMainInterface {

	private JFrame frmPaginaInicial;
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
					window.frmPaginaInicial.setVisible(true);
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
		frmPaginaInicial = new JFrame();
		frmPaginaInicial.setTitle("Pagina inicial");
		frmPaginaInicial.setResizable(false);
		frmPaginaInicial.setBounds(100, 100, 400, 500);
		frmPaginaInicial.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPaginaInicial.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Leil\u00F5es ativos");
		lblNewLabel.setBounds(47, 14, 300, 24);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBackground(Color.WHITE);
		frmPaginaInicial.getContentPane().add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(47, 63, 300, 331);
		frmPaginaInicial.getContentPane().add(scrollPane);
		
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
		
		JButton btnCriarLeilo = new JButton("Criar Leil\u00E3o");
		btnCriarLeilo.setBounds(47, 429, 300, 23);
		btnCriarLeilo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CriarLeilao.criarMain();
				frmPaginaInicial.dispose();
			}
		});
		
		JButton btnMeusLeiles = new JButton("Meus Leil\u00F5es");
		btnMeusLeiles.setBounds(47, 400, 300, 23);
		btnMeusLeiles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MeusLeiloes.showMeusLeilos();
			}
		});
		frmPaginaInicial.getContentPane().add(btnMeusLeiles);
		frmPaginaInicial.getContentPane().add(btnCriarLeilo);
		
				
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
