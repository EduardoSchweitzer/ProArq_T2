package Negocio;

public class Usuario {

	private String cpf;
	private String nome;
	private String id;
	private String email;
	private String senha;
	private boolean admin;

	public Usuario(String cpf, String nome, String id, String email, String senha, boolean admin) {
		this.cpf = cpf;
		this.nome = nome;
		this.id = id; //TODO gerar id
		this.email = email;
		this.senha = senha;
		this.admin = admin;
	}

	//CPF
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	//Nome
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	//ID
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	//Email
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	//Senha
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	//Admin
	public void setAdmin(boolean admin) {

	}

	public boolean isAdmin() {
		return false;
	}

}
