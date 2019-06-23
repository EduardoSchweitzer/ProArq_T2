package Negocio;

import Persistencia.*;

import java.util.ArrayList;

public class LeilaoFachada {

    LeilaoDAO leilaoDAO;
    ValidadorLeilao vLeilao;
    AdaptadorPersistenciaLeilao adpLei;

    public LeilaoFachada() {
        this.leilaoDAO = new LeilaoDAOJson();
        this.vLeilao = new ValidadorLeilao();
        this.adpLei = new AdaptadorPersistenciaLeilao(leilaoDAO);
    }

    public void inserir(String nomeProduto, double precoInicial, String cpfProponente) throws LeilaoException, LeilaoDAOIdDuplicadoException {
        if (!vLeilao.validaString(nomeProduto)) {
            throw new LeilaoException("Nome de produto invalido");
        }

        if (!vLeilao.validaPreco(precoInicial)) {
            throw new LeilaoException("Preco inicial invalido");
        }
        Leilao leilao = new Leilao(nomeProduto, precoInicial, cpfProponente);
        adpLei.inserir(leilao);
    }

    public Leilao buscarPorId(String id) throws LeilaoException {
        if (!vLeilao.validaId(id)) {
            throw new LeilaoException("ID invalido.");
        }
        return adpLei.buscarPorId(id);
    }

    public ArrayList<Leilao> buscarTodos() {
        return adpLei.buscarTodos();
    }

    public ArrayList<Leilao> buscarAtivos() {
        return adpLei.buscarAtivos();
    }

    public ArrayList<Leilao> buscarFinalizados() {
        return adpLei.buscarFinalizados();
    }

    public void alterar(Leilao leilao) throws LeilaoDAOIdInexistenteException {
        adpLei.alterar(leilao);
    }

    public void addLance(double valor, String cpfUsuario, String idLeilao) throws LeilaoException, LeilaoDAOIdInexistenteException {
        if (!vLeilao.validaPreco(valor)) {
            throw new LeilaoException("Valor invalido");
        }
        Leilao leilao = buscarPorId(idLeilao);
        if (leilao == null) {
            throw new LeilaoException("Leilao nao encontrado");
        }
        
        if (leilao.getHistoricoLances().get(leilao.getHistoricoLances().size()-1).getValor() >= valor) {
        	throw new LeilaoException("Valor invalido");
        }
        leilao.addLance(valor, cpfUsuario);
        alterar(leilao);

    }
}
