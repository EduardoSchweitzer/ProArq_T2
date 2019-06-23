package Interface;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import Negocio.Leilao;
import Negocio.UsuarioException;
import Negocio.UsuarioFachada;

import java.awt.Font;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JPanel;
import java.awt.Dimension;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

public class LeilaoAtualAdmin {

	private JFrame frame;
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


	/**
	 * Launch the application.
	 */
	public static void leilaoAtualMain(Leilao leilao) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LeilaoAtualAdmin window = new LeilaoAtualAdmin(leilao);
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
	public LeilaoAtualAdmin(Leilao leilao) {
		this.leilao = leilao;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setMinimumSize(new Dimension(300, 300));
		frame.setBounds(100, 100, 300, 300);
		frame.setTitle(leilao.getNomeProduto());
		frame.getContentPane().setLayout(null);
		
		
		lblProductLabel = new JLabel(leilao.getNomeProduto());
		lblProductLabel.setBounds(24, 54, 239, 22);
		lblProductLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblProductLabel.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lblProductLabel);
		
		panel = new JPanel();
		panel.setBounds(0, 108, 282, 29);
		frame.getContentPane().add(panel);
		
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
		frame.getContentPane().add(lblProponente);
		
		try {
			lblNome = new JLabel(new UsuarioFachada().buscarPorCpf(leilao.getCpfProponente()).getNome());
		} catch (UsuarioException e1) {
			e1.printStackTrace();
		}
		lblNome.setBounds(0, 160, 282, 19);
		lblNome.setHorizontalAlignment(SwingConstants.CENTER);
		lblNome.setFont(new Font("Tahoma", Font.BOLD, 15));
		frame.getContentPane().add(lblNome);
		
		lblLanceInvalido = new JLabel("");
		lblLanceInvalido.setBounds(0, 185, 282, 22);
		lblLanceInvalido.setHorizontalAlignment(SwingConstants.CENTER);
		lblLanceInvalido.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblLanceInvalido.setForeground(Color.RED);
		frame.getContentPane().add(lblLanceInvalido);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(282, 260, 2, 1);
		frame.getContentPane().add(scrollPane);
		
		frame.addWindowListener(new WindowAdapter()
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
