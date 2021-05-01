/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifnet.DAO;

import ifnet.Classes.Aluno;
import ifnet.Classes.Curso;
import java.util.ArrayList;

/**
 *
 * @author gabriel
 */
public class AlunoDAO {
    private ArrayList<Aluno> alunos;
    
    public ArrayList<Aluno> getAlunos() {
        return this.alunos;
    }
    
    public Aluno cadastrarAluno(String nome, String prontuario, String email, ArrayList<Curso> cursos) {
        Aluno novoAluno = new Aluno(nome, prontuario, email, cursos); 
        
        this.alunos.add(novoAluno);
        
        return novoAluno;
    }
}
