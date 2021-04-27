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
    private ArrayList<Curso> cursos;
    private ArrayList<Disciplina> disciplinas;
    
    public Aluno(String nome, String prontuario, String email, ArrayList<Curso> cursos) {
        super(nome, prontuario, email);
        
        this.cursos = cursos;
    }
    
    public ArrayList<Curso> getCursos() {
        return this.cursos;
    }
    
    public void setCursos(ArrayList<Curso> cursos) {
        this.cursos = cursos;
    }
    
    public void cadastrarDisciplina(Disciplina disciplina) {
        this.disciplinas.add(disciplina);
    }
    
    public void removerDisciplina(String nome) {
        
    }
}
