/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifnetpoo.Controllers;

import ifnetpoo.Models.Professor;

import ifnetpoo.DAO.DisciplinaDAO;
import ifnetpoo.DAO.ProfessorDAO;

import ifnetpoo.Database.MySQLConnection;

import ifnetpoo.CustomExceptions.ExcessaoItemNaoEncontrado;

import java.util.ArrayList;

/**
 *
 * @author gabriel
 */
public class ProfessorController {
    private final ProfessorDAO professorDAO;
    private final DisciplinaDAO disciplinaDAO;
    
    public ProfessorController() {
       var conn = new MySQLConnection();
       
       this.professorDAO = new ProfessorDAO(conn);
       this.disciplinaDAO = new DisciplinaDAO(conn);
    }
    
    public ArrayList<Professor> index() {
        var professores = this.professorDAO.getProfessores();
        
        for (var professor : professores) {
            var disciplinas = this.disciplinaDAO.getDisciplinasUsuario(professor.getProntuario());
            professor.setDisciplinas(disciplinas);
        }
        
        return professores;
    }
    
    public Professor store(String nome, String prontuario, String email, String area) {
        return this.professorDAO.cadastraProfessor(nome, prontuario, email, area);
    }
    
    public Professor destroy(int index) {
        var professores = this.professorDAO.getProfessores();
        int size = professores.size();
        
        if (index < 0 || index > size - 1) {
            throw new ExcessaoItemNaoEncontrado("Professor não foi encontrado");
        }
        
        var professorDeletado = professores.get(index);
        
        this.professorDAO.removerProfessor(professorDeletado.getId());
        
        return professorDeletado;
    }
}
