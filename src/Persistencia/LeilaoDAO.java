package Persistencia;

import java.util.ArrayList;

public interface LeilaoDAO {
    ArrayList<LeilaoDTO> buscarTodos();
    LeilaoDTO buscarPorId(String id);
    void inserir(LeilaoDTO leilao);
    void alterar(LeilaoDTO leilao);
}
