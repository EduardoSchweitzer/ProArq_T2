package Negocio;

import Negocio.Leilao.Lance;

public class ValidadorLeilao {
	
	public ValidadorLeilao() {}
	
	public boolean validaPreco(double preco) {
		if(preco<=0) return false;
		return true;
	}
	
	public boolean validaLance(Lance lance) {
		if(lance.getValor()<=0) return false;
		return true;
	}
	
	public boolean validaMaiorLance(Lance lance, Leilao leilao) {
		if(leilao.getMaiorLance().getValor()>lance.getValor()) return false;
		return true;
	}
	
	public boolean lanceValido(Lance lance, Leilao leilao) {
		if(validaLance(lance) && validaMaiorLance(lance, leilao)) return true;
		return false;
	}

}
