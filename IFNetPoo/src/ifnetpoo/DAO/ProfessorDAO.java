package ifnetpoo.DAO;

import ifnetpoo.Models.Professor;
import ifnetpoo.CustomExceptions.ExcessaoDuplicacao;

import java.util.ArrayList;

public class ProfessorDAO {
    private final ArrayList<Professor> professores = new ArrayList<>();
    
    public ArrayList<Professor> getProfessores() {
        return this.professores;
    }
    
    public Professor buscarProfessorPeloProntuario(String prontuario) {
        for (Professor professor : this.professores) {
            if (professor.getProntuario().equals(prontuario)) {
                return professor;
            }
        }
        
        throw new Error("Professor não foi encontrado");
    }
    
    public Professor cadastraProfessor(String nome, String prontuario, String email, String area) {
        Professor novoProfessor = new Professor(nome, prontuario, email, area);

        this.professores.forEach(professor -> {
            if (professor.compareTo(novoProfessor) == 1) {
                throw new ExcessaoDuplicacao("Já existe um usuário com esse prontuário", professor.getProntuario());
            };
        });

        this.professores.add(novoProfessor);

        return novoProfessor;
    }
    
    public void removerProfessor(int index) {
        int size = this.professores.size();
        
        if (index < 0 || index > size - 1) {
            throw new Error("Professor não encontrado");
        }
        
        this.professores.remove(index);                
    }
}
