package Persistencia;

public class UsuarioDAOCpfInexistenteException extends Exception{

    public UsuarioDAOCpfInexistenteException(String mensagem) {
        super(mensagem);
    }
}