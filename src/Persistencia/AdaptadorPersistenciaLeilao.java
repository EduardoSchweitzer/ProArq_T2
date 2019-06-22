package Persistencia;

import java.util.ArrayList;

import Negocio.Leilao;
import Negocio.Leilao.Lance;
import Persistencia.LeilaoDTO.LanceDTO;


public class AdaptadorPersistenciaLeilao {
	private LeilaoDAO lDAO;
	
	public AdaptadorPersistenciaLeilao(LeilaoDAO l) {
		lDAO = l;
	}
	
	public Leilao converterDeDTO(LeilaoDTO l) {
		Leilao leilao = new Leilao(l.getNomeProduto(), l.getPrecoInicial(), l.getIdProponente());
		leilao.setId(l.getId());
		leilao.atualizaMaiorLance(l.getMaiorLanceDTO().getValor(), l.getMaiorLanceDTO().getUsuario());
		leilao.setUltimaModificacao(l.getUltimaModificacao());
		for(LanceDTO lance : l.getHistoricoLanceDTOS()) {
			leilao.addLance(lance.getValor(), lance.getUsuario());
		}
		return leilao;
	}
	
	public LeilaoDTO converterParaDTO(Leilao l) {
		LeilaoDTO lDTO = new LeilaoDTO();
		lDTO.setNomeProduto(l.getNomeProduto());
		lDTO.setPrecoInicial(l.getPrecoInicial());
		lDTO.setIdProponente(l.getIdProponente());
		lDTO.setId(l.getId());
		lDTO.atualizaMaiorLanceDTO(l.getMaiorLance().getValor(), l.getMaiorLance().getUsuario());
		lDTO.setUltimaModificacao(l.getUltimaModificacao());
		for(Lance lance : l.getHistoricoLances()) {
			lDTO.addLanceDTO(lance.getValor(), lance.getUsuario());
		}		
		return lDTO;
	}
	
	public ArrayList<Leilao> buscarTodos(){
		ArrayList<LeilaoDTO> lista = lDAO.buscarTodos();
		ArrayList<Leilao> nlista = new ArrayList<>();
		for(LeilaoDTO l : lista) {
			nlista.add(converterDeDTO(l));

		}
		return nlista;
	}
	
	public void inserir(Leilao l) {
		LeilaoDTO lDTO = converterParaDTO(l);
		lDAO.inserir(lDTO);

	}

}
