package Negocio;

import Persistencia.*;

import java.util.ArrayList;

public class UsuarioFachada {

    private UsuarioDAO usuarioDAO;
    private ValidadorUsuario vUsuario;
    private AdaptadorPersistenciaUsuario adpUsu;

    public UsuarioFachada() {
        usuarioDAO = new UsuarioDAOJson();
        vUsuario = new ValidadorUsuario();
        adpUsu = new AdaptadorPersistenciaUsuario(usuarioDAO);
    }

    public void inserir(String cpf, String nome, String email, String senha, boolean admin) throws UsuarioException, UsuarioDAOEmailDuplicadoException, UsuarioDAOCpfDuplicadoException {
        if (!vUsuario.validaCpf(cpf)) {
            throw new UsuarioException("CPF invalido.");
        }

        if (!vUsuario.validaNome(nome)) {
            throw new UsuarioException("Nome invalido.");
        }

        if (!vUsuario.validaEmail(email)) {
            throw new UsuarioException("Email invalido.");
        }

        if (!vUsuario.validaSenha(senha)) {
            throw new UsuarioException("Senha invalida.");
        }

        Usuario usuario = new Usuario(cpf, nome, email, senha, admin);
        usuarioDAO.inserir(adpUsu.converterParaDTO(usuario));
    }

    public Usuario buscarPorCpf(String cpf) throws UsuarioException {
        if (!vUsuario.validaCpf(cpf)) {
            throw new UsuarioException("CPF invalido.");
        }
        return adpUsu.buscarPorCpf(cpf);
    }

    public Usuario buscarPorEmail(String email) throws UsuarioException {
        if (!vUsuario.validaEmail(email)) {
            throw new UsuarioException("Email invalido.");
        }
        return adpUsu.buscarPorEmailf(email);
    }

    public ArrayList<Usuario> buscarTodos() {
        return adpUsu.buscarTodos();
    }

    public void alterar(String cpf, String novoNome, String novoEmail, String novaSenha, boolean admin) throws UsuarioDAOCpfInexistenteException {
        adpUsu.alterar(new Usuario(cpf, novoNome, novoEmail, novaSenha, admin));
    }
}
