package Persistencia;

import java.util.ArrayList;

public class LeilaoDTO {

    private String id;
    private String nomeProduto;
    private double precoInicial;
    private LanceDTO maiorLanceDTO;
    private ArrayList<LanceDTO> historicoLanceDTOS;
    private String idProponente;
    private long ultimaModificacao;

    public LeilaoDTO() {
        this.historicoLanceDTOS = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public double getPrecoInicial() {
        return precoInicial;
    }

    public void setPrecoInicial(double precoInicial) {
        this.precoInicial = precoInicial;
    }

    public LanceDTO getMaiorLanceDTO() {
        return maiorLanceDTO;
    }

    public void setMaiorLance(LanceDTO l) {
    	this.maiorLanceDTO = l;
    }
    
    public void atualizaMaiorLanceDTO(double valor,String usuario) {
    	this.maiorLanceDTO = new LanceDTO(valor, usuario);
    }

    public ArrayList<LanceDTO> getHistoricoLanceDTOS() {
        return historicoLanceDTOS;
    }

    public void setHistoricoLanceDTOS(ArrayList<LanceDTO> historicoLanceDTOS) {
        this.historicoLanceDTOS = historicoLanceDTOS;
    }

    public String getIdProponente() {
        return idProponente;
    }

    public void setIdProponente(String idProponente) {
        this.idProponente = idProponente;
    }
    
    public void addLanceDTO(double valor, String usuario) {
    	historicoLanceDTOS.add(new LanceDTO(valor, usuario));
    }

    public long getUltimaModificacao() {
        return ultimaModificacao;
    }

    public void setUltimaModificacao(long ultimaModificacao) {
        this.ultimaModificacao = ultimaModificacao;
    }

    public class LanceDTO {

        private double valor;
        private String usuario;

        public LanceDTO(double valor, String usuario) {
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
