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
    private Professor orientador;

    public Pesquisa(Professor orientador, String nome, Aluno[] alunos) {
        super(nome, alunos);
        this.orientador = orientador;
    }

    public Professor getOrientador() {
        return orientador;
    }

    public void setOrientador(Professor orientador) {
        this.orientador = orientador;
    }    
}
