package Interface;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JList;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import Negocio.Leilao;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class MeusLeiloes {

	private JFrame frmMeusLeiles;
	private ArrayList<Leilao> leiloesAtivos;
	private ArrayList<Leilao> leiloesInativos;
	private Negocio.Usuario usuarioAtual;
	
	/**
	 * Launch the application.
	 * @return 
	 */
	public static void showMeusLeilos(Negocio.Usuario usuarioAtual) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MeusLeiloes window = new MeusLeiloes(usuarioAtual);
					window.frmMeusLeiles.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MeusLeiloes(Negocio.Usuario usuarioAtual) {
		leiloesAtivos = new ArrayList<Leilao>();
		leiloesInativos = new ArrayList<Leilao>();
		this.usuarioAtual = usuarioAtual;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMeusLeiles = new JFrame();
		frmMeusLeiles.setResizable(false);
		frmMeusLeiles.setTitle("Meus Leil\u00F5es");
		frmMeusLeiles.setBounds(100, 100, 400, 500);
		frmMeusLeiles.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmMeusLeiles.getContentPane().setLayout(null);
		
		JLabel lblMeusLeiles = new JLabel("Meus leil\u00F5es");
		lblMeusLeiles.setBounds(124, 23, 151, 34);
		lblMeusLeiles.setFont(new Font("Tahoma", Font.PLAIN, 28));
		frmMeusLeiles.getContentPane().add(lblMeusLeiles);
		
		JLabel lblAtivos = new JLabel("Ativos");
		lblAtivos.setBounds(25, 68, 162, 17);
		lblAtivos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		frmMeusLeiles.getContentPane().add(lblAtivos);
		
		JLabel lblFechados = new JLabel("Fechados");
		lblFechados.setBounds(197, 68, 172, 17);
		lblFechados.setFont(new Font("Tahoma", Font.PLAIN, 14));
		frmMeusLeiles.getContentPane().add(lblFechados);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(25, 85, 162, 363);
		frmMeusLeiles.getContentPane().add(scrollPane_1);
		
		JList listLeiloesAtivos = atualizarListaLeiloesAtivos();
		scrollPane_1.setViewportView(listLeiloesAtivos);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(197, 85, 172, 363);
		frmMeusLeiles.getContentPane().add(scrollPane);
		
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
				frmMeusLeiles.dispose();
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
				frmMeusLeiles.dispose();
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
