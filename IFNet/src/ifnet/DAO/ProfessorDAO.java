/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifnet.DAO;

import ifnet.src.Classes.Professor;
import java.util.ArrayList;

/**
 *
 * @author gabriel
 */
public class ProfessorDAO {
    private ArrayList<Professor> professores;
    
    public ArrayList<Professor> getProfessores() {
        return this.professores;
    }
    
    public Professor cadastraProfessor(String nome, String prontuario, String email, String area) {
        Professor novoProfessor = new Professor(nome, prontuario, email, area);
        
        this.professores.add(novoProfessor);
        
        return novoProfessor;
    }
}
