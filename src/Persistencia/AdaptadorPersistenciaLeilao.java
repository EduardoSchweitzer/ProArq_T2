package Persistencia;

import java.util.ArrayList;

import Negocio.Leilao;
import Negocio.Leilao.Lance;
import Persistencia.LeilaoDTO.LanceDTO;


public class AdaptadorPersistenciaLeilao {
	private LeilaoDAO leilaoDAO;
	
	public AdaptadorPersistenciaLeilao(LeilaoDAO leilaoDAO) {
		this.leilaoDAO = leilaoDAO;
	}
	
	public Leilao converterDeDTO(LeilaoDTO leilaoDTO) {
		Leilao leilao = new Leilao(leilaoDTO.getNomeProduto(), leilaoDTO.getPrecoInicial(), leilaoDTO.getCpfProponente());
		leilao.setId(leilaoDTO.getId());
		leilao.atualizaMaiorLance(leilaoDTO.getMaiorLance().getValor(), leilaoDTO.getMaiorLance().getUsuario());
		leilao.setUltimaModificacao(leilaoDTO.getUltimaModificacao());
		leilao.setAtivo(leilaoDTO.isAtivo());
		for(LanceDTO lance : leilaoDTO.getHistoricoLance()) {
			leilao.addLance(lance.getValor(), lance.getUsuario());
		}
		return leilao;
	}
	
	public LeilaoDTO converterParaDTO(Leilao leilao) {
		LeilaoDTO leilaoDTO = new LeilaoDTO();
		leilaoDTO.setNomeProduto(leilao.getNomeProduto());
		leilaoDTO.setPrecoInicial(leilao.getPrecoInicial());
		leilaoDTO.setCpfProponente(leilao.getCpfProponente());
		leilaoDTO.setId(leilao.getId());
		leilaoDTO.atualizaMaiorLance(leilao.getMaiorLance().getValor(), leilao.getMaiorLance().getUsuario());
		leilaoDTO.setUltimaModificacao(leilao.getUltimaModificacao());
		leilaoDTO.setAtivo(leilao.isAtivo());
		for(Lance lance : leilao.getHistoricoLances()) {
			leilaoDTO.addLance(lance.getValor(), lance.getUsuario());
		}		
		return leilaoDTO;
	}
	
	public Leilao buscarPorId(String id) {
		return converterDeDTO(leilaoDAO.buscarPorId(id));
	}

	public ArrayList<Leilao> buscarTodos(){
		ArrayList<LeilaoDTO> lista = leilaoDAO.buscarTodos();
		ArrayList<Leilao> nlista = new ArrayList<>();
		for(LeilaoDTO leilao : lista) {
			nlista.add(converterDeDTO(leilao));

		}
		return nlista;
	}

	public ArrayList<Leilao> buscarAtivos() {
		ArrayList<LeilaoDTO> lista = leilaoDAO.buscarAtivos();
		ArrayList<Leilao> nlista = new ArrayList<>();
		for(LeilaoDTO leilao : lista) {
			nlista.add(converterDeDTO(leilao));
		}
		return nlista;
	}

	public ArrayList<Leilao> buscarFinalizados() {
		ArrayList<LeilaoDTO> lista = leilaoDAO.buscarFinalizados();
		ArrayList<Leilao> nlista = new ArrayList<>();
		for(LeilaoDTO leilao : lista) {
			nlista.add(converterDeDTO(leilao));

		}
		return nlista;
	}

	public void inserir(Leilao leilao) throws LeilaoDAOIdDuplicadoException {
		leilaoDAO.inserir(converterParaDTO(leilao));
	}

	public void alterar(Leilao leilao) throws LeilaoDAOIdInexistenteException {
		leilaoDAO.alterar(converterParaDTO(leilao));
	}

}
