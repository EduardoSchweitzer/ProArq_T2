package Persistencia;

import java.util.ArrayList;

public class LeilaoDTO {

    private String id;
    private String nomeProduto;
    private double precoInicial;
    private LanceDTO maiorLance;
    private ArrayList<LanceDTO> historicoLance;
    private String cpfProponente;
    private long ultimaModificacao;
    private boolean ativo;

    public LeilaoDTO() {
        this.historicoLance = new ArrayList<>();
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

    public LanceDTO getMaiorLance() {
        return maiorLance;
    }

    public void setMaiorLance(LanceDTO l) {
    	this.maiorLance = l;
    }

    public void setMaiorLance(double valor, String idUsuario) {
        this.maiorLance = new LanceDTO(valor, idUsuario);
    }
    
    public void atualizaMaiorLance(double valor, String usuario) {
    	this.maiorLance = new LanceDTO(valor, usuario);
    }

    public ArrayList<LanceDTO> getHistoricoLance() {
        return historicoLance;
    }

    public void setHistoricoLance(ArrayList<LanceDTO> historicoLance) {
        this.historicoLance = historicoLance;
    }

    public String getCpfProponente() {
        return cpfProponente;
    }

    public void setCpfProponente(String cpfProponente) {
        this.cpfProponente = cpfProponente;
    }
    
    public void addLance(double valor, String usuario) {
    	historicoLance.add(new LanceDTO(valor, usuario));
    }

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

        @Override
        public String toString() {
            return "{" +
                    "valor=" + valor +
                    ", usuario='" + usuario + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "LeilaoDTO{" +
                "id='" + id + '\'' +
                ", nomeProduto='" + nomeProduto + '\'' +
                ", precoInicial=" + precoInicial +
                ", maiorLance=" + maiorLance +
                ", historicoLance=" + historicoLance +
                ", cpfProponente='" + cpfProponente + '\'' +
                ", ultimaModificacao=" + ultimaModificacao +
                ", ativo=" + ativo +
                '}';
    }
}
