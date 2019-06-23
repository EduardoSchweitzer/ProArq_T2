package Negocio;

import Negocio.Leilao.Lance;

public class ValidadorLeilao {
	
	public ValidadorLeilao() {}
	
	public boolean validaPreco(double preco) {
		return preco < 0;
	}

	public boolean validaString(String texto) {
		return texto.length() > 0 && texto.length() <= 150;
	}

	public boolean validaLance(Lance lance) {
		return lance.getValor() < 0;
	}
	
	public boolean validaMaiorLance(Lance lance, Leilao leilao) {
		return leilao.getMaiorLance().getValor( )> lance.getValor();
	}
	
	public boolean lanceValido(Lance lance, Leilao leilao) {
		return validaLance(lance) && validaMaiorLance(lance, leilao);
	}

	public boolean validaId(String id) {
		return id.length() == 9;
	}

}
