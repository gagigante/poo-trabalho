/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifnetpoo.Controllers;

import ifnetpoo.Models.Aluno;
import ifnetpoo.Models.Disciplina;
import ifnetpoo.Models.Usuario;
import ifnetpoo.Models.Trabalho;

import ifnetpoo.DAO.TrabalhoDAO;

import ifnetpoo.Database.MySQLConnection;

import ifnetpoo.CustomExceptions.ExcessaoItemNaoEncontrado;

import java.util.ArrayList;

/**
 *
 * @author gabriel
 */
public class GrupoDeTrabalhoController {
    TrabalhoDAO trabalhoDAO;
    
    public GrupoDeTrabalhoController() {
       var conn = new MySQLConnection();
       
       this.trabalhoDAO = new TrabalhoDAO(conn);
    }
    
    public ArrayList<Trabalho> index() {
        return this.trabalhoDAO.getGrupos();
    }
    
    public ArrayList<Trabalho> index(Disciplina disciplina) {
        return this.trabalhoDAO.getGruposPorDisciplina(disciplina.getId());
    }
    
    public Trabalho show(int index) {
        var grupos = this.index();
        int size = grupos.size();
        
        if (index < 0 || index > size - 1) {
            throw new ExcessaoItemNaoEncontrado("Grupo não encontrado");
        }
          
        return grupos.get(index);
    }
    
    public Trabalho store(Disciplina disciplina, String nome, Usuario criador) {
        return this.trabalhoDAO.criarGrupo(disciplina, nome, criador);
    }
    
    public Trabalho destroy(int index) {
        var grupos = this.index();
        int size = grupos.size();
        
        if (index < 0 || index > size - 1) {
            throw new ExcessaoItemNaoEncontrado("Grupo não encontrado");
        }
        
        var grupoDeletado = grupos.get(index);
        
        this.trabalhoDAO.removerGrupo(grupoDeletado.getId());
        
        return grupoDeletado;
    }
    
    public ArrayList<Trabalho> getMaisPopulares() {
        return this.trabalhoDAO.getTresGruposMaisPopulares();
    }
    
    public void associaAluno(Aluno aluno, Trabalho grupo) {
        this.trabalhoDAO.cadastraAlunoEmGrupo(aluno, grupo);
    }
}
