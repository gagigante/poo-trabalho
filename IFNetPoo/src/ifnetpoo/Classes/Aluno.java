/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifnetpoo.Classes;

import java.util.ArrayList;

/**
 *
 * @author gabriel
 */
public class Aluno extends Usuario {
    private final ArrayList<Disciplina> disciplinas;
    
    public Aluno(String nome, String prontuario, String email, ArrayList<Disciplina> disciplinas) {
        super(nome, prontuario, email);
        
        this.disciplinas = disciplinas;
    }
    
    public void cadastrarDisciplina(Disciplina disciplina) {
        this.disciplinas.add(disciplina);
    }
    
    public ArrayList<Disciplina> getDisciplinas() {
        return this.disciplinas;
    }
    
    public void imprimeDisciplinas() {
        disciplinas.forEach(d -> {
            System.out.println(d.getNome());
        });
    }
    
    @Override
    public String tipoUsuario() {
        return "aluno";
    }
    
    public void removerDisciplina(String nome) {
        
    }
}
