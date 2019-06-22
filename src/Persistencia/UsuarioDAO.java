package Persistencia;

import java.util.ArrayList;

public interface UsuarioDAO {
    ArrayList<UsuarioDTO> buscarTodos();
    UsuarioDTO buscarPorId(String id);
    void inserir(UsuarioDTO usuario);
    void alterar(UsuarioDTO usuario);
}
