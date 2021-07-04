/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifnetpoo.Controllers;

import ifnetpoo.Models.Aluno;
import ifnetpoo.Models.Professor;
import ifnetpoo.Models.Disciplina;

import ifnetpoo.DAO.DisciplinaDAO;

import ifnetpoo.Database.MySQLConnection;

import ifnetpoo.CustomExceptions.ExcessaoItemNaoEncontrado;

import java.util.ArrayList;

/**
 *
 * @author gabriel
 */
public class DisciplinaController {
    private final DisciplinaDAO disciplinaDAO;
    
    public DisciplinaController() {
       var conn = new MySQLConnection();
       
       this.disciplinaDAO = new DisciplinaDAO(conn);
    }
    
    public ArrayList<Disciplina> index() {
        return this.disciplinaDAO.getDisciplinas();
    }
    
    public Disciplina show(int index) {
        var disciplinas = this.disciplinaDAO.getDisciplinas();
        int size = disciplinas.size();
        
        if (index < 0 || index > size - 1) {
            throw new ExcessaoItemNaoEncontrado("Disciplina não foi encontrada");
        }
        
        return disciplinas.get(index);
    }
    
    public Disciplina store(String nome, String sigla) {
        return this.disciplinaDAO.cadastraDisciplina(nome, sigla);
    }
    
    public Disciplina destroy(int index) {
        var disciplinas = this.disciplinaDAO.getDisciplinas();
        int size = disciplinas.size();
        
        if (index < 0 || index > size - 1) {
            throw new ExcessaoItemNaoEncontrado("Disciplina não foi encontrada");
        }
        
        var disciplinaDeletada = disciplinas.get(index);
        
        this.disciplinaDAO.removerDisciplina(disciplinaDeletada.getId());
        
        return disciplinaDeletada;
    }
    
    public void associaAluno(Disciplina disciplina, Aluno aluno) {
        this.disciplinaDAO.associaDiciplinaUsuario(disciplina.getSigla(), aluno.getProntuario());
    }
    
    public void associaProfessor(Disciplina disciplina, Professor professor) {
        this.disciplinaDAO.associaDiciplinaUsuario(disciplina.getSigla(), professor.getProntuario());
    }
}
