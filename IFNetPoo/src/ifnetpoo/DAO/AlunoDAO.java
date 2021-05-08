/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifnetpoo.DAO;

import ifnetpoo.Classes.Aluno;
import ifnetpoo.Classes.Disciplina;
import java.util.ArrayList;

/**
 *
 * @author gabriel
 */
public class AlunoDAO {
    private final ArrayList<Aluno> alunos = new ArrayList<>();
    
    public ArrayList<Aluno> getAlunos() {
        return this.alunos;
    }
    
    public Aluno cadastrarAluno(String nome, String prontuario, String email, ArrayList<Disciplina> disciplinas) {
        Aluno novoAluno = new Aluno(nome, prontuario, email, disciplinas); 
        
        this.alunos.add(novoAluno);
        
        return novoAluno;
    }
}
