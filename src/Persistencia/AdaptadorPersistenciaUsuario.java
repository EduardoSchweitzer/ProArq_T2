package Persistencia;

import java.util.ArrayList;

import Negocio.Usuario;

public class AdaptadorPersistenciaUsuario {
	private UsuarioDAO uDAO;
	
	public AdaptadorPersistenciaUsuario(UsuarioDAO u) {
		uDAO = u;
	}
	
	public Usuario converterDeDTO(UsuarioDTO u) {
		return new Usuario(u.getCpf(), u.getNome(), u.getEmail(), u.getSenha(), u.isAdmin());	
	}
	
	public UsuarioDTO converterParaDTO(Usuario u) {
		UsuarioDTO uDTO = new UsuarioDTO();
		uDTO.setCpf(u.getCpf());
		uDTO.setNome(u.getNome());
		uDTO.setEmail(u.getEmail());
		uDTO.setSenha(u.getSenha());
		uDTO.setAdmin(u.isAdmin());
		return uDTO;
	}
	

	public ArrayList<Usuario> buscarTodos(){
		ArrayList<UsuarioDTO> lista = uDAO.buscarTodos();
		ArrayList<Usuario> nlista = new ArrayList<>();
		for(UsuarioDTO u : lista) {
			nlista.add(converterDeDTO(u));

		}
		return nlista;
	}
	
	
	public void inserir(Usuario u) {
		UsuarioDTO uDTO = converterParaDTO(u);
		uDAO.inserir(uDTO);

	}

}
