package ifnetpoo.CustomExceptions;

public class ExcessaoDuplicacao extends Error {
    private final String valorDuplicado;
    
    public ExcessaoDuplicacao(String mensagem, String valorDuplicado) {
        super(mensagem);
        
        this.valorDuplicado = valorDuplicado;
    }
    
    public String getValorDuplicado() {
        return this.valorDuplicado;
    }
}
