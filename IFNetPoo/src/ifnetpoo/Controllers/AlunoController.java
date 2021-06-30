/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifnetpoo.Controllers;

import ifnetpoo.Models.Aluno;

import ifnetpoo.DAO.AlunoDAO;
import ifnetpoo.DAO.DisciplinaDAO;

import ifnetpoo.Database.MySQLConnection;

import ifnetpoo.CustomExceptions.ExcessaoItemNaoEncontrado;

import java.util.ArrayList;

/**
 *
 * @author gabriel
 */
public class AlunoController {
    private final AlunoDAO alunoDAO;
    private final DisciplinaDAO disciplinaDAO;
    
    public AlunoController() {
       var conn = new MySQLConnection();
       
       this.alunoDAO = new AlunoDAO(conn);
       this.disciplinaDAO = new DisciplinaDAO(conn);
    }
    
    public ArrayList<Aluno> index() {
        var alunos = this.alunoDAO.getAlunos();
        
        for (var aluno : alunos) {
            var disciplinas = this.disciplinaDAO.getDisciplinasUsuario(aluno.getProntuario());
            aluno.setDisciplinas(disciplinas);
        }
        
        return alunos;
    }
    
    public Aluno store(String nome, String prontuario, String email) {
        return this.alunoDAO.cadastrarAluno(nome, prontuario, email);
    }
    
    public Aluno destroy(int index) {
        var alunos = this.alunoDAO.getAlunos();
        int size = alunos.size();
        
        if (index < 0 || index > size - 1) {
            throw new ExcessaoItemNaoEncontrado("Aluno não foi encontrado");
        }
        
        var alunoDeletado = alunos.get(index);
        
        this.alunoDAO.removerAluno(alunoDeletado.getId());
        
        return alunoDeletado;
    }
}
