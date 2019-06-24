package Interface;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import Negocio.Leilao;
import Negocio.LeilaoFachada;

public class LeilaoMainInterfaceDecorator extends JFrame{
	
	private ArrayList<Leilao> leiloes;
	private Negocio.Usuario usuarioAtual;
	
	public LeilaoMainInterfaceDecorator(Negocio.Usuario usuarioAtual) {
		super();
		this.usuarioAtual = usuarioAtual;
		leiloes = new ArrayList<Leilao>();
		inicializar();
	}
	
	public void inicializar() {
		this.setTitle("Pagina inicial");
		this.setResizable(false);
		this.setBounds(100, 100, 400, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);
		
		JLabel lblUsurioName = new JLabel("  Usu\u00E1rio : " + usuarioAtual.getNome());
		lblUsurioName.setBounds(0, 6, 347, 14);
		this.getContentPane().add(lblUsurioName);
		
		JLabel lblNewLabel = new JLabel("Leil\u00F5es ativos");
		lblNewLabel.setBounds(47, 20, 300, 24);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBackground(Color.WHITE);
		this.getContentPane().add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(47, 69, 300, 331);
		this.getContentPane().add(scrollPane);
		
				JList listaLeiloes = atualizarListaLeiloes();
				listaLeiloes.setFont(new Font("Tahoma", Font.PLAIN, 20));
				listaLeiloes.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						Leilao leilao = leiloes.get(listaLeiloes.getSelectedIndex());
						if (leilao.getCpfProponente().matches(usuarioAtual.getCpf())) {
							LeilaoAtualProponente.leilaoAtualMain(leilao, usuarioAtual);
						} else {
							LeilaoAtual.leilaoAtualMain(leilao, usuarioAtual);
						}
						dispose();
					}
				});
				scrollPane.setViewportView(listaLeiloes);
				listaLeiloes.setBackground(Color.WHITE);
		
		JButton btnCriarLeilo = new JButton("Criar Leil\u00E3o");
		btnCriarLeilo.setBounds(47, 435, 300, 23);
		btnCriarLeilo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CriarLeilao.criarMain(usuarioAtual);
				dispose();
			}
		});
		
		JButton btnMeusLeiles = new JButton("Meus Leil\u00F5es");
		btnMeusLeiles.setBounds(47, 406, 300, 23);
		btnMeusLeiles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MeusLeiloes.showMeusLeilos(usuarioAtual);
				dispose();
			}
		});
		getContentPane().add(btnMeusLeiles);
		getContentPane().add(btnCriarLeilo);
	}
	
	public JList atualizarListaLeiloes() {
		leiloes = new Negocio.LeilaoFachada().buscarAtivos();
		String[] nomes = new String[leiloes.size()];
		for (int i = 0; i < leiloes.size(); i++) {
			nomes[i] = leiloes.get(i).getNomeProduto();
		}
		JList<String> listaLeiloes = new JList<>(nomes);
		return listaLeiloes;
	}

}
