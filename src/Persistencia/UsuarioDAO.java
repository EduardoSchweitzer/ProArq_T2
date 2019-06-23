package Persistencia;

import java.util.ArrayList;

public interface UsuarioDAO {
    ArrayList<UsuarioDTO> buscarTodos();
    UsuarioDTO buscarPorCpf(String cpf);
    UsuarioDTO buscarPorEmail(String email);
    void inserir(UsuarioDTO usuario) throws UsuarioDAOCpfDuplicadoException, UsuarioDAOEmailDuplicadoException;
    void alterar(UsuarioDTO usuario) throws UsuarioDAOCpfInexistenteException;
}
