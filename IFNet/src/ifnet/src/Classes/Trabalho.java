/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifnet.src.Classes;

/**
 *
 * @author gabriel
 */
public class Trabalho extends Grupo {
    private Disciplina diciplina;

    public Trabalho(Disciplina diciplina, String nome, Aluno[] alunos) {
        super(nome, alunos);
        
        this.diciplina = diciplina;
    }

    public Disciplina getDiciplina() {
        return diciplina;
    }

    public void setDiciplina(Disciplina diciplina) {
        this.diciplina = diciplina;
    }
}
