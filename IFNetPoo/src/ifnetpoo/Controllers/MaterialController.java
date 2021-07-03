package ifnetpoo.Controllers;

import ifnetpoo.Models.PaginaWeb;
import ifnetpoo.Models.Livro;
import ifnetpoo.Models.Usuario;
import ifnetpoo.Models.Disciplina;

import ifnetpoo.Interfaces.IMaterial;

import ifnetpoo.DAO.MaterialDAO;

import ifnetpoo.Database.MySQLConnection;

import ifnetpoo.CustomExceptions.ExcessaoItemNaoEncontrado;
import ifnetpoo.Models.Apostila;

import java.util.ArrayList;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author gabriel
 */
public class MaterialController {
    private final MaterialDAO materialDAO;
    
    public MaterialController() {
       var conn = new MySQLConnection();
       
       this.materialDAO = new MaterialDAO(conn);
    }
    
    public ArrayList<IMaterial> index() {
        return materialDAO.getMateriais();
    }
    
    public Livro store(String nome, String categoria, String autor, int edicao, int numeroDePaginas, Usuario criador, Disciplina disciplina) {
        return this.materialDAO.adicionarMaterial(nome, categoria, autor, edicao, numeroDePaginas, criador, disciplina);
    }
    
    public Apostila store(String nome, String categoria, Usuario criador, String area, Disciplina disciplina) {
        return this.materialDAO.adicionarMaterial(nome, categoria, criador, area, disciplina);
    }
    
    public PaginaWeb store (String nome, String categoria, String url, Usuario criador, Disciplina disciplina) {
        return this.materialDAO.adicionarMaterial(nome, categoria, url, criador, disciplina);       
    }
    
    public IMaterial destroy(int index) {
        var materiais = this.materialDAO.getMateriais();
        int size = materiais.size();
          
        if (index < 0 || index > size - 1) {
            throw new ExcessaoItemNaoEncontrado("Material não foi encontrado");
        }
        
        var materialDeletado = materiais.get(index);
        
        this.materialDAO.removerMaterial(materialDeletado.getId());
        
        return materialDeletado;
    }
}
