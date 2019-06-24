package Interface;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;

import Negocio.Leilao;

public class MeusLeiloesDecorator extends JFrame{
	
	private ArrayList<Leilao> leiloesAtivos;
	private ArrayList<Leilao> leiloesInativos;
	private Negocio.Usuario usuarioAtual;
	
	public MeusLeiloesDecorator(Negocio.Usuario usuarioAtual) {
		super();
		leiloesAtivos = new ArrayList<Leilao>();
		leiloesInativos = new ArrayList<Leilao>();
		this.usuarioAtual = usuarioAtual;
		inicializar();
		
	}
	
	public void inicializar() {
		this.setResizable(false);
		this.setTitle("Meus Leil\u00F5es");
		this.setBounds(100, 100, 400, 500);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.getContentPane().setLayout(null);
		
		JLabel lblMeusLeiles = new JLabel("Meus leil\u00F5es");
		lblMeusLeiles.setBounds(124, 23, 151, 34);
		lblMeusLeiles.setFont(new Font("Tahoma", Font.PLAIN, 28));
		this.getContentPane().add(lblMeusLeiles);
		
		JLabel lblAtivos = new JLabel("Ativos");
		lblAtivos.setBounds(25, 68, 162, 17);
		lblAtivos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		this.getContentPane().add(lblAtivos);
		
		JLabel lblFechados = new JLabel("Fechados");
		lblFechados.setBounds(197, 68, 172, 17);
		lblFechados.setFont(new Font("Tahoma", Font.PLAIN, 14));
		this.getContentPane().add(lblFechados);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(25, 85, 162, 363);
		this.getContentPane().add(scrollPane_1);
		
		JList listLeiloesAtivos = atualizarListaLeiloesAtivos();
		scrollPane_1.setViewportView(listLeiloesAtivos);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(197, 85, 172, 363);
		this.getContentPane().add(scrollPane);
		
		JList listLeiloesFechados = atualizarListaLeiloesInativos();
		scrollPane.setViewportView(listLeiloesFechados);
		
		listLeiloesAtivos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Leilao leilao = leiloesAtivos.get(listLeiloesAtivos.getSelectedIndex());
				if (leilao.getCpfProponente().matches(usuarioAtual.getCpf())) {
					LeilaoAtualProponente.leilaoAtualMain(leilao, usuarioAtual);
				} else {
					LeilaoAtual.leilaoAtualMain(leilao, usuarioAtual);
				}
				dispose();
			}
		});
		
		listLeiloesFechados.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Leilao leilao = leiloesInativos.get(listLeiloesFechados.getSelectedIndex());
				if (leilao.getCpfProponente().matches(usuarioAtual.getCpf())) {
					LeilaoAtualProponente.leilaoAtualMain(leilao, usuarioAtual);
				} else {
					LeilaoAtual.leilaoAtualMain(leilao, usuarioAtual);
				}
				dispose();
			}
		});
	}
	
	public JList atualizarListaLeiloesAtivos() {
		leiloesAtivos = new Negocio.LeilaoFachada().buscarAtivosPorCpf(usuarioAtual.getCpf());
		String[] nomes = new String[leiloesAtivos.size()];
		for (int i = 0; i < leiloesAtivos.size(); i++) {
			nomes[i] = leiloesAtivos.get(i).getNomeProduto();
		}
		JList<String> listaLeiloes = new JList<>(nomes);
		return listaLeiloes;
	}
	
	public JList atualizarListaLeiloesInativos() {
		leiloesInativos = new Negocio.LeilaoFachada().buscarFinalizadosPorCpf(usuarioAtual.getCpf());
		String[] nomes = new String[leiloesInativos.size()];
		for (int i = 0; i < leiloesInativos.size(); i++) {
			nomes[i] = leiloesInativos.get(i).getNomeProduto();
		}
		JList<String> listaLeiloes = new JList<>(nomes);
		return listaLeiloes;
		
	}

}
