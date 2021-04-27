/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifnetpoo.CustomExceptions;

/**
 *
 * @author gabri
 */
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
