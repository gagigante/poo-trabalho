package ifnetpoo.DAO;

import ifnetpoo.Classes.Aluno;

import java.util.ArrayList;

public class AlunoDAO {
    private final ArrayList<Aluno> alunos = new ArrayList<>();
    
    public ArrayList<Aluno> getAlunos() {
        return this.alunos;
    }
    
    public Aluno buscarAlunoPeloProntuario(String prontuario) {
        for (Aluno aluno : this.alunos) {
            if (aluno.getProntuario().equals(prontuario)) {
                return aluno;
            }
        }
        
        throw new Error("Aluno não foi encontrado");
    }
    
    public Aluno cadastrarAluno(String nome, String prontuario, String email) {
        Aluno novoAluno = new Aluno(nome, prontuario, email); 
        
        this.alunos.add(novoAluno);
        
        return novoAluno;
    }
    
    public void removerAluno(int index) {
        int size = this.alunos.size();
        
        if (index < 0 || index > size - 1) {
            throw new Error("Aluno não encontrado");
        }
        
        this.alunos.remove(index);                
    }
}
