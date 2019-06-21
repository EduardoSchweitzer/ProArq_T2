package Negocio;

import java.util.ArrayList;
import java.util.Calendar;

public class Leilao {

	private String id;
	private String nomeProduto;
	private double precoInicial;
	private Lance maiorLance;
	private ArrayList<Lance> historicoLances;
	private String idProponente;
	private long ultimaModificacao;

	public Leilao(String nomeProduto, double precoInicial, String idProponente) {
		this.nomeProduto = nomeProduto;
		this.precoInicial = precoInicial;
		this.idProponente = idProponente;
		//TODO gerar id
		this.maiorLance = new Lance(precoInicial, this.idProponente);
		this.historicoLances = new ArrayList<>();
		ultimaModificacao = Calendar.getInstance().getTimeInMillis();
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

	public void setMaiorLance(Double valor, String idUsuario) {
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
	public String getIdProponente() {
		return idProponente;
	}

	public void setIdProponente(String idProponente) {
		this.idProponente = idProponente;
	}

	//Ultima modificação
	public long getUltimaModificacao() {
		return ultimaModificacao;
	}

	public void setUltimaModificacao(long ultimaModificacao) {
		this.ultimaModificacao = ultimaModificacao;
	}


	private class Lance {

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

		public void setValor(double valor) {
			this.valor = valor;
		}

		//Usuario
		public String getUsuario() {
			return usuario;
		}

		public void setUsuario(String usuario) {
			this.usuario = usuario;
		}
	}
}
