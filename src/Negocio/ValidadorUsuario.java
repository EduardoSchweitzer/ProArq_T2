package Negocio;

public class ValidadorUsuario {
	
	public ValidadorUsuario() {}
	
	public boolean validaCpf(String cpf) {
		if(cpf.length()==11) return true;
		return false;
	}
	
	public boolean validaEmail(String email) {
		if(email.contains("@")) return true;
		return false;
	}
	
	public boolean validaSenha(String senha) {
		if(senha.length()<4) return false;
		return true;
	}

}
