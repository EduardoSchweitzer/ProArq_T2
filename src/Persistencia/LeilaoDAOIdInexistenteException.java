package Persistencia;

public class LeilaoDAOIdInexistenteException extends Exception{

    public LeilaoDAOIdInexistenteException(String mensagem) {
        super(mensagem);
    }
}
