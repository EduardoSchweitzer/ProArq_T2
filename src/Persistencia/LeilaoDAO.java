package Persistencia;

import java.util.ArrayList;

public interface LeilaoDAO {
    ArrayList<LeilaoDTO> buscarTodos();
    ArrayList<LeilaoDTO> buscarAtivos();
    ArrayList<LeilaoDTO> buscarFinalizados();
    LeilaoDTO buscarPorId(String id);
    void inserir(LeilaoDTO leilao) throws LeilaoDAOIdDuplicadoException;
    void alterar(LeilaoDTO leilao) throws LeilaoDAOIdInexistenteException;
}
