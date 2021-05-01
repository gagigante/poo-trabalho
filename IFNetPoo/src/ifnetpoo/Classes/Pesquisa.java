/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifnetpoo.Classes;

/**
 *
 * @author gabriel
 */
public class Pesquisa extends Grupo {
    private final Usuario orientador;

    public Pesquisa(Usuario orientador, String nome) {
        super(nome, orientador);
        
        this.orientador = orientador;
    }

    public Usuario getOrientador() {
        return orientador;
    }
    
    @Override
    public String getGrupoOverview() {
        return "Tipo: Pesquisa; Nome: " + this.getNome() + "; Criador: " + this.getCriador().getNome() + "; Orientador: " + this.getOrientador().getNome();
    }
}
