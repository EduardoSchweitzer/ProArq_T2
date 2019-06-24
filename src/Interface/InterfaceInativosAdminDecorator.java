package Interface;

import java.awt.Color;
import java.awt.Cursor;
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

public class InterfaceInativosAdminDecorator extends JFrame{
	
	private ArrayList<Leilao> leiloes;
	
	public InterfaceInativosAdminDecorator() {
		super();
		leiloes = new ArrayList<Leilao>();
		inicializar();
	}
	
	public void inicializar() {
		this.setTitle("Pagina inicial");
		this.setResizable(false);
		this.setBounds(100, 100, 400, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JLabel lblUsurioAdmin = new JLabel("  Usu\u00E1rio: Admin");
		lblUsurioAdmin.setBounds(0, 10, 347, 14);
		getContentPane().add(lblUsurioAdmin);
		
		JLabel lblNewLabel = new JLabel("Leil\u00F5es inativos");
		lblNewLabel.setBounds(47, 43, 300, 24);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBackground(Color.WHITE);
		this.getContentPane().add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(47, 82, 300, 331);
		this.getContentPane().add(scrollPane);
		
				JList listaLeiloes = atualizarListaLeiloes();
				listaLeiloes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				listaLeiloes.setFont(new Font("Tahoma", Font.PLAIN, 20));
				listaLeiloes.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						Leilao leilao = leiloes.get(listaLeiloes.getSelectedIndex());
						dispose();
						LeilaoAtualAdmin.leilaoAtualMain(leilao);
					}
				});
				scrollPane.setViewportView(listaLeiloes);
				listaLeiloes.setBackground(Color.WHITE);
		
		JButton btnVerAtivos = new JButton("Ver ativos");
		btnVerAtivos.setBounds(47, 419, 300, 23);
		btnVerAtivos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Interface.InterfaceAtivosAdminDecorator().setVisible(true);
				dispose();
			}
		});
		this.getContentPane().add(btnVerAtivos);	
	}
	
	public JList atualizarListaLeiloes() {
		leiloes = new Negocio.LeilaoFachada().buscarFinalizados();
		String[] nomes = new String[leiloes.size()];
		for (int i = 0; i < leiloes.size(); i++) {
			nomes[i] = leiloes.get(i).getNomeProduto();
		}
		JList<String> listaLeiloes = new JList<>(nomes);
		return listaLeiloes;
	}

}
