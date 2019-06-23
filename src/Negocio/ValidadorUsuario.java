package Negocio;

public class ValidadorUsuario {
	
	public ValidadorUsuario() {}
	
	public boolean validaCpf(String cpf) {
		return cpf.length() == 11;
	}
	
	public boolean validaEmail(String email) {
		return email.matches("^(.+)@(.+)$");
	}
	
	public boolean validaSenha(String senha) {
		return senha.length() >= 4 && senha.length() <= 16;
	}

	public boolean validaNome(String nome) {
		return nome.length() > 0;
	}

}
