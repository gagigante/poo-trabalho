/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifnetpoo.CustomExceptions;

/**
 *
 * @author gabriel
 */
public class ExcessaoUsuarioJaAssociado extends Error {
    public ExcessaoUsuarioJaAssociado(String mensagem) {
        super(mensagem);
    }
}
