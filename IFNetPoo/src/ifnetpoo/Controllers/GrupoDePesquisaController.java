/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifnetpoo.Controllers;

import ifnetpoo.Models.Aluno;
import ifnetpoo.Models.Pesquisa;
import ifnetpoo.Models.Usuario;

import ifnetpoo.DAO.PesquisaDAO;

import ifnetpoo.Database.MySQLConnection;

import ifnetpoo.CustomExceptions.ExcessaoItemNaoEncontrado;

import java.util.ArrayList;

/**
 *
 * @author gabriel
 */
public class GrupoDePesquisaController {
    private final PesquisaDAO pesquisaDAO;
    
    public GrupoDePesquisaController() {
       var conn = new MySQLConnection();
       
       this.pesquisaDAO = new PesquisaDAO(conn);
    }
    
    public ArrayList<Pesquisa> index() {
        return this.pesquisaDAO.getGrupos();
    }
    
    public Pesquisa show(int index) {
        var grupos = this.index();
        int size = grupos.size();
        
        if (index < 0 || index > size - 1) {
            throw new ExcessaoItemNaoEncontrado("Grupo não encontrado");
        }
          
        return grupos.get(index);
    }
    
    public Pesquisa store(Usuario orientador, String nome) {
        return this.pesquisaDAO.criarGrupo(orientador, nome);
    }
    
     public Pesquisa destroy(int index) {
        var grupos = this.index();
        int size = grupos.size();
        
        if (index < 0 || index > size - 1) {
            throw new ExcessaoItemNaoEncontrado("Grupo não encontrado");
        }
        
        var grupoDeletado = grupos.get(index);
        
        this.pesquisaDAO.removerGrupo(grupoDeletado.getId());
        
        return grupoDeletado;
    }
    
    public ArrayList<Pesquisa> getMaisPopulares() {
        return this.pesquisaDAO.getTresGruposMaisPopulares();
    }
      
    public void associaAluno(Aluno aluno, Pesquisa grupo) {
        this.pesquisaDAO.cadastraAlunoEmGrupo(aluno, grupo);
    }
}
