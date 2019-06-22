package Persistencia;

import java.util.ArrayList;

public interface UsuarioDAO {
    ArrayList<UsuarioDTO> buscarTodos();
    UsuarioDTO buscarPorCpf(String id);
    void inserir(UsuarioDTO usuario) throws UsuarioDAOException;
    void alterar(UsuarioDTO usuario) throws UsuarioDAOException;
}
