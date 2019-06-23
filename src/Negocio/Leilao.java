package Negocio;

import java.util.ArrayList;
import java.util.Calendar;

public class Leilao {

	private String id;
	private String nomeProduto;
	private double precoInicial;
	private Lance maiorLance;
	private ArrayList<Lance> historicoLances;
	private String cpfProponente;
	private long ultimaModificacao;
	private boolean ativo;

	public Leilao(String nomeProduto, double precoInicial, String cpfProponente) {
		this.nomeProduto = nomeProduto;
		this.precoInicial = precoInicial;
		this.cpfProponente = cpfProponente;
		//TODO gerar id
		this.maiorLance = new Lance(precoInicial, this.cpfProponente);
		this.historicoLances = new ArrayList<>();
		this.historicoLances.add(maiorLance);
		this.ativo = true;
		ultimaModificacao = Calendar.getInstance().getTimeInMillis();
	}

	public Leilao(String id, String nomeProduto, double precoInicial, Lance maiorLance, ArrayList<Lance> historicoLances, String cpfProponente, long ultimaModificacao, boolean ativo) {
		this.id = id;
		this.nomeProduto = nomeProduto;
		this.precoInicial = precoInicial;
		this.maiorLance = maiorLance;
		this.historicoLances = historicoLances;
		this.cpfProponente = cpfProponente;
		this.ultimaModificacao = ultimaModificacao;
		this.ativo = ativo;
	}

	//ID
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	//Nome do produto
	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	//Preco inicial
	public double getPrecoInicial() {
		return precoInicial;
	}

	public void setPrecoInicial(double precoInicial) {
		this.precoInicial = precoInicial;
	}

	//Maior lance
	public Lance getMaiorLance() {
		return maiorLance;
	}
	
	public void setMaiorLance(Lance l) {
		this.maiorLance = l;
	}
	
	public void atualizaMaiorLance(Double valor, String idUsuario) {
		this.maiorLance = new Lance(valor, idUsuario);
	}

	//Historico de lances
	public ArrayList<Lance> getHistoricoLances() {
		return historicoLances;
	}

	public void setHistoricoLances(ArrayList<Lance> historicoLances) {
		this.historicoLances = historicoLances;
	}

	public void addLance(double valor, String idUsuario) {
		Lance novoLance = new Lance(valor, idUsuario);
		historicoLances.add(novoLance);
	}

	//ID proponente
	public String getCpfProponente() {
		return cpfProponente;
	}

	public void setCpfProponente(String cpfProponente) {
		this.cpfProponente = cpfProponente;
	}

	//Ultima modificação
	public long getUltimaModificacao() {
		return ultimaModificacao;
	}

	public void setUltimaModificacao(long ultimaModificacao) {
		this.ultimaModificacao = ultimaModificacao;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public class Lance {

		private double valor;
		private String usuario;

		public Lance(double valor, String usuario) {
			this.valor = valor;
			this.usuario = usuario;
		}

		//Valor
		public double getValor() {
			return valor;
		}

		//Usuario
		public String getUsuario() {
			return usuario;
		}
	}
}
