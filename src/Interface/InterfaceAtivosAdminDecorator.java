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
import java.awt.Cursor;

public class InterfaceAtivosAdminDecorator extends JFrame{
	
	private ArrayList<Leilao> leiloes;
	
	public InterfaceAtivosAdminDecorator() {
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
		
		JLabel lblNewLabel = new JLabel("Leil\u00F5es ativos");
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
		
		JButton btnVerInativos = new JButton("Ver inativos");
		btnVerInativos.setBounds(47, 419, 300, 23);
		btnVerInativos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Interface.InterfaceInativosAdminDecorator().setVisible(true);
				dispose();
			}
		});
		this.getContentPane().add(btnVerInativos);	
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
