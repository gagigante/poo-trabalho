/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifnetpoo.DAO;

import ifnetpoo.Classes.Professor;
import ifnetpoo.CustomExceptions.ExcessaoDuplicacao;
import java.util.ArrayList;

/**
 *
 * @author gabriel
 */
public class ProfessorDAO {
    private ArrayList<Professor> professores = new ArrayList<>();
    
    public ArrayList<Professor> getProfessores() {
        return this.professores;
    }
    
    public Professor buscarProfessorPeloProntuario(String prontuario) {
        for (Professor professor : this.professores) {
            if (professor.getProtuario().equals(prontuario)) {
                return professor;
            }
        }
        
        throw new Error("Professor não foi encontrado");
    }
    
    public Professor cadastraProfessor(String nome, String prontuario, String email, String area) {
        Professor novoProfessor = new Professor(nome, prontuario, email, area);

        this.professores.forEach(professor -> {
            if (professor.compareTo(novoProfessor) == 1) {
                throw new ExcessaoDuplicacao("Já existe um usuário com esse prontuário", professor.getProtuario());
            };
        });

        this.professores.add(novoProfessor);

        return novoProfessor;
    }
}
